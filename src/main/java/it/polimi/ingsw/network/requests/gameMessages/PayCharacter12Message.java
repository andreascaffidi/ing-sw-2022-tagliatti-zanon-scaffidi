package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;

/**
 * game message to pay character 12
 */
public class PayCharacter12Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final ColorS color;

    /**
     * builds pay character 12 message
     * @param color student color
     */
    public PayCharacter12Message(ColorS color) {
        this.character = 12;
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
     * executes controller method pay character 12
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter12(this);
    }
}
