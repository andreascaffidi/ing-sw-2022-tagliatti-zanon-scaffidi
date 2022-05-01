package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT= 12345;
    private ServerSocket serverSocket;

    private ExecutorService executor = Executors.newFixedThreadPool(128);

    private Map<String, Connection> clients;

    private Map<Lobby, List<Connection>> lobbies;

    private List<Connection> connections = new ArrayList<Connection>();

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.clients = new HashMap<>();
        this.lobbies = new HashMap<>();
    }

    private synchronized void registerConnection(Connection c){
        connections.add(c);
    }

    public synchronized void deregisterConnection(Connection c){
        connections.remove(c);
        //rimuovere client
        clients.remove(c.getUsernameConnection());
        //rimuovere connessione dalla lobby
        if (c.getHostLobby() != null){
            Lobby lobby = lobbies.keySet().stream().filter(l -> l.getHost().equals(c.getHostLobby())).findFirst().orElse(null);
            List<Connection> lobbyConnections = lobbies.get(lobby);
            lobbyConnections.remove(c);
            lobby.removeConnection();
        }

        System.out.println("Connection number: " + getNumOfConnections());
                /*
        Connection opponent = playingConnection.get(c);
        if(opponent != null){
            opponent.closeConnection();
            playingConnection.remove(c);
            playingConnection.remove(opponent);
            //Iterator<String> iterator = waitingConnection.keySet().iterator();
            //while(iterator.hasNext()){
            //    if(waitingConnection.get(iterator.next())==c){
            //        iterator.remove();
            //    }
            //}

        }*/

    }

    public synchronized void joinLobby(String host, Connection c){
        //trovare lobby dell'host
        Lobby lobby = lobbies.keySet().stream().filter(l -> l.getHost().equals(host)).findFirst().orElse(null);
        lobby.addConnection();
        //prendere le connessioni e aggiungerci questa
        List<Connection> lobbyConnections = lobbies.get(lobby);
        lobbyConnections.add(c);
        lobbies.put(lobby, lobbyConnections);
        if(lobby.getNumOfPlayers() == lobbyConnections.size()){
            System.out.println("Lobby full");

            List<Player> players = new ArrayList<>();
            //List<RemoteView> playerViews = new ArrayList<>();
            for (Connection conn : lobbyConnections){
                Player player = new Player(conn.getUsernameConnection());
                //RemoteView playerView = new RemoteView(player, conn);
                players.add(player);
                //playerViews.add(playerView);
            }

            if (lobby.getGameMode().equals("EXPERT")){
                TableExpertMode model = new TableExpertMode(players);
                ControllerExpertMode controller = new ControllerExpertMode(model);
                for(Connection conn : lobbyConnections){
                    model.addObserver(conn);
                    conn.addObserver(controller);
                }
            }else{
                Table model = new Table(players);
                Controller controller = new Controller(model);
                for(Connection conn : lobbyConnections){
                    model.addObserver(conn);
                    conn.addObserver(controller);
                }
                //fixme: prova
                model.notifyall();
            }

            /*
            playingConnection.put(c1, c2);
            playingConnection.put(c2, c1);
            waitingConnection.clear();
             */
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
        lobbies.put(new Lobby(settings.getHost(), settings.getGameMode(), settings.getNumOfPlayers()), lobbyConnections);
    }

    public List<Lobby> getLobbies() {
        return new ArrayList<>(lobbies.keySet());
    }

    public int getNumOfConnections() {
        return connections.size();
    }
}
