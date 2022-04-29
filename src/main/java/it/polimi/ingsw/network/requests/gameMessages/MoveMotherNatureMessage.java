package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

public class MoveMotherNatureMessage implements RequestMessage, ControllerExecute {
    private int movements;

    public MoveMotherNatureMessage(int movements) {
        this.movements = movements;
    }

    public int getMovements() {
        return movements;
    }

    @Override
    public void execute(Controller controller, String username) {
        controller.moveMotherNature(this, username);
    }
}
