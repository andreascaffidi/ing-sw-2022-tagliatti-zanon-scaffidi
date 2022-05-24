package it.polimi.ingsw.network.client.states;

public abstract class AbstractClientState {

    public abstract void render();

    public void serverError(String message){
        throw new UnsupportedOperationException();
    }

    public void disconnectionError(String message){
        System.out.println(message);
    }
}
