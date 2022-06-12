package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

/**
 * server error message
 */
public class ServerErrorMessage implements ResponseMessage, ClientExecute {

    private final String message;

    /**
     * builds a server error message
     * @param message error message
     */
    public ServerErrorMessage(String message){
        this.message = message;
    }

    /**
     * implements ClientExecute interface
     * executes serverError() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client) {
        client.getCurrentState().serverError(message);
    }
}
