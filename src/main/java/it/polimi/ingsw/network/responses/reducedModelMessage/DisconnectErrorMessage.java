package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

/**
 * disconnect error message
 */
public class DisconnectErrorMessage implements ResponseMessage, ClientExecute {
    private final String message;

    /**
     * builds a disconnect error message
     * @param message disconnect error message
     */
    public DisconnectErrorMessage(String message){
        this.message = message;
    }

    /**
     * implements ClientExecute interface
     * executes disconnectError() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client) {
        client.getCurrentState().disconnectionError(message);
    }
}
