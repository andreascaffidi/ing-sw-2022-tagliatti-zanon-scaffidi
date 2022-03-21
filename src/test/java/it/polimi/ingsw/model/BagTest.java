package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    Bag bag = new Bag();

    @org.junit.jupiter.api.Test
    void addStudent() {
        Bag bag = new Bag();
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.RED);
        bag.addStudent(student1);
        bag.addStudent(student2);
        for(Student s: bag.getStudents())
        {
            assertEquals()
        }
        assertEquals(student1, bag.getStudents().get(0));
        assertEquals(student2, bag.getStudents().get(1));
    }

    @org.junit.jupiter.api.Test
    void addStudents() {
        Bag bag = new Bag();
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.RED);
        List<Student> students = {student1, student2};
        bag.addStudents(Arrays.asList(students));
        assertEquals(student1, bag.getStudents().get(0));
        assertEquals(student2, bag.getStudents().get(1));
     }

    @org.junit.jupiter.api.Test
    void drawStudent() {
        Bag bag = new Bag();
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.RED);
        bag.addStudent(student1);
        bag.addStudent(student2);
        drawStudent() = getStudent
        getStudent(student2) ==
    }
}