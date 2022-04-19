package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.ControllerExecute;
import it.polimi.ingsw.view.View;

public class PlayAssistantMessage implements RequestMessage, ControllerExecute {
    private int value;

    public PlayAssistantMessage(int value) {
        this.value = value;
    }

    @Override
    public void execute(Controller controller, String username, View view) {
        controller.playAssistant(this,username,view);
    }

    public int getValue() {
        return value;
    }
}
