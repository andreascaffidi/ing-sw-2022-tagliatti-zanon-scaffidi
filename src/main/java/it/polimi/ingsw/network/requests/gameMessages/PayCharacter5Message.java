package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message to pay character 5
 */
public class PayCharacter5Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final int islandId;

    /**
     * builds pay character 5 message
     * @param islandId island id
     */
    public PayCharacter5Message(int islandId) {
        this.character = 5;
        this.islandId = islandId;
    }

    /**
     * gets island id
     * @return island id
     */
    public int getIslandId() {
        return islandId;
    }

    /**
     * gets character id
     * @return character id
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 5
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter5(this);
    }
}
