package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;
import it.polimi.ingsw.view.View;

public class PayCharacter2Message implements RequestMessage, ControllerExecuteExpertMode {

    private int character;

    public PayCharacter2Message(){
        character = 2;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username, View view) {
        controller.payCharacter2(this, username, view);
    }
}
