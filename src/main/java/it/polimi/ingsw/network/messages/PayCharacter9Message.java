package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter9Message implements CharactersMessage {

    private String color;

    public PayCharacter9Message(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter9(this, username);
    }
}
