package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter12Message implements CharactersMessage {

    private String color;

    public PayCharacter12Message(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter12(this, username);
    }
}
