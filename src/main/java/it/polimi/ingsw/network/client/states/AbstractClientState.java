package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;

/**
 * abstract client state class, to be extended by CLI or GUI implementation
 */
public abstract class AbstractClientState {

    protected Client client;

    /**
     * displays the client state
     */
    public abstract void render();

    /**
     * displays and manages an error sent by the server
     * @param message error message
     */
    public void serverError(String message){
        throw new UnsupportedOperationException();
    }

    /**
     * displays a disconnection error
     * @param message error message
     */
    public void disconnectionError(String message){
        System.out.println(message);
    }
}
