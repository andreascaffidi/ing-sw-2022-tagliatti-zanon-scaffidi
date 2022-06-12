package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.ColorS;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * reduced serializable version of a cloud
 */
public class ReducedCloud implements Serializable {

    private final int id;
    private final List<ColorS> students;

    /**
     * builds a reduced cloud
     * @param id cloud id
     * @param students colors of students on the cloud
     */
    public ReducedCloud(int id, List<ColorS> students) {
        this.id = id;
        this.students = students;
    }

    /**
     * gets cloud id
     * @return cloud id
     */
    public int getId() {
        return id;
    }

    /**
     * gets students on cloud
     * @return students on cloud
     */
    public List<ColorS> getStudents() {
        return students;
    }

    /**
     * gets a list of students of a chosen color
     * @param color color chosen
     * @return students of the chosen color
     */
    public List<ColorS> getStudents(ColorS color) {
        return students.stream().filter(i -> i.equals(color)).collect(Collectors.toList());
    }
}
