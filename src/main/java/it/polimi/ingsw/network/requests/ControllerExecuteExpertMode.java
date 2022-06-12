package it.polimi.ingsw.network.requests;

import it.polimi.ingsw.controller.ControllerExpertMode;

/**
 * interface used to dispatch controller (expert mode) methods
 */
public interface ControllerExecuteExpertMode {

    /**
     * executes a method on the controller (expert mode)
     * @param controller controller (expert mode)
     */
    void execute(ControllerExpertMode controller);
}
