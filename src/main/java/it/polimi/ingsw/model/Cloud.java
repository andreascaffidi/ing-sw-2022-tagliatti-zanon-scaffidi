package it.polimi.ingsw.model;
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
        this.students = new ArrayList<>();
    }

    /**
     * adds a student to the cloud
     * @param student student to add
     */
    public void addStudent(Student student){
        this.students.add(student);
    }

    /**
     * returns all students on the cloud
     * @return all students on the cloud
     */
    public List<Student> takeAllStudents(){
        List<Student> clonedList =  new ArrayList<>(this.students);
        this.students = new ArrayList<>();
        return clonedList;
    }

    /**
     * gets students on the cloud
     * @return students on the cloud
     */
    public List<Student> getStudents() {
        return this.students;
    }
}