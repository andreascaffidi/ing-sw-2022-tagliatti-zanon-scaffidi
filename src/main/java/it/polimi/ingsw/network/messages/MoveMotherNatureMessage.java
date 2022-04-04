package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.TypeOfMessage;

public class MoveMotherNatureMessage implements TypeOfMessage {
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
