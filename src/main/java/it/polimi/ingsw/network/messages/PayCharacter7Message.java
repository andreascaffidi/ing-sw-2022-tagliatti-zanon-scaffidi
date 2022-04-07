package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter7Message implements CharactersMessage {

    private int cardStudent1;
    private int cardStudent2;
    private int cardStudent3;

    private int entranceStudent1;
    private int entranceStudent2;
    private int entranceStudent3;

    public PayCharacter7Message(int cardStudent1, int entranceStudent1) {
        this.cardStudent1 = cardStudent1;

        this.entranceStudent1 = entranceStudent1;
    }

    public PayCharacter7Message(int cardStudent1, int cardStudent2, int entranceStudent1, int entranceStudent2) {
        this.cardStudent1 = cardStudent1;
        this.cardStudent2 = cardStudent2;

        this.entranceStudent1 = entranceStudent1;
        this.entranceStudent2 = entranceStudent2;
    }

    public PayCharacter7Message(int cardStudent1, int cardStudent2, int cardStudent3, int entranceStudent1, int entranceStudent2, int entranceStudent3) {
        this.cardStudent1 = cardStudent1;
        this.cardStudent2 = cardStudent2;
        this.cardStudent3 = cardStudent3;

        this.entranceStudent1 = entranceStudent1;
        this.entranceStudent2 = entranceStudent2;
        this.entranceStudent3 = entranceStudent3;
    }

    public int getCardStudent1() {
        return cardStudent1;
    }

    public int getCardStudent2() {
        return cardStudent2;
    }

    public int getCardStudent3() {
        return cardStudent3;
    }

    public int getEntranceStudent1() {
        return entranceStudent1;
    }

    public int getEntranceStudent2() {
        return entranceStudent2;
    }

    public int getEntranceStudent3() {
        return entranceStudent3;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter7(this, username);
    }
}
