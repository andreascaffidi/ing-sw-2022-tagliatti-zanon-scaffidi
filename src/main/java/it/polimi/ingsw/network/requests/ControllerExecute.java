package it.polimi.ingsw.network.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.View;

public interface ControllerExecute {
    void execute(Controller controller, String username, View view);
}
