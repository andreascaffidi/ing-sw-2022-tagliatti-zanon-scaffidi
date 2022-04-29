package it.polimi.ingsw.network.responses.setupMessages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;


public class SetupResponseMessage implements ResponseMessage, ClientExecute {
    private final ClientState clientState;

    public SetupResponseMessage(ClientState clientState){
        this.clientState = clientState;
    }

    @Override
    public void execute(Client client) {
        client.changeState(clientState);
    }
}
