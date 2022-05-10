package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;


public class CLIWaitingState extends AbstractClientState {
    private final Client client;

    public CLIWaitingState(Client client){
        this.client = client;
    }

    @Override
    public void render(){
        System.out.println(client.getWaitingMessage());
    }
}