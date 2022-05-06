package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;

public abstract class AbstractPlayAssistantState extends AbstractClientState{

    //assistant id
    protected int id;

    @Override
    public void notifyFromUI(Client client) {
        client.send(new PlayAssistantMessage(id));
    }
}
