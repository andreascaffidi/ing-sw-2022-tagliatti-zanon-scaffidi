package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.TypeOfMessage;

public class PlayAssistantMessage implements TypeOfMessage{
    private int value;

    public PlayAssistantMessage(int value) {
        this.value = value;
    }

    @Override
    public void execute(Controller controller, String username) {
        controller.playAssistant(this,username);
    }

    public int getValue() {
        return value;
    }
}
