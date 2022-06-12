package it.polimi.ingsw;

import it.polimi.ingsw.network.server.Server;

import java.io.IOException;

/**
 * server application
 */
public class ServerApp {

    /**
     * initializes and launch a server
     * @param args command line arguments
     * @throws IOException if there are some problems in Server class
     */
    public static void main( String[] args ) throws IOException {
        Server server = new Server();
        server.run();
    }
}
