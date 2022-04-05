package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter1Message implements CharactersMessage {

    private int islandId;
    private int studentId;

    public PayCharacter1Message(int islandId, int studentId) {
        this.islandId = islandId;
        this.studentId = studentId;
    }

    public int getIslandId() {
        return islandId;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username)
    {
        controller.payCharacter1(this, username);
    }
}
