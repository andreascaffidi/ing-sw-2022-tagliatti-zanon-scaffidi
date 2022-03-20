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
    private int numOfTowers;

    public Island(int id){
        this.id=id;
        this.motherNature=false;
        this.students=new ArrayList<Student>();
        this.tower=null;
        this.numOfTowers = 1;
    }

    /**
     * Adds a student to the island
     * @param student
     */
    public void addStudent(Student student){
        this.students.add(student);
    }

    /**
     * adds a list of students to the island
     * @param students
     */
    public void addStudents(List<Student> students){
        this.students.addAll(students);
    }

    public void setTower(Tower tower){
        this.tower=tower;
    }

    /**
     * Counts the number of students with a specific color on the island
     * @param color
     * @return number of student
     */
    public int numStudent(ColorS color){
        int count=0;
        for (Student s : students){
            if (s.getColor() == color){
                count++;
            }
        }
        return count;
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

    public boolean isMotherNature() {
        return motherNature;
    }

    public int getId() {
        return this.id;
    }

    public void setNumOfTowers(int numOfTowers) {
        this.numOfTowers = numOfTowers;
    }

    /**
     * change island id when a new group of island is created
     * @param decrement
     */
    public void changeId(int decrement){
        this.id = this.id - decrement;
    }

    public int getNumOfTowers() {
        return numOfTowers;
    }
}
