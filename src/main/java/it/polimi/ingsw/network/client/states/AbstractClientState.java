package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;

public abstract class AbstractClientState {

    public abstract void render();

    public abstract void notifyFromUI(Client client);

    public void clientError(String message){
        throw new UnsupportedOperationException();
    }

    public void serverError(String message){
        System.out.println(message);
        throw new UnsupportedOperationException();
    }
}
