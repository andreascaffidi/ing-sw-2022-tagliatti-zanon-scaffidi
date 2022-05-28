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

        client.getUI().showModel(client.getReducedModel());
        System.out.println("It's your turn! Move Mother Nature by typing the number of movement you want, ");
        if (client.getReducedModel() instanceof ReducedModelExpertMode){
            System.out.println("Or you can even pay a character card from the available, by typing " +
                    "PAY CHARACTER (you can pay a character card only one time per round)");
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

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        if (myBoard != null){
            maxMovement = myBoard.getAssistantDeck().getPlayedAssistant().getMotherNatureMovements();
            System.out.println("Maximum movements available: " + maxMovement);
        }

        while (!exit){
            String input = in.nextLine();
            if (client.getReducedModel() instanceof ReducedModelExpertMode &&
                    input.equalsIgnoreCase("PAY CHARACTER")){
                if ( ((ReducedModelExpertMode) client.getReducedModel()).isCharacterAlreadyPlayed()){
                    System.out.println("You have already played a character in this round ");
                }else{
                    exit = true;
                    payCharacter = true;
                    client.changeState(ClientState.PLAY_CHARACTER);
                }
            }else {
                try {
                    movementChosen = Integer.parseInt(input);
                    if (movementChosen < 1 || movementChosen > maxMovement) {
                        System.out.println("Invalid Mother Nature Movement ");
                    } else {
                        System.out.println("Mother Nature moved, waiting for the other players...");
                        exit = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You have to insert a number ");
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
        System.out.println(message);
        render();
    }
}
