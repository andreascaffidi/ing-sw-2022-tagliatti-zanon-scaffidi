package it.polimi.ingsw;

import it.polimi.ingsw.network.server.Server;

import java.io.IOException;

public class ServerApp {
    public final static int SERVER_PORT = 1338;

    public static void main( String[] args ) throws IOException {
        Server server = new Server(SERVER_PORT);
        server.startServer();
    }
}
