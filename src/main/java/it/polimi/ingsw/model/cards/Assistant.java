package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.enums.Wizards;

public class Assistant {
    private int value;
    private int motherNatureMovements;
    private boolean played;
    private Wizards wizard;

    public Assistant(int value, int motherNatureMovements, Wizards wizard){
        this.value = value;
        this.motherNatureMovements = motherNatureMovements;
        this.played = false;
        this.wizard = wizard;
    }

    public int getValue() {
        return value;
    }

    public int getMotherNatureMovements() {
        return motherNatureMovements;
    }

    public boolean isPlayed() {
        return played;
    }

    public void playAssistant(){
        played = true;
    }

    public Wizards getWizard() {
        return wizard;
    }
}
