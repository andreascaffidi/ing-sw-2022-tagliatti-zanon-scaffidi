package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter11Message implements CharactersMessage {

    private int studentId;

    public PayCharacter11Message(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter11(this, username);
    }
}
