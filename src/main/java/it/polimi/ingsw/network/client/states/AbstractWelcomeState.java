package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;

public abstract class AbstractWelcomeState extends AbstractClientState{

    protected String username;

    @Override
    public void notifyFromUI(Client client){
        client.send(new SetupRequestMessage("USERNAME", username));
    }

}
