package it.polimi.ingsw.network.responses.setupMessages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

import java.net.MalformedURLException;

public class WaitingMessage implements ResponseMessage, ClientExecute {
    private final ClientState clientState;
    private final String message;

    public WaitingMessage(ClientState clientState, String message){
        this.clientState = clientState;
        this.message = message;
    }

    @Override
    public void execute(Client client) throws MalformedURLException {
        client.setWaitingMessage(message);
        client.changeState(clientState);
    }
}
