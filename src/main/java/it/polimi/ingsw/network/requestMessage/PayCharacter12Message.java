package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;
import it.polimi.ingsw.view.View;

import java.io.Serializable;

public class PayCharacter12Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private int character;
    private String color;

    public PayCharacter12Message(String color) {
        this.character = 12;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getCharacter() {
        return character;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username, View view) {
        controller.payCharacter12(this, username, view);
    }
}
