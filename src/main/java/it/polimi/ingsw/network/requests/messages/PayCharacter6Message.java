package it.polimi.ingsw.network.requests.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.view.View;

import java.io.Serializable;

public class PayCharacter6Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private int character;
    private int islandId;

    public PayCharacter6Message(int islandId) {
        this.character = 6;
        this.islandId = islandId;
    }

    public int getIslandId() {
        return islandId;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter6(this, username);
    }
}