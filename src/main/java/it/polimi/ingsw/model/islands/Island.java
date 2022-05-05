package it.polimi.ingsw.model.islands;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.network.client.reducedModel.ReducedIsland;

import java.util.*;
import java.util.stream.Collectors;

/**
 * class island
 */
public class Island {
    private int id;
    private boolean motherNature;
    private final List<Student> students;
    private Tower tower;
    private int numOfTowers;

    /**
     * builds island
     * @param id island id
     */
    public Island(int id){
        this.id=id;
        this.motherNature=false;
        this.students=new ArrayList<>();
        this.tower=null;
        this.numOfTowers = 1;
    }

    /**
     * gets island id
     * @return island id
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * adds a student to the island
     * @param student student to add
     */
    public void addStudent(Student student){
        this.students.add(student);
    }

    /**
     * adds a list of student to the island
     * @param students students to add
     */
    public void addStudents(List<Student> students){
        this.students.addAll(students);
    }

    /**
     * sets the island's tower
     * @param tower island tower
     */
    public void setTower(Tower tower){
        this.tower=tower;
    }

    /**
     * gets the number of students of a chosen color on the island
     * @param color color chosen
     * @return number of student of the chosen color
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

    /**
     * sets mother nature value
     * @param motherNature mother nature value
     */
    public void setMotherNature(boolean motherNature) {
        this.motherNature = motherNature;
    }

    /**
     * gets island students
     * @return island students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * gets island towers
     * @return island towers
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * checks if mother nature is on the island
     * @return true if mother nature is present, else false
     */
    public boolean isMotherNature() {
        return motherNature;
    }

    /**
     * sets island number of towers
     * @param numOfTowers island number of towers
     */
    public void setNumOfTowers(int numOfTowers) {
        this.numOfTowers = numOfTowers;
    }

    /**
     * changes island id when a new group of island is created
     * @param decrement new id offset
     */
    public void changeId(int decrement){
        this.id = this.id - decrement;
    }

    /**
     * gets island number of towers
     * @return island number of towers
     */
    public int getNumOfTowers() {
        return numOfTowers;
    }

    //FIXME: it's horrible
    /**
     * gets a reduced version of the island
     * @return reduced island
     */
    public ReducedIsland reduceIsland()
    {
        ColorT towerColor = null;
        if (this.getTower() != null){
            towerColor = this.getTower().getColor();
        }
        return new ReducedIsland(this.id, this.getStudents().stream().map(Student::getColor).collect(Collectors.toList()),
                towerColor, this.numOfTowers, this.motherNature);
    }
}
