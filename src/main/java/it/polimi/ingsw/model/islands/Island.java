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

    /**
     * Adds a student to the island
     * @param student
     */
    public void addStudent(Student student){
        this.students.add(student);
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

    /**
     * Counts the number of towers on the island, this method is useful for table.getSupremacy() method because a group of
     * islands has more than one tower.
     */
    public int numOfTowers(){
        if (tower == null){
            return 0;
        }
        else{
            return 1;
        }
    }

}
