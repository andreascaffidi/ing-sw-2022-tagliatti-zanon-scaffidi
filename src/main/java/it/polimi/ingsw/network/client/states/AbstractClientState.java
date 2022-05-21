package it.polimi.ingsw.network.client.states;

import java.net.MalformedURLException;

public abstract class AbstractClientState {

    public abstract void render() throws MalformedURLException;

    public void serverError(String message) throws MalformedURLException {
        throw new UnsupportedOperationException();
    }

    public void disconnectionError(String message){
        System.out.println(message);
    }
}
