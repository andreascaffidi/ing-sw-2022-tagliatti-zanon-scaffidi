package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;
import it.polimi.ingsw.view.View;

import java.io.Serializable;

public class PayCharacter3Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

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
    public void execute(ControllerExpertMode controller, String username, View view)
    {
        controller.payCharacter3(this, username, view);
    }
}
