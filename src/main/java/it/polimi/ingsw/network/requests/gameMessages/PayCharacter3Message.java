package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message pay character 3
 */
public class PayCharacter3Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final int islandId;

    /**
     * builds pay character 3 message
     * @param islandId island id
     */
    public PayCharacter3Message(int islandId) {
        this.character = 3;
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
     * gets character 3
     * @return character 3
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 3
     * @param controller game controller
     * @param username of the player that pays character 3
     */
    @Override
    public void execute(ControllerExpertMode controller, String username)
    {
        controller.payCharacter3(this, username);
    }
}
