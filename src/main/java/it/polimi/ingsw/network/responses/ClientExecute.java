package it.polimi.ingsw.network.responses;

import it.polimi.ingsw.network.client.Client;


/**
 * interface used to dispatch Client methods
 */
public interface ClientExecute {

    /**
     * executes a method on Client class
     * @param client client
     */
    void execute(Client client);
}
