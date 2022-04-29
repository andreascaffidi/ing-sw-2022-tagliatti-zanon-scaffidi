package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

public class PayCharacter8Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private int character;

    public PayCharacter8Message(){
        character = 8;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter8(this, username);
    }
}
