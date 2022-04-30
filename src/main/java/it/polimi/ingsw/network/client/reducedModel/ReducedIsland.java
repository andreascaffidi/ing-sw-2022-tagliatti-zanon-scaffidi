package it.polimi.ingsw.network.client.reducedModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReducedIsland implements Serializable {

    private int id;
    private List<String> students;
    private String tower;
    private int numOfTowers;
    private boolean motherNature;

    public ReducedIsland(int id, List<String> students, String tower, int numOfTowers, boolean motherNature) {
        this.id = id;
        this.students = students;
        this.tower = tower;
        this.numOfTowers = numOfTowers;
        this.motherNature = motherNature;
    }

    public int getId() {
        return id;
    }

    public List<String> getStudents() {
        return students;
    }

    public String getTower() {
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
