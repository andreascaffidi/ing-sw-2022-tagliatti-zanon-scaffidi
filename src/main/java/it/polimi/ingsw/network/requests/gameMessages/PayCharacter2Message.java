package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

/**
 * game message pay character 2
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
     * gets character 2
     * @return character 2
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 2
     * @param controller game controller
     * @param username of the player that pays character 2
     */
    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter2(this, username);
    }
}
