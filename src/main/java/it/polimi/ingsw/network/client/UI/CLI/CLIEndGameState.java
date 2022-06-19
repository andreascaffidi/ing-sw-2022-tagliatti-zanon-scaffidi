package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.AbstractClientState;

/**
 * CLI end game state class
 */
public class CLIEndGameState extends AbstractClientState {
    private final Client client;

    /**
     * builds a CLI end game state class
     * @param client client
     */
    public CLIEndGameState(Client client){
        this.client = client;
    }

    /**
     * displays end game state on command line
     */
    @Override
    public void render()
    {
        ReducedModel reducedModel = client.getReducedModel();
        if (reducedModel.getBoards().size() == 4){
            int winningTeam = reducedModel.getBoard(client.getWinner()).getTagTeam();
            if (reducedModel.getBoard(client.getUsername()).getTagTeam() == winningTeam){
                System.out.println("Congrats! =) Team " + winningTeam + " is the winner. Wow you are so intelligent! :)");
            } else {
                System.out.println("The winner is... not you! It's team " + winningTeam);
            }
        } else {
            if(client.getUsername().equals(client.getWinner())){
                System.out.println("Congrats! =) You are the winner. Wow you are so intelligent! :)");
            }else{
                System.out.println("The winner is... not you! It's " + client.getWinner());
            }
        }
        client.disconnectClient();
    }
}
