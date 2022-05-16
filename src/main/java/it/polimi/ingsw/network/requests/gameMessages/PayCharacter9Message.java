package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message to pay character 9
 */
public class PayCharacter9Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final ColorS color;

    /**
     * builds pay character 9 message
     * @param color student color
     */
    public PayCharacter9Message(ColorS color) {
        this.character = 9;
        this.color = color;
    }

    /**
     * gets student color
     * @return student color
     */
    public ColorS getColor() {
        return color;
    }

    /**
     * gets character id
     * @return character id
     */
    public int getCharacter() {
        return character;
    }

    /**
     * executes controller method pay character 9
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter9(this);
    }
}
