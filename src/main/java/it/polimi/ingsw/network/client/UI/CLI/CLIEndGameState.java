package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
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
        if(client.getUsername().equals(client.getWinner()))
        {
            System.out.println("Congrats! =) You are the winner. Wow you are so intelligent! :)");
        }else{
            System.out.println("The winner is... not you! It's " + client.getWinner());
        }
        client.disconnectClient();
    }
}
