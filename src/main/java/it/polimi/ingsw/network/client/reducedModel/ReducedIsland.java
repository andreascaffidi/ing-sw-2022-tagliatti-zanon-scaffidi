package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * reduced serializable version of an island
 */
public class ReducedIsland implements Serializable {

    private final int id;
    private final List<ColorS> students;
    private final ColorT tower;
    private final int numOfTowers;
    private final boolean motherNature;

    /**
     * builds a reduced island
     * @param id island id
     * @param students students on the island
     * @param tower tower's color on the island
     * @param numOfTowers number of towers on the island
     * @param motherNature presence of mother nature on the island
     */
    public ReducedIsland(int id, List<ColorS> students, ColorT tower, int numOfTowers, boolean motherNature) {
        this.id = id;
        this.students = students;
        this.tower = tower;
        this.numOfTowers = numOfTowers;
        this.motherNature = motherNature;
    }

    /**
     * gets island id
     * @return island id
     */
    public int getId() {
        return id;
    }

    /**
     * gets students on the island
     * @return students on the island
     */
    public List<ColorS> getStudents() {
        return students;
    }

    public List<ColorS> getStudents(ColorS color) {
        return students.stream().filter(i -> i.equals(color)).collect(Collectors.toList());
    }

    /**
     * gets the tower's color on the island
     * @return tower's color on the island
     */
    public ColorT getTower() {
        return tower;
    }

    /**
     * gets the number of towers on the island
     * @return number of towers on the island
     */
    public int getNumOfTowers() {
        return numOfTowers;
    }

    /**
     * checks if there is mother nature on the island
     * @return mother nature presence
     */
    public boolean isMotherNature()
    {
        return motherNature;
    }
}
