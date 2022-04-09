package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;

public class PayCharacter3Message implements RequestMessageExpertMode, ControllerExecuteExpertMode {

    private int character;
    private int islandId;

    public PayCharacter3Message(int islandId) {
        this.character = 3;
        this.islandId = islandId;
    }

    public int getIslandId() {
        return islandId;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username)
    {
        controller.payCharacter3(this, username);
    }
}
