package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class ServerErrorMessage implements ResponseMessage, ClientExecute {
    private String message;

    public ServerErrorMessage(String message){
        this.message = message;
    }

    @Override
    public void execute(Client client) {
        client.getCurrentState().serverError(message);
    }
}
