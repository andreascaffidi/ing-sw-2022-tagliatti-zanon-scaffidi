package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedAssistant;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        client.getUI().showModel(client.getReducedModel());
        System.out.println("It's your turn! Play an Assistant Card by typing the id ");
        if (client.getReducedModel() instanceof ReducedModelExpertMode){
            System.out.println("Or you can even pay a character card from the available, by typing " +
                    "PAY CHARACTER (you can pay a character card only one time per round)");
        }
    }

    /**
     * displays play assistant state on command line
     */
    @Override
    public void render(){
        int assistantChosen = 0;
        boolean exit = false;
        boolean payCharacter = false;
        List<Integer> possibleChoices = null;

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        if (myBoard != null){
            possibleChoices = myBoard.getAssistantDeck().getAssistantCards().stream()
                    .map(ReducedAssistant::getId).collect(Collectors.toList());
        }

        while (possibleChoices != null && !exit){
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
                try{
                    assistantChosen = Integer.parseInt(input);
                    if (!possibleChoices.contains(assistantChosen)) {
                        System.out.println("You can't play this assistant ");
                    } else {
                        System.out.println("Assistant chosen, waiting for players...");
                        exit = true;
                    }
                }catch (NumberFormatException e){
                    System.out.println("You have to insert a number ");
                }
            }
        }
        if (!payCharacter){
            client.send(new PlayAssistantMessage(assistantChosen));
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