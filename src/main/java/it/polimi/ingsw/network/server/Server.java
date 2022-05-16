package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.setupMessages.ChooseTeamMessage;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.ServerErrorMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.DisconnectErrorMessage;
import it.polimi.ingsw.network.responses.setupMessages.ShowLobbyMessage;
import it.polimi.ingsw.network.responses.setupMessages.WaitingMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT= 12345;
    private final ServerSocket serverSocket;

    private final ExecutorService executor = Executors.newFixedThreadPool(128);

    private final Map<String, Connection> clients;

    private final Map<Lobby, List<Connection>> waitingLobbies;

    private final Map<Lobby, List<Connection>> playingLobbies;

    private final List<Connection> connections = new ArrayList<>();

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.clients = new HashMap<>();
        this.waitingLobbies = new HashMap<>();
        this.playingLobbies = new HashMap<>();
    }

    private synchronized void registerConnection(Connection connection){
        connections.add(connection);
    }

    public synchronized void deregisterConnection(Connection connection){
        connections.remove(connection);
        //remove from clients list
        clients.remove(connection.getUsernameConnection());

        //remove connection from the lobby (if the player is in a lobby)
        if (connection.getHostLobby() != null){
            Lobby lobby = waitingLobbies.keySet().stream().filter(l -> l.getHost().equals(connection.getHostLobby()))
                    .findFirst().orElse(null);

            //if the player is in a waiting lobby
            if (lobby != null) {
                List<Connection> lobbyConnections = waitingLobbies.get(lobby);
                lobbyConnections.remove(connection);
                lobby.removeConnection();

                //if lobby for 4 players, remove player from the team
                if (lobby.getNumOfPlayers() == 4){
                    List<String> team1 = lobby.getPlayersByTeam(1);
                    if (team1.contains(connection.getUsernameConnection())){
                        team1.remove(connection.getUsernameConnection());
                        lobby.setTeams(1, team1);
                    }else{
                        List<String> team2 = lobby.getPlayersByTeam(2);
                        team2.remove(connection.getUsernameConnection());
                        lobby.setTeams(2, team2);
                    }
                }

                //if the player is the host, remove the lobby
                if (connection.getUsernameConnection().equals(connection.getHostLobby())){
                    waitingLobbies.remove(lobby);

                    //close all the connections
                    for (Connection c : lobbyConnections){
                        c.send(new DisconnectErrorMessage("Host has left the lobby, you have been disconnected"));
                        c.send(new ShowLobbyMessage(this.getWaitingLobbies()));
                    }

                }else {
                    //if the player isn't the host
                    waitingLobbies.put(lobby, lobbyConnections);

                    int missingPlayers = lobby.getNumOfPlayers() - lobbyConnections.size();

                    for (Connection c : lobbyConnections) {
                        c.send(new DisconnectErrorMessage(connection.getUsernameConnection() + " has left the lobby, " +
                                "waiting for " + missingPlayers + " players"));
                    }
                }

            }else{
                lobby = playingLobbies.keySet().stream().filter(l -> l.getHost().equals(connection.getHostLobby()))
                        .findFirst().orElse(null);

                //if the player is in a playing lobby
                if (lobby != null) {
                    //remove playing lobby
                    List<Connection> lobbyConnections = playingLobbies.get(lobby);
                    lobbyConnections.remove(connection);
                    lobby.removeConnection();
                    playingLobbies.remove(lobby);

                    //close all the connections
                    for (Connection c : lobbyConnections){
                        c.send(new DisconnectErrorMessage(connection.getUsernameConnection() + " has left the game" +
                                "\nGAME ENDED"));
                        c.closeConnection();
                    }
                }
            }
        }

        System.out.println("Connection number: " + getNumOfConnections());
    }

    public synchronized void joinLobby(String host, Connection connection){
        //search for host's lobby
        Lobby lobby = waitingLobbies.keySet().stream().filter(l -> l.getHost().equals(host)).findFirst().orElse(null);
        if (lobby == null){
            connection.send(new ServerErrorMessage("Lobby no more available"));
            connection.send(new ShowLobbyMessage(this.getWaitingLobbies()));
        }else{
            //add this connection to lobby
            lobby.addConnection();
            List<Connection> lobbyConnections = waitingLobbies.get(lobby);
            lobbyConnections.add(connection);
            waitingLobbies.put(lobby, lobbyConnections);

            //notify other players
            int missingPlayers = lobby.getNumOfPlayers() - lobbyConnections.size();
            for (Connection c : lobbyConnections){
                if (c.equals(connection)){
                    c.send(new WaitingMessage(ClientState.WAITING, "Lobby joined, waiting for " + missingPlayers + " players..."));
                }else{
                    c.send(new WaitingMessage(ClientState.WAITING, connection.getUsernameConnection() + " joined, waiting for " + missingPlayers + " players..."));
                }
            }

            //if lobby is full start the game
            if(missingPlayers == 0){
                createMatch(lobby, lobbyConnections);
            }
        }

    }

    public void run(){
        System.out.println("Server listening on port: " + PORT);
        while(true){
            try {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket, this);
                registerConnection(connection);
                System.out.println("Connection number: " + getNumOfConnections());
                executor.submit(connection);
            } catch (IOException e){
                System.err.println("Connection error!");
            }
        }
    }

    public boolean validUsername(String username, Connection connection){
        if (clients.containsKey(username)){
            return false;
        }else{
            clients.put(username, connection);
            return true;
        }
    }

    public void createLobby(CreateLobbyMessage settings, Connection connection){
        List<Connection> lobbyConnections = new ArrayList<>();
        lobbyConnections.add(connection);
        Lobby lobby = new Lobby(settings.getHost(), settings.getGameMode(), settings.getNumOfPlayers());
        if (lobby.getNumOfPlayers() == 4){
            List<String> team1 = new ArrayList<>();
            List<String> team2 = new ArrayList<>();
            team1.add(connection.getUsernameConnection());
            lobby.setTeams(1, team1);
            lobby.setTeams(2, team2);
        }
        waitingLobbies.put(lobby, lobbyConnections);
        connection.send(new WaitingMessage(ClientState.WAITING, "Lobby created, waiting for players..."));
    }

    public synchronized void setLobbyTeam(ChooseTeamMessage message, Connection connection){
        Lobby lobby = waitingLobbies.keySet().stream().filter(l -> l.getHost().equals(message.getSelectedHost())).findFirst().orElse(null);
        //if there isn't the lobby
        if (lobby == null) {
            connection.send(new ServerErrorMessage("Lobby no more available"));
            connection.send(new ShowLobbyMessage(this.getWaitingLobbies()));
        }else{
            //if selected team is full
            if (lobby.getPlayersByTeam(message.getTeam()).size() == 2){
                connection.send(new ServerErrorMessage("Team is full, select another lobby: "));
                connection.send(new ShowLobbyMessage(this.getWaitingLobbies()));
            }else{
                //if selected team goes right, add player to the team and join the lobby
                List<String> players = lobby.getPlayersByTeam(message.getTeam());
                players.add(connection.getUsernameConnection());
                lobby.setTeams(message.getTeam(), players);
                connection.setHostLobby(message.getSelectedHost());
                joinLobby(message.getSelectedHost(), connection);
            }
        }
    }

    public List<Lobby> getWaitingLobbies() {
        return new ArrayList<>(waitingLobbies.keySet());
    }

    public int getNumOfConnections() {
        return connections.size();
    }

    private void createMatch(Lobby lobby, List<Connection> lobbyConnections){
        System.out.println("Lobby full, create match");

        for (Connection c : lobbyConnections){
            c.send(new WaitingMessage(ClientState.WAITING, "GAME IS STARTING !!!"));
        }

        List<Player> players = new ArrayList<>();
        for (Connection c : lobbyConnections){
            //TODO: mi sono dimenticato di creare players con tag team in caso di partita a 4
            Player player = new Player(c.getUsernameConnection());
            players.add(player);
        }

        if (lobby.getGameMode().equals("EXPERT")){
            TableExpertMode model = new TableExpertMode(players);
            ControllerExpertMode controller = new ControllerExpertMode(model);
            for(Connection c : lobbyConnections){
                model.addObserver(c);
                c.addObserver(controller);
            }
            model.startGame();
        }else{
            Table model = new Table(players);
            Controller controller = new Controller(model);
            for(Connection c : lobbyConnections){
                model.addObserver(c);
                c.addObserver(controller);
            }
            model.startGame();
        }

        waitingLobbies.remove(lobby);
        playingLobbies.put(lobby, lobbyConnections);
    }
}
