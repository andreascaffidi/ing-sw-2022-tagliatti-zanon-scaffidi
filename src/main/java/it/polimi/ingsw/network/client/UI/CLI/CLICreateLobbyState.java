package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;

import java.util.Scanner;

/**
 * CLI create lobby state class
 */
public class CLICreateLobbyState extends AbstractClientState {
    private final Client client;
    private final Scanner in;
    private String gameMode;
    private int numOfPlayers;

    /**
     * builds a CLI create lobby state class
     * @param client client
     */
    public CLICreateLobbyState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    /**
     * displays create lobby state on command line
     */
    @Override
    public void render(){
        CLI.CTA("Insert the game mode by typing "+CLI.Ansi.colorize("NORMAL", CLI.Ansi.UNDERLINE)+
                " or "+CLI.Ansi.colorize("EXPERT", CLI.Ansi.UNDERLINE));
        boolean valid = false;
        while (!valid){
            String rawInput = in.nextLine();
            String input = rawInput.toUpperCase();
            if (input.equals("NORMAL")){
                gameMode = input;
                valid = true;
            }else if(input.equals("EXPERT")){
                gameMode = input;
                valid = true;
            }else{
                CLI.error("["+rawInput+"] is not a valid command\n");
            }
        }

        CLI.CTA("Insert the number of players (maximum 4 players)");
        int num=0;
        while (num < 2 || num > 4){
            try {
                num = Integer.parseInt(in.nextLine());
                if (num < 2 || num > 4){
                    CLI.error("Invalid number of players ");
                }else{
                    numOfPlayers = num;
                }
            }catch (NumberFormatException e){
                CLI.error("You have to insert a number ");
            }
        }

        String host = client.getUsername();
        client.send(new CreateLobbyMessage(host, gameMode, numOfPlayers));
    }

}
