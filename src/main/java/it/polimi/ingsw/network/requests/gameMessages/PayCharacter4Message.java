package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

public class PayCharacter4Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private int character;
    private int additionalMovement;

    public PayCharacter4Message(int additionalMovement) {
        this.character = 4;
        this.additionalMovement = additionalMovement;
    }

    public int getAdditionalMovement() {
        return additionalMovement;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter4(this, username);
    }
}
