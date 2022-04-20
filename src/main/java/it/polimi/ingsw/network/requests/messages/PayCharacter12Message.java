package it.polimi.ingsw.network.requests.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;
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
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter12(this, username);
    }
}
