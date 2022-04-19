package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.exceptions.InvalidEntranceStudentException;
import it.polimi.ingsw.model.pawns.Student;
import java.util.*;

/**
 * school board's entrance
 */
public class Entrance {
    private final List<Student> students;

    /**
     * builds entrance
     */
    public Entrance(){
        students = new ArrayList<>();
    }

    /**
     * gets entrance students
     * @return entrance students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * adds a student to the entrance
     * @param student student to add
     */
    public void addStudent(Student student){
        students.add(student);
    }

    /**
     * removes a student from the Entrance
     * @param student student to remove
     */
    public void removeStudent(Student student){
        students.remove(student);
    }

    /**
     * checks student index validity
     * @param studentIndex student index
     * @throws InvalidEntranceStudentException invalid chosen student index
     */
    public void validStudentIndex(int studentIndex) throws InvalidEntranceStudentException {
        if(studentIndex >= students.size() || studentIndex < 0)
            throw new InvalidEntranceStudentException("Student not found");
    }
}
