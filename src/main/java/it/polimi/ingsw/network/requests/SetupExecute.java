package it.polimi.ingsw.network.requests;

import it.polimi.ingsw.network.server.Connection;

/**
 * interface used to dispatch Connection methods
 */
public interface SetupExecute {

    /**
     * execute a method on the Connection class
     * @param connection connection
     */
    void execute(Connection connection);
}
