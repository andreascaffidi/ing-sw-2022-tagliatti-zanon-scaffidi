package it.polimi.ingsw.model;
import it.polimi.ingsw.model.pawns.Student;

import java.util.*;

/**
 * bag
 */
public class Bag {

    private final List<Student> students;

    /**
     * builds the bag
     */
    public Bag() {
        this.students = new ArrayList<>();
    }

    /**
     * gets the students in the bag
     * @return the students in the bag
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * adds a student to the bag
     * @param student student to add
     */
    public void addStudent(Student student){
        this.students.add(student);

        //then I shuffle them
        Collections.shuffle(students);
    }

    /**
     * adds a list of student to the bag and then shuffles the bag
     * @param student student to add
     */
    public void addStudents(List<Student> student){
        this.students.addAll(student);

        //then I shuffle them
        Collections.shuffle(students);
    }

    /**
     * removes a student from the bag
     * @return Student removed student
     */
    public Student drawStudent(){
        return this.students.remove(0);
    }
}
