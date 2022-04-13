package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;

import java.io.Serializable;

public class PayCharacter9Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private int character;
    private String color;

    public PayCharacter9Message(String color) {
        this.character = 9;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter9(this, username);
    }
}
