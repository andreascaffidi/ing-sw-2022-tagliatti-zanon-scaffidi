package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;


/**
 * reduced model message
 */
public class ReducedModelMessage implements ResponseMessage, ClientExecute {
    private ClientState clientState;
    private final ReducedModel reducedModel;

    /**
     * builds a reduced model message
     * @param clientState client state to set
     * @param reducedModel reduced model
     */
    public ReducedModelMessage(ClientState clientState, ReducedModel reducedModel){
        this.clientState = clientState;
        this.reducedModel = reducedModel;
    }

    /**
     * implements ClientExecute interface
     * sets the reduced model to clients, shows it and sets the waiting message to all clients that aren't the current
     * player. At the end executes changeState() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client){
        client.setReducedModel(reducedModel);
        if(!reducedModel.getCurrentPlayer().equals(client.getUsername()))
        {
            client.getUI().showModel(reducedModel);
            clientState = ClientState.WAITING;
            client.setWaitingMessage("It's " + reducedModel.getCurrentPlayer() + " turn, waiting for yours...");
        }
        client.changeState(clientState);
    }
}