package it.polimi.ingsw.model.islands;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;

import java.util.*;

public class Island {
    private int id;
    private boolean motherNature;
    private List<Student> students;
    private Tower tower;

    public Island(int id){
        this.id=id;
        this.motherNature=false;
        this.students=new ArrayList<Student>();
        this.tower=null;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public void setTower(Tower tower){
        this.tower=tower;
    }

    private int numStudent(ColorS color){
        return this.students.size();
    }

    public void setMotherNature(boolean motherNature) {
        this.motherNature = motherNature;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Tower getTower() {
        return tower;
    }
}
