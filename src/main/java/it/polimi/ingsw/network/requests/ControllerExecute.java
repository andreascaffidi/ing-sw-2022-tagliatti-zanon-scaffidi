package it.polimi.ingsw.network.requests;

import it.polimi.ingsw.controller.Controller;

public interface ControllerExecute {
    void execute(Controller controller, String username);
}
