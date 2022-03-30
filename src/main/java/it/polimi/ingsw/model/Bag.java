package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

import java.util.*;

import static it.polimi.ingsw.model.Table.NUM_OF_STUDENTS_PER_COLOR;

public class Bag {
    private List<Student> students;


    public Bag() {
        this.students = new ArrayList<Student>();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents()
    {
        return this.students;
    }

    /** adds a student to the bag
     *
     * @param student the student to add
     */
    public void addStudent(Student student){
        this.students.add(student);

        //then I shuffle them
        Collections.shuffle(students);
    }

    /** adds a list of Student to the bag and then shuffles the Collection of Students,
     *
     * @param student the student to add
     */
    public void addStudents(List<Student> student){
        this.students.addAll(student);

        //then I shuffle them
        Collections.shuffle(students);
    }

    /**
     * returns and removes a student from the bag
     * @return Student
     */
    public Student drawStudent(){
        return this.students.remove(0);
    }
}
