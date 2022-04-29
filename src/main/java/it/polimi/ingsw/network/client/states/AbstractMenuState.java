package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;

public abstract class AbstractMenuState extends AbstractClientState{
    protected String command;

    @Override
    public void notifyFromUI(Client client){
        client.send(new SetupRequestMessage("COMMAND", command));
    }

}
