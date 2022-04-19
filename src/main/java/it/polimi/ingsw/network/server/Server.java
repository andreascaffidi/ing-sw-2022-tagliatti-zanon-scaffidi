package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.network.responses.messages.FirstPlayerConnectResponse;
import it.polimi.ingsw.network.responses.messages.UsernameTakenResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;

    private ArrayList<Player> lobby;
    private int numOfPlayers;

    private Map<SocketConnection, Controller> games;
    private Map<String, SocketConnection> clients;

    ExecutorService executorSocket = Executors.newCachedThreadPool();


    public Server(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
        }catch (IOException e){
            System.err.println(e.getMessage());
            return;
        }
        print("Server ready");
        while (true){
            try {
                Socket socket = serverSocket.accept();
                SocketConnection socketConnection = new SocketConnection(this, socket);
                print("Nuova connessione");
                executorSocket.submit(socketConnection);
            }catch (IOException e){
                System.err.println(e.getMessage());
                break;
            }
        }
        executorSocket.shutdown();
        serverSocket.close();
    }


    public Map<SocketConnection, Controller> getGames() {
        return games;
    }

    public void print(String text){
        System.out.println(text);
    }

    public void connect(String username, SocketConnection socketConnection) {
        if(clients.containsKey(username)){
            socketConnection.send(new UsernameTakenResponse());
        }
        clients.put(username,socketConnection);

        synchronized (lobby){
            if(lobby.isEmpty()){
                lobby.add(new Player(username));
                socketConnection.send(new FirstPlayerConnectResponse());
            }else{
                lobby.add(new Player(username));
                if(lobby.size() == numOfPlayers){
                    createGame();
                }
            }
        }

    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void  createGame(){
        numOfPlayers = -1;  //

        ArrayList players = new ArrayList<>(lobby);

        Table table = new Table(players);
        Controller controller = new Controller(table);

    }
}
