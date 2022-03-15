package it.polimi.ingsw.model.cards;

public class Assistant {
    private int value;
    private int motherNatureMovements;
    private boolean played;
    private int id;

    public Assistant(int value, int motherNatureMovements, int id){
        this.value = value;
        this.motherNatureMovements = motherNatureMovements;
        this.played = false;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void playAssistant(){
        played = true;
    }
}
