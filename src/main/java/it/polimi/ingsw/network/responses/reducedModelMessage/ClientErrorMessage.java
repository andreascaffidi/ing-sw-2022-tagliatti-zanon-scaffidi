package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class ClientErrorMessage implements ResponseMessage, ClientExecute {

    private String message;

    public ClientErrorMessage(String message){
        this.message = message;
    }

    @Override
    public void execute(Client client) {
        client.getCurrentState().clientError(message);
    }
}
