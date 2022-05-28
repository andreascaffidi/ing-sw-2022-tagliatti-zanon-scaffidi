package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.CLI.CLI;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

import java.net.MalformedURLException;

public class GameStartedMessage implements ResponseMessage, ClientExecute {
    private final ReducedModel reducedModel;
    private ClientState clientState;

    public GameStartedMessage(ReducedModel reducedModel){
        this.reducedModel = reducedModel;
        clientState = ClientState.PLAY_ASSISTANT;
    }

    @Override
    public void execute(Client client) throws MalformedURLException {
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
