package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.gameMessages.MoveMotherNatureMessage;

import java.util.Scanner;

/**
 * CLI move mother nature state class
 */
public class CLIMoveMotherNatureState extends AbstractClientState {
    private final Client client;
    private final Scanner in;

    /**
     * builds a CLI move mother nature state class and print the main information
     * @param client client
     */
    public CLIMoveMotherNatureState(Client client){
        this.client = client;
        in = new Scanner(System.in);

        CLI.showModel(client.getReducedModel());
        System.out.print("It's your turn! Move Mother Nature by typing the number of movement you want");
        if (client.getReducedModel() instanceof ReducedModelExpertMode){
            System.out.print("\nOr you can even pay a character card from the available, by typing "
                    +Ansi.colorize("PAY CHARACTER", Ansi.UNDERLINE) +
                    " (you can pay a character card only one time per round)\n\n");
        }
    }

    /**
     * displays move mother nature state on command line
     */
    @Override
    public void render(){
        int movementChosen = 0;
        boolean exit = false;
        boolean payCharacter = false;
        int maxMovement = 0;

        ReducedBoard myBoard = client.getReducedModel().getBoard(client.getUsername());
        if (myBoard != null){
            maxMovement = myBoard.getAssistantDeck().getPlayedAssistant().getMotherNatureMovements();
            CLI.CTA("\nMaximum movements available: " + maxMovement);
        }

        while (!exit){
            String input = in.nextLine();
            if (client.getReducedModel() instanceof ReducedModelExpertMode &&
                    input.equalsIgnoreCase("PAY CHARACTER")){
                if ( ((ReducedModelExpertMode) client.getReducedModel()).isCharacterAlreadyPlayed()){
                    CLI.error("You have already played a character in this round ");
                }else{
                    exit = true;
                    payCharacter = true;
                    client.changeState(ClientState.PLAY_CHARACTER);
                }
            }else {
                try {
                    movementChosen = Integer.parseInt(input);
                    if (movementChosen < 1 || movementChosen > maxMovement) {
                        CLI.error("Invalid Mother Nature Movement ");
                    } else {
                        System.out.println("Mother Nature moved, waiting for the other players...");
                        exit = true;
                    }
                } catch (NumberFormatException e) {
                    CLI.error("You have to insert a number ");
                }
            }
        }
        if (!payCharacter) {
            client.send(new MoveMotherNatureMessage(movementChosen));
        }
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
