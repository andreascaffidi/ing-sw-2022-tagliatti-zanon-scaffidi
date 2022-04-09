package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;

public class PayCharacter11Message implements RequestMessageExpertMode, ControllerExecuteExpertMode {

    private int character;
    private int studentId;

    public PayCharacter11Message(int studentId) {
        this.character = 11;
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter11(this, username);
    }
}
