package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.ColorS;

import java.io.Serializable;
import java.util.List;

public class ReducedCloud implements Serializable {

    private int id;
    private List<ColorS> students;

    public ReducedCloud(int id, List<ColorS> students) {
        this.id = id;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public List<ColorS> getStudents() {
        return students;
    }
}
