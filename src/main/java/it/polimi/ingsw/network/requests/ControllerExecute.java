package it.polimi.ingsw.network.requests;

import it.polimi.ingsw.controller.Controller;

/**
 * interface used to dispatch controller methods
 */
public interface ControllerExecute {

    /**
     * executes a method on the controller
     * @param controller controller
     */
    void execute(Controller controller);
}
