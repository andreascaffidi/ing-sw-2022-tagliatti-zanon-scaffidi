package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;

import java.util.Scanner;

public class CLIWelcomeState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private String username;

    public CLIWelcomeState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        System.out.println("\n\t\t\t\t\t\t\t"+Ansi.colorize("W E L C O M E   T O",Ansi.UNDERLINE));
        CLI.showLogo();
        System.out.print("Insert a username: ");
        this.askUsername();
    }

    @Override
    public void serverError(String message) {
        CLI.error(message);
        this.askUsername();
    }

    private void askUsername(){
        username = in.nextLine();
        //TODO: evitare che lo user si chiami \n
        client.setUsername(username.toLowerCase());
        client.send(new SetupRequestMessage(SetupResponsesTypes.USERNAME, username));
    }
}
