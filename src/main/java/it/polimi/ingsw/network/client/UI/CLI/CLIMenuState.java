package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;

import java.util.Scanner;

/**
 * CLI menu state class
 */
public class CLIMenuState extends AbstractClientState {
    private final Client client;
    private final Scanner in;
    private String command;

    /**
     * builds a CLI menu state class
     * @param client client
     */
    public CLIMenuState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    /**
     * displays menu state on command line
     */
    @Override
    public void render(){
        System.out.print("Create or join a lobby by typing: CREATE or JOIN ");
        boolean valid = false;
        while (!valid){
            String input = in.nextLine().toUpperCase();
            if (input.equals("JOIN")){
                command = input;
                valid = true;
            }else if(input.equals("CREATE")){
                command = input;
                valid = true;
            }else{
               CLI.error("Unknown command, please type: CREATE or JOIN ");
            }
        }
        client.send(new SetupRequestMessage(SetupResponsesTypes.COMMAND, command));
    }
}
