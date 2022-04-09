package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;

public class PayCharacter2Message implements RequestMessageExpertMode, ControllerExecuteExpertMode {

    private int character;

    public PayCharacter2Message(){
        character = 2;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter2(this, username);
    }
}
