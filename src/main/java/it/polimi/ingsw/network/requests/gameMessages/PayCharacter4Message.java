package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message to pay character 4
 */
public class PayCharacter4Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final int additionalMovement;

    /**
     * builds pay character 4 message
     * @param additionalMovement additional mother nature movements
     */
    public PayCharacter4Message(int additionalMovement) {
        this.character = 4;
        this.additionalMovement = additionalMovement;
    }

    /**
     * gets mother nature additional movements
     * @return mother nature additional movements
     */
    public int getAdditionalMovement() {
        return additionalMovement;
    }

    /**
     * gets character id
     * @return character id
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 4
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter4(this);
    }
}
