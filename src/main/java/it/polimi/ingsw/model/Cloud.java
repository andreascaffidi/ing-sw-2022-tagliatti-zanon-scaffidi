package it.polimi.ingsw.model;
import it.polimi.ingsw.exceptions.CloudNotValidException;
import it.polimi.ingsw.model.pawns.Student;

import java.util.*;

/**
 * cloud
 */
public class Cloud {

    private List<Student> students;

    /**
     * builds the cloud
     */
    public Cloud(){
        this.students = new ArrayList<Student>();
    }

    /**
     * adds a student to the cloud
     * @param student student to add
     */
    public void addStudent(Student student){
        this.students.add(student);
    }

    /**
     * returns all students from the cloud
     * @return all students from the cloud
     */
    public List<Student> takeAllStudents(){
        List<Student> clonedList =  new ArrayList<Student>(this.students);
        this.students = new ArrayList<Student>();
        return clonedList;
    }

    /**
     * gets
     * @return
     */
    public List<Student> getStudents() {
        return this.students;
    }


}
