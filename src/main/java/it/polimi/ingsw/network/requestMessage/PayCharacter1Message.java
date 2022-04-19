package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;
import it.polimi.ingsw.view.View;

import java.io.Serializable;

public class PayCharacter1Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private int character;
    private int islandId;
    private int studentId;

    public PayCharacter1Message(int islandId, int studentId) {
        this.character = 1;
        this.islandId = islandId;
        this.studentId = studentId;
    }

    public int getIslandId() {
        return islandId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username, View view)
    {
        controller.payCharacter1(this, username, view);
    }
}