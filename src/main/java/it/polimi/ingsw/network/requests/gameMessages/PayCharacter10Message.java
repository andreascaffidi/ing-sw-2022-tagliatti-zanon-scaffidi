package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.io.Serializable;
import java.util.*;

/**
 * game message to pay character 10
 */
public class PayCharacter10Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private final int character;
    private final List<ColorS> diningStudents;
    private final List<Integer>  entranceStudents;

    //TODO: implementare sulla view un metodo che verifichi che le due liste siano della stessa lunghezza
    // e che non si ripetano gli indici della stessa lista
    /**
     * builds pay character 10 message
     * @param diningStudents dining student colors
     * @param entranceStudents entrance student IDs
     */
    public PayCharacter10Message(List<ColorS> diningStudents, List<Integer> entranceStudents) {
        this.character = 10;
        this.diningStudents = diningStudents;
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
     * gets a list of dining student colors
     * @return list of dining student colors
     */
    public List<ColorS> getDiningStudents() {
        return diningStudents;
    }

    /**
     * gets a list of entrance student IDs
     * @return list of entrance student IDs
     */
    public List<Integer> getEntranceStudents() {
        return entranceStudents;
    }

    /**
     * executes controller method pay character 10
     * @param controller game controller
     */
    @Override
    public void execute(ControllerExpertMode controller) {
        controller.payCharacter10(this);
    }
}
