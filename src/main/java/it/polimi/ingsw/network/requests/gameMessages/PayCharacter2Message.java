package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

/**
 * game message to pay character 2
 */
public class PayCharacter2Message implements RequestMessage, ControllerExecuteExpertMode {

    private final int character;

    /**
     * builds pay character 2 message
     */
    public PayCharacter2Message(){
        character = 2;
    }

    /**
     * gets character id
     * @return character id
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 2
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter2(this);
    }
}
