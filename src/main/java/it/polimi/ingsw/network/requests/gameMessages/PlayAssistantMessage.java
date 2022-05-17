package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

/**
 * game message to play assistant card
 */
public class PlayAssistantMessage implements RequestMessage, ControllerExecute {
    private final int value;

    /**
     * builds play assistant message
     * @param value assistant card's value
     */
    public PlayAssistantMessage(int value) {
        this.value = value;
    }

    /**
     * executes controller method play assistant
     * @param controller game controller
     */
    @Override
    public void execute(Controller controller) {
        controller.playAssistant(this);
    }

    /**
     * gets assistant card's value
     * @return assistant card's value
     */
    public int getValue() {
        return value;
    }
}
