package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;


/**
 * end game message
 */
public class EndGameMessage implements ResponseMessage, ClientExecute {
    private final String winner;

    /**
     * builds an end game message
     * @param winner winner of the game
     */
    public EndGameMessage(String winner){
        this.winner = winner;
    }

    /**
     * implements ClientExecute interface
     * sets the winner of the game to clients and executes changeState() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client){
        client.setWinner(winner);
        client.changeState(ClientState.END_GAME);
    }
}
