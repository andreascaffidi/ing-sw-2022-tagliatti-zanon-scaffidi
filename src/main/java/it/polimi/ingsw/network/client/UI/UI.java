package it.polimi.ingsw.network.client.UI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;

/**
 * interface UI, to be implemented by GUI or CLI classes
 */
public interface UI {

    /**
     * gets the client state class (GUI or CLI implementation)
     * @param clientState client state enum
     * @param client client
     * @return client state class
     */
    AbstractClientState getClientState(ClientState clientState, Client client);
}