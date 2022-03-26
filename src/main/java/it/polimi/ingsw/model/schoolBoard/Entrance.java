package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.model.pawns.Student;
import java.util.*;

public class Entrance {
    private List<Student> students;

    public Entrance(){
        students = new ArrayList<Student>();
    }

    public List<Student> getStudents() {
        return students;
    }

    /**
     * adds a Student to the Entrance
     * @param student
     */

    public void addStudent(Student student){
        students.add(student);
    }

    /**
     * removes a Student from the Entrance
     * @param student
     */

    public void removeStudent(Student student){
        students.remove(student);
    }
}
