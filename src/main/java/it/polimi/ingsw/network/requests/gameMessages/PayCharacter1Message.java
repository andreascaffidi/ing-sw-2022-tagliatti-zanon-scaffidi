package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message pay character 1
 */
public class PayCharacter1Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final int islandId;
    private final int studentId;

    /**
     * builds pay character 1 message
     * @param islandId island id
     * @param studentId student id
     */
    public PayCharacter1Message(int islandId, int studentId) {
        this.character = 1;
        this.islandId = islandId;
        this.studentId = studentId;
    }

    /**
     * gets island
     * @return island
     */
    public int getIslandId() {
        return islandId;
    }

    /**
     * gets student id
     * @return student id
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * gets character 1
     * @return character 1
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 1
     * @param controller game controller
     * @param username of the player that pays character 1
     */
    @Override
    public void execute(ControllerExpertMode controller, String username)
    {
        controller.payCharacter1(this, username);
    }
}
