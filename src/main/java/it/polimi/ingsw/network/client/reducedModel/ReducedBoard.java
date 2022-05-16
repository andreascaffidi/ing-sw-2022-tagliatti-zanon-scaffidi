package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;

import java.io.Serializable;
import java.util.List;

/**
 * reduced serializable version of player's board
 */
public class ReducedBoard implements Serializable {

    private final String player;
    private final ColorT towerColor;

    private final int yellowStudents;
    private final int blueStudents;
    private final int redStudents;
    private final int pinkStudents;
    private final int greenStudents;

    private final List<ColorS> entranceStudents;

    private final List<ColorS> professors;

    private final int numOfTowers;

    private final ReducedAssistantDeck assistantDeck;

    /**
     * builds a reduced board
     * @param player player's username
     * @param towerColor tower's color
     * @param yellowStudents number of yellow students
     * @param blueStudents number of blue students
     * @param redStudents number of red students
     * @param pinkStudents number of pink students
     * @param greenStudents number of green students
     * @param entranceStudents colors of entrance students
     * @param professors colors of professors on the board
     * @param numOfTowers number of towers on the board
     * @param assistantDeck reduced assistant deck
     */
    public ReducedBoard(String player, ColorT towerColor, int yellowStudents, int blueStudents, int redStudents,
                        int pinkStudents, int greenStudents, List<ColorS> entranceStudents, List<ColorS> professors,
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

    /**
     * gets player's username
     * @return player's username
     */
    public String getPlayer() {
        return player;
    }

    /**
     * gets tower's color
     * @return tower's color
     */
    public ColorT getTowerColor() {
        return towerColor;
    }

    /**
     * gets number of yellow students
     * @return number of yellow students
     */
    public int getYellowStudents() {
        return yellowStudents;
    }

    /**
     * gets number of blue students
     * @return number of blue students
     */
    public int getBlueStudents() {
        return blueStudents;
    }

    /**
     * gets number of red students
     * @return number of red students
     */
    public int getRedStudents() {
        return redStudents;
    }

    /**
     * gets number of pink students
     * @return number of pink students
     */
    public int getPinkStudents() {
        return pinkStudents;
    }

    /**
     * gets number of green students
     * @return number of green students
     */
    public int getGreenStudents() {
        return greenStudents;
    }

    /**
     * gets colors of entrance students
     * @return colors of entrance students
     */
    public List<ColorS> getEntranceStudents() {
        return entranceStudents;
    }

    /**
     * gets colors of professors on the board
     * @return colors of professors on the board
     */
    public List<ColorS> getProfessors() {
        return professors;
    }

    /**
     * gets number of towers on the board
     * @return number of towers on the board
     */
    public int getNumOfTowers() {
        return numOfTowers;
    }

    /**
     * gets reduced assistant deck
     * @return reduced assistant deck
     */
    public ReducedAssistantDeck getAssistantDeck() {
        return assistantDeck;
    }
}
