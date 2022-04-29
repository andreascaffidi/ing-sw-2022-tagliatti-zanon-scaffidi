package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

public class PlayAssistantMessage implements RequestMessage, ControllerExecute {
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
