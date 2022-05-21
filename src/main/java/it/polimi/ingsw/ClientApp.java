package it.polimi.ingsw;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.CLI.CLI;
import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.UI.GUI.JavaFXGUI;
import it.polimi.ingsw.network.client.UI.UI;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class ClientApp
{

    private static UI ui;
    private enum LaunchComponent{
        CLI, GUI
    }

    /**
     * initializes and launches the client
     * to run the client with a GUI, do not specify any command line argument or specify "gui"
     * to run the client with a CLI, specify "cli" as the first command line argument
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main( String[] args ) throws IOException {
        String launchComponentValue = args.length > 0 ? args[0] : "gui";
        LaunchComponent launchComponent = LaunchComponent.valueOf(launchComponentValue.toUpperCase());

        switch(launchComponent)
        {
            case GUI:
            {
                ui = new GUI();
                JavaFXGUI.main(null);
                //ui.init();
                break;
            }
            case CLI: ui = new CLI();
            break;
        }
        Client client = new Client("127.0.0.1", 12345, ui);
        client.startClient();
    }

}

