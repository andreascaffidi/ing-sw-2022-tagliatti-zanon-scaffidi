package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

import java.net.MalformedURLException;

public class ServerErrorMessage implements ResponseMessage, ClientExecute {

    private String message;

    public ServerErrorMessage(String message){
        this.message = message;
    }

    @Override
    public void execute(Client client) throws MalformedURLException {
        client.getCurrentState().serverError(message);
    }
}
