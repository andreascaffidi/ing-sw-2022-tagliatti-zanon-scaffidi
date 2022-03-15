package it.polimi.ingsw.model;
import it.polimi.ingsw.model.pawns.Student;

import java.util.*;

public class Cloud {
    private List<Student> students;

    public Cloud(){
        this.students = new ArrayList<Student>();
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public List<Student> takeAllStudents(){
        List<Student> clonedList =  new ArrayList<Student>(this.students);
        this.students = new ArrayList<Student>();
        return clonedList;
    }
}
