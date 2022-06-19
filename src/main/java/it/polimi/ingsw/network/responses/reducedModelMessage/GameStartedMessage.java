package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

/**
 * game started message
 */
public class GameStartedMessage implements ResponseMessage, ClientExecute {
    private final ReducedModel reducedModel;
    private ClientState clientState;

    /**
     * builds game started message
     * @param reducedModel reduced model
     */
    public GameStartedMessage(ReducedModel reducedModel){
        this.reducedModel = reducedModel;
        clientState = ClientState.PLAY_ASSISTANT;
    }

    /**
     * implements ClientExecute interface
     * sets the reduced model to clients and sets the waiting message to all clients that aren't the current
     * player. At the end executes changeState() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client){
        client.setReducedModel(reducedModel);
        if(!reducedModel.getCurrentPlayer().equals(client.getUsername()))
        {
            clientState = ClientState.WAITING;
            client.setWaitingMessage("It's " + reducedModel.getCurrentPlayer() + " turn, waiting for yours...");
        }
        client.changeState(clientState);
    }
}
