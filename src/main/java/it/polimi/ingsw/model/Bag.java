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


    public List<Student> getStudents()
    {
        return this.students;
    }

    /** Add a student to the bag
     *
     * @param student the student to add
     */
    public void addStudent(Student student){
        this.students.add(student);

        //then I shuffle them
        Collections.shuffle(students);
    }

    /** Add a list of  students to the bag and then shuffle the,
     *
     * @param student the student to add
     */
    public void addStudents(List<Student> student){
        this.students.addAll(student);

        //then I shuffle them
        Collections.shuffle(students);
    }

    /**
     * Return and removes a studnet from the bag
     * @return Student
     */
    public Student drawStudent(){
        return this.students.remove(0);
    }
}
