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

    /**
     * builds a reduced character card
     * @param id character's id
     * @param cost character card's cost
     * @param students students on the card
     */
    public ReducedCharacter(int id, int cost, List<ColorS> students) {
        this.id = id;
        this.cost = cost;
        this.students = students;
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
     * Return a list of students of a chosen color
     * @param color The color
     * @return list of students of the chosen color
     */
    public List<ColorS> getStudents(ColorS color) {
        if(students == null) return  new ArrayList<>();
        return students.stream().filter(i -> i.equals(color)).collect(Collectors.toList());
    }
}
