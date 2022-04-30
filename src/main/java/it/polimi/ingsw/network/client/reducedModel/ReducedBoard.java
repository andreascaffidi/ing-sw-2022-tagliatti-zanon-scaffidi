package it.polimi.ingsw.network.client.reducedModel;

import java.io.Serializable;
import java.util.List;

public class ReducedBoard implements Serializable {

    private String player;
    private String towerColor;

    private int yellowStudents;
    private int blueStudents;
    private int redStudents;
    private int pinkStudents;
    private int greenStudents;

    private List<String> entranceStudents;

    private List<String> professors;

    private int numOfTowers;

    private ReducedAssistantDeck assistantDeck;

    public ReducedBoard(String player, String towerColor, int yellowStudents, int blueStudents, int redStudents,
                        int pinkStudents, int greenStudents, List<String> entranceStudents, List<String> professors,
                        int numOfTowers, ReducedAssistantDeck assistantDeck) {
        this.player = player;
        this.towerColor = towerColor;
        this.yellowStudents = yellowStudents;
        this.blueStudents = blueStudents;
        this.redStudents = redStudents;
        this.pinkStudents = pinkStudents;
        this.greenStudents = greenStudents;
        this.entranceStudents = entranceStudents;
        this.professors = professors;
        this.numOfTowers = numOfTowers;
        this.assistantDeck = assistantDeck;
    }

    public String getPlayer() {
        return player;
    }

    public String getTowerColor() {
        return towerColor;
    }

    public int getYellowStudents() {
        return yellowStudents;
    }

    public int getBlueStudents() {
        return blueStudents;
    }

    public int getRedStudents() {
        return redStudents;
    }

    public int getPinkStudents() {
        return pinkStudents;
    }

    public int getGreenStudents() {
        return greenStudents;
    }

    public List<String> getEntranceStudents() {
        return entranceStudents;
    }

    public List<String> getProfessors() {
        return professors;
    }

    public int getNumOfTowers() {
        return numOfTowers;
    }

    public ReducedAssistantDeck getAssistantDeck() {
        return assistantDeck;
    }
}
