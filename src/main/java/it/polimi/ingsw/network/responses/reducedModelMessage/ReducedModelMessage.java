package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class ReducedModelMessage implements ResponseMessage, ClientExecute {
    private final ClientState clientState;
    private final ReducedModel reducedModel;

    public ReducedModelMessage(ClientState clientState, ReducedModel reducedModel){
        this.clientState = clientState;
        this.reducedModel = reducedModel;
    }

    @Override
    public void execute(Client client) {
        client.setReducedModel(reducedModel);
        client.changeState(clientState);
    }
}
