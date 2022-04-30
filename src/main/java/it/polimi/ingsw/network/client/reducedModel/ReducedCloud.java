package it.polimi.ingsw.network.client.reducedModel;

import java.io.Serializable;
import java.util.List;

public class ReducedCloud implements Serializable {

    private int id;
    private List<String> students;

    public ReducedCloud(int id, List<String> students) {
        this.id = id;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public List<String> getStudents() {
        return students;
    }
}
