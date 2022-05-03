package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

/**
 * game message move mother nature
 */
public class MoveMotherNatureMessage implements RequestMessage, ControllerExecute {
    private final int movements;

    /**
     * builds move mother nature message
     * @param movements mother nature movements
     */
    public MoveMotherNatureMessage(int movements) {
        this.movements = movements;
    }

    /**
     * gets mother nature movements
     * @return mother nature movements
     */
    public int getMovements() {
        return movements;
    }

    /**
     * executes controller method move mother nature
     * @param controller game controller
     * @param username of the player that moves mother nature
     */
    @Override
    public void execute(Controller controller, String username) {
        controller.moveMotherNature(this, username);
    }
}
