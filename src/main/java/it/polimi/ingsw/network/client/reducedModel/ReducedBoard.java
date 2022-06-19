package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * reduced serializable version of player's board
 */
public class ReducedBoard implements Serializable {

    private final String player;
    private final int tagTeam;
    private final ColorT towerColor;

    private final Map <ColorS,Integer> students;

    private final List<ColorS> entranceStudents;

    private final List<ColorS> professors;

    private final int numOfTowers;

    private final ReducedAssistantDeck assistantDeck;

    /**
     * builds a reduced board
     * @param player player's username
     * @param tagTeam player's team
     * @param towerColor tower's color
     * @param students students on the dining room
     * @param entranceStudents colors of entrance students
     * @param professors colors of professors on the board
     * @param numOfTowers number of towers on the board
     * @param assistantDeck reduced assistant deck
     */
    public ReducedBoard(String player, int tagTeam, ColorT towerColor, Map <ColorS,Integer> students, List<ColorS> entranceStudents, List<ColorS> professors,
                        int numOfTowers, ReducedAssistantDeck assistantDeck) {
        this.player = player;
        this.tagTeam = tagTeam;
        this.towerColor = towerColor;
        this.students = students;
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
     * gets player's tag team
     * @return player's tag team
     */
    public int getTagTeam() {
        return tagTeam;
    }

    /**
     * gets tower's color
     * @return tower's color
     */
    public ColorT getTowerColor() {
        return towerColor;
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

    /**
     * gets the students on the dining room
     * @return number of student on the dining room per color
     */
    public Map <ColorS,Integer> getStudents(){
        return students;
    }
}
