package it.polimi.ingsw.network.requests.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.view.View;

import java.io.Serializable;
import java.util.*;

public class PayCharacter10Message implements RequestMessage, ControllerExecuteExpertMode, Serializable {

    private int character;
    private List<String> diningStudents;
    private List<Integer>  entranceStudents;

    //TODO: implementare sulla view un metodo che verifichi che le due liste siano della stessa lunghezza
    // e che non si ripetano gli indici della stessa lista
    public PayCharacter10Message(List<String> diningStudents, List<Integer> entranceStudents) {
        this.character = 10;
        this.diningStudents = diningStudents;
        this.entranceStudents = entranceStudents;
    }

    public int getCharacter() {
        return character;
    }

    public List<String> getDiningStudents() {
        return diningStudents;
    }

    public List<Integer> getEntranceStudents() {
        return entranceStudents;
    }


    @Override
    public void execute(ControllerExpertMode controller, String username, View view) {
        controller.payCharacter10(this, username, view);
    }
}
