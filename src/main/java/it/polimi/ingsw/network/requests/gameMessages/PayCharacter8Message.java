package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message to pay character 8
 */
public class PayCharacter8Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;

    /**
     * builds pay character 8 message
     */
    public PayCharacter8Message(){
        character = 8;
    }

    /**
     * gets character id
     * @return character id
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 8
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter8(this);
    }
}
