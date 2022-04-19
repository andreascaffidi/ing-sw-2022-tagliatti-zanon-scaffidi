package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.ControllerExecuteExpertMode;
import it.polimi.ingsw.view.View;

import java.io.Serializable;
import java.util.*;

public class PayCharacter7Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private int character;
    private List<Integer>  cardStudents;
    private List<Integer>  entranceStudents;

    //TODO: implementare sulla view un metodo che verifichi che le due liste siano della stessa lunghezza
    // e che non si ripetano gli indici della stessa lista
    public PayCharacter7Message(List<Integer> cardStudents, List<Integer> entranceStudents) {
        this.character = 7;
        this.cardStudents = cardStudents;
        this.entranceStudents = entranceStudents;
    }

    public int getCharacter() {
        return character;
    }

    public List<Integer> getCardStudents() {
        return cardStudents;
    }

    public List<Integer> getEntranceStudents() {
        return entranceStudents;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username, View view) {
        controller.payCharacter7(this, username, view);
    }
}
