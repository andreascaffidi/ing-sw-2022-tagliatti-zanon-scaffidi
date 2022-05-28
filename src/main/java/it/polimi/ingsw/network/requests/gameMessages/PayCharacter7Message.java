package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;
import java.util.*;

/**
 * game message to pay character 7
 */
public class PayCharacter7Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final List<Integer>  cardStudents;
    private final List<Integer>  entranceStudents;

    /**
     * builds pay character 7 message
     * @param cardStudents card student IDs
     * @param entranceStudents entrance student IDs
     */
    public PayCharacter7Message(List<Integer> cardStudents, List<Integer> entranceStudents) {
        this.character = 7;
        this.cardStudents = cardStudents;
        this.entranceStudents = entranceStudents;
    }

    /**
     * gets character id
     * @return character id
     */
    public int getCharacter() {
        return character;
    }

    /**
     * gets a list of card student IDs
     * @return list of card student IDs
     */
    public List<Integer> getCardStudents() {
        return cardStudents;
    }

    /**
     * gets a list of entrance student IDs
     * @return list of entrance student IDs
     */
    public List<Integer> getEntranceStudents() {
        return entranceStudents;
    }

    /**
     * executes controller method pay character 7
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter7(this);
    }
}
