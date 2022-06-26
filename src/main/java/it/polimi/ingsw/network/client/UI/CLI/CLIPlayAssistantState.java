package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;

import java.util.List;
import java.util.Scanner;

/**
 * CLI play assistant state class
 */
public class CLIPlayAssistantState extends AbstractClientState {

    private final Client client;
    private final Scanner in;

    /**
     * builds a CLI play assistant state class and print the main information
     * @param client client
     */
    public CLIPlayAssistantState(Client client){
        this.client = client;
        in = new Scanner(System.in);
        CLI.showModel(client.getReducedModel());
        CLI.CTA("It's your turn! Play an Assistant Card by typing the "+CLI.Ansi.colorize("ID", CLI.Ansi.UNDERLINE));
    }

    /**
     * displays play assistant state on command line
     */
    @Override
    public void render(){
        int assistantChosen = 0;
        boolean exit = false;

        ReducedBoard myBoard = client.getReducedModel().getBoard(client.getUsername());

        List<Integer> possibleChoices = null;
        if(myBoard != null){
            possibleChoices = myBoard.getAssistantDeck().getPossibleChoices();
        }

        while (possibleChoices != null && !exit){
            String input = in.nextLine();
            try{
                assistantChosen = Integer.parseInt(input);
                if (!possibleChoices.contains(assistantChosen)) {
                    CLI.error("You can't play this assistant ");
                } else {
                    System.out.println("Assistant chosen, waiting for players...");
                    exit = true;
                }
            }catch (NumberFormatException e){
                CLI.error("You have to insert a number ");
            }
        }

        client.send(new PlayAssistantMessage(assistantChosen));
    }

    /**
     * manages server error on command line
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        CLI.error(message);
        render();
    }
}