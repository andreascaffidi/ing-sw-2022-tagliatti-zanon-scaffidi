package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message to pay character 6
 */
public class PayCharacter6Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final int islandId;

    /**
     * builds pay character 6 message
     * @param islandId island id
     */
    public PayCharacter6Message(int islandId) {
        this.character = 6;
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
     * executes controller method pay character 6
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter6(this);
    }
}
