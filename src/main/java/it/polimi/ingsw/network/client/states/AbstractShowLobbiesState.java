package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;

public abstract class AbstractShowLobbiesState extends AbstractClientState{
    protected String selectedLobby;

    @Override
    public void notifyFromUI(Client client){
        client.send(new SetupRequestMessage("JOIN_LOBBY", selectedLobby));
    }

}
