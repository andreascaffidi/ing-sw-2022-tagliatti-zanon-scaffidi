package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.ColorS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * reduced serializable version of a character card
 */
public class ReducedCharacter implements Serializable {
    private final int id;
    private final int cost;
    private final List<ColorS> students;
    private final String name;

    /**
     * builds a reduced character card
     * @param id character's id
     * @param cost character card's cost
     * @param students students on the card
     * @param name name of the character
     */
    public ReducedCharacter(int id, int cost, List<ColorS> students, String name) {
        this.id = id;
        this.cost = cost;
        this.students = students;
        this.name = name;
    }

    /**
     * gets character card's id
     * @return character card's id
     */
    public int getId(){
        return this.id;
    }

    /**
     * gets character card's cost
     * @return character card's cost
     */
    public int getCost()
    {
        return this.cost;
    }

    /**
     * gets students on the card
     * @return students on the card
     */
    public List<ColorS> getStudents()
    {
        return this.students;
    }


    /**
     * gets a list of students of a chosen color
     * @param color color chosen
     * @return students of the chosen color
     */
    public List<ColorS> getStudents(ColorS color) {
        if(students == null) return  new ArrayList<>();
        return students.stream().filter(i -> i.equals(color)).collect(Collectors.toList());
    }

    /**
     * gets the name of the character
     * @return name of the character
     */
    public String getName(){
        return name;
    }
}
