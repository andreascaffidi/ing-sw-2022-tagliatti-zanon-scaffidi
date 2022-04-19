package it.polimi.ingsw.network.requests.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.view.View;

import java.io.Serializable;

public class PayCharacter11Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

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
    public void execute(ControllerExpertMode controller, String username, View view) {
        controller.payCharacter11(this, username, view);
    }
}
