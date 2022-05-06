package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;

public abstract class AbstractChooseCloudState extends AbstractClientState{

    protected int id;

    @Override
    public void notifyFromUI(Client client){
        client.send(new ChooseCloudMessage(id));
    }
}
