package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.ColorS;

import java.io.Serializable;
import java.util.List;

public class ReducedCharacter implements Serializable {
    private int id;
    private int cost;
    private List<ColorS> students;

    public ReducedCharacter(int id, int cost, List<ColorS> students)
    {
        this.id = id;
        this.cost = cost;
        this.students = students;
    }

    public int getId(){
        return this.id;
    }

    public int getCost()
    {
        return this.cost;
    }

    public List<ColorS> getStudents()
    {
        return this.students;
    }
}
