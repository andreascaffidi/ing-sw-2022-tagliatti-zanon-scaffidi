package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter4Message implements CharactersMessage {

    private int additionalMovement;

    public PayCharacter4Message(int additionalMovement) {
        this.additionalMovement = additionalMovement;
    }

    public int getAdditionalMovement() {
        return additionalMovement;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter4(this, username);
    }
}
