package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

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
    public void execute(ControllerExpertMode controller, String username)
    {
        controller.payCharacter3(this, username);
    }
}
