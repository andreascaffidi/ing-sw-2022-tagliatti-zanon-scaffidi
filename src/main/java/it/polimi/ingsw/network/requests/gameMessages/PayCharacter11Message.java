package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message to pay character 11
 */
public class PayCharacter11Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final int studentId;

    /**
     * builds pay character 11 message
     * @param studentId student id
     */
    public PayCharacter11Message(int studentId) {
        this.character = 11;
        this.studentId = studentId;
    }

    /**
     * gets student id
     * @return student id
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * gets character id
     * @return character id
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 11
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter11(this);
    }
}
