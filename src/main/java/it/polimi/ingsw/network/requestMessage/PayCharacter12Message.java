package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;

public class PayCharacter12Message implements RequestMessageExpertMode, ControllerExecuteExpertMode {

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
