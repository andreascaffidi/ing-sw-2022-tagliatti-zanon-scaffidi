package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;

import java.util.Scanner;

public class CLIMenuState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private String command;
    private final String CTA = "Create or join a lobby by typing "+Ansi.colorize("CREATE",Ansi.UNDERLINE)+" or "+Ansi.colorize("JOIN",Ansi.UNDERLINE)+": "+Ansi.TYPING_ICON+" ";
    public CLIMenuState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        boolean valid = false;
        while (!valid){
            System.out.print(CTA);
            String rawInput = in.nextLine();
            String input = rawInput.toUpperCase();
            if (input.equals("JOIN")){
                command = input;
                valid = true;
            }else if(input.equals("CREATE")){
                command = input;
                valid = true;
            }else{
               CLI.error("["+rawInput+"] is not a valid command\n");
            }
        }
        client.send(new SetupRequestMessage(SetupResponsesTypes.COMMAND, command));
    }
}
