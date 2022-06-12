package it.polimi.ingsw.network.responses.setupMessages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

/**
 * waiting message
 */
public class WaitingMessage implements ResponseMessage, ClientExecute {
    private final ClientState clientState;
    private final String message;

    /**
     * builds a waiting message
     * @param clientState client state to set
     * @param message waiting message
     */
    public WaitingMessage(ClientState clientState, String message){
        this.clientState = clientState;
        this.message = message;
    }

    /**
     * implements ClientExecute interface
     * sets the waiting message to client and executes changeState() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client){
        client.setWaitingMessage(message);
        client.changeState(clientState);
    }
}
