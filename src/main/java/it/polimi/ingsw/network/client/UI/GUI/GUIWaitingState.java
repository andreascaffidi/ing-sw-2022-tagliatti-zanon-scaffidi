package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;

public class GUIWaitingState extends AbstractClientState {
    private final Client client;

    public GUIWaitingState(Client client){
        this.client = client;
    }

    @Override
    public void render(){

    }
}
