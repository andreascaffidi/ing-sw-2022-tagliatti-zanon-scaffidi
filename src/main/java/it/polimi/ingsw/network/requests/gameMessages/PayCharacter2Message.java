package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

public class PayCharacter2Message implements RequestMessage, ControllerExecuteExpertMode {

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
