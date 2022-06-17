package it.polimi.ingsw;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.CLI.CLI;
import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.server.Server;

import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * Eriantys application
 */
public class EriantysApp
{

    /**
     * initializes and launches the client or the server
     * to run the server, do not specify any command line argument or specify "server"
     * to run the client with a GUI, specify "gui" as the first command line argument
     * to run the client with a CLI, specify "cli" as the first command line argument
     * @param args the command line arguments
     * @throws IOException if there are some problems in Client class
     */
    public static void main( String[] args ) throws IOException {
        String UIType = args.length > 0 ? args[0].toUpperCase() : "SERVER";
        String ip = args.length > 1 ? args[1] : "127.0.0.1";
        int port = args.length > 2 ? parseInt(args[2]) : 7268;

        UI ui;

        switch (UIType) {
            case "GUI": {
                ui = new GUI();
                Client client = new Client(ip, port, ui);
                client.startClient();
                break;
            }
            case "CLI": {
                ui = new CLI();
                Client client = new Client(ip, port, ui);
                client.startClient();
                break;
            }
            case "SERVER":
            {
                Server server = new Server(port);
                server.run();
            }
        }
    }
}

