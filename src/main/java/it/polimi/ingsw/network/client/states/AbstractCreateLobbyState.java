package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;

public abstract class AbstractCreateLobbyState extends AbstractClientState{
    protected String host;
    protected String gameMode;
    protected int numOfPlayers;

    @Override
    public void notifyFromUI(Client client){
        client.send(new CreateLobbyMessage(host, gameMode, numOfPlayers));
    }
}
