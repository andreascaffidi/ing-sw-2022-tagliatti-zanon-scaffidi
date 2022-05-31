package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;

import java.util.Scanner;

/**
 * CLI welcome state class
 */
public class CLIWelcomeState extends AbstractClientState {
    private final Scanner in;
    private final String CTA = "Insert a username: "+Ansi.TYPING_ICON+" ";
    /**
     * builds a CLI welcome state class
     * @param client client
     */
    public CLIWelcomeState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    /**
     * displays welcome state on command line
     */
    @Override
    public void render(){
        System.out.println("\n\t\t\t\t\t\t\t"+Ansi.colorize("W E L C O M E   T O",Ansi.UNDERLINE));
        CLI.showLogo();
        System.out.print(CTA);
        this.askUsername();
    }

    /**
     * manages server error on command line
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        CLI.error(message);
        this.askUsername();
    }

    /**
     * asks username on command line and sends it to the server
     */
    private void askUsername(){
        boolean valid = false;
        String username = null;
        while(!valid){
            username = in.nextLine();
            if (username.contains(" ") || username.length() == 0){
                CLI.error("Username not permitted (avoid white spaces)\n");
                System.out.print(CTA);
            }else {
                valid = true;
            }
        }
        client.setUsername(username.toLowerCase());
        client.send(new SetupRequestMessage(SetupResponsesTypes.USERNAME, username.toLowerCase()));
    }
}
