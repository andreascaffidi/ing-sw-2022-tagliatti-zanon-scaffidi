package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;

public class PayCharacter8Message implements RequestMessageExpertMode, ControllerExecuteExpertMode {

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
