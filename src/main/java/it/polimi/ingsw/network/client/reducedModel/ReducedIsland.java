package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReducedIsland implements Serializable {

    private int id;
    private List<ColorS> students;
    private ColorT tower;
    private int numOfTowers;
    private boolean motherNature;

    public ReducedIsland(int id, List<ColorS> students, ColorT tower, int numOfTowers, boolean motherNature) {
        this.id = id;
        this.students = students;
        this.tower = tower;
        this.numOfTowers = numOfTowers;
        this.motherNature = motherNature;
    }

    public int getId() {
        return id;
    }

    public List<ColorS> getStudents() {
        return students;
    }

    public ColorT getTower() {
        return tower;
    }

    public int getNumOfTowers() {
        return numOfTowers;
    }

    public boolean isMotherNature()
    {
        return motherNature;
    }
}
