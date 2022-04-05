package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter10Message implements CharactersMessage {

    private int diningStudent1;
    private int diningStudent2;

    private int entranceStudent1;
    private int entranceStudent2;

    public PayCharacter10Message(int diningStudent1, int entranceStudent1) {
        this.diningStudent1 = diningStudent1;

        this.entranceStudent1 = entranceStudent1;
    }

    public PayCharacter10Message(int diningStudent1, int diningStudent2, int entranceStudent1, int entranceStudent2) {
        this.diningStudent1 = diningStudent1;
        this.diningStudent2 = diningStudent2;

        this.entranceStudent1 = entranceStudent1;
        this.entranceStudent2 = entranceStudent2;
    }

    public int getDiningStudent1() {
        return diningStudent1;
    }

    public int getDiningStudent2() {
        return diningStudent2;
    }


    public int getEntranceStudent1() {
        return entranceStudent1;
    }

    public int getEntranceStudent2() {
        return entranceStudent2;
    }


    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter10(this, username);
    }
}
