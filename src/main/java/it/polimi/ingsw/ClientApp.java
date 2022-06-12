package it.polimi.ingsw;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.CLI.CLI;
import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.UI.UI;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * client application
 */
public class ClientApp
{

    /**
     * initializes and launches the client
     * to run the client with a GUI, do not specify any command line argument or specify "gui"
     * to run the client with a CLI, specify "cli" as the first command line argument
     * @param args the command line arguments
     * @throws IOException if there are some problems in Client class
     */
    public static void main( String[] args ) throws IOException {
        //final Scanner in = new Scanner(System.in);
        String UIType = args.length > 0 ? args[0].toUpperCase() : "GUI";
        UI ui;
        if (UIType.equals("GUI")){
            ui = new GUI();
        }else{
            ui = new CLI();
        }
        //IP: 127.0.0.1
        //PORT: 12345
        //String Ip = in.nextLine();
        //String port = in.nextLine();
        Client client = new Client("127.0.0.1", 12345, ui);
        client.startClient();
    }

}

