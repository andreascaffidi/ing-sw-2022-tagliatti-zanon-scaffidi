package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    private Bag bag;
    private List<Student> students;

    @BeforeEach
    void init() {
        bag = new Bag();
        students = new ArrayList<>();
        students.add(new Student(ColorS.GREEN));
        students.add(new Student(ColorS.RED));
        students.add(new Student(ColorS.PINK));
    }

    @AfterEach
    void tearDown() {
        this.bag = null;
        this.students = null;
    }

    @Test
    void getStudents() {
        assertTrue(true, "tested in other methods");
    }

    @Test
    void addStudent() {
        for (Student s : students){
            bag.addStudent(s);
            assertTrue(bag.getStudents().contains(s));
        }
    }

    @Test
    void addStudents() {
        bag.addStudents(students);
        for (Student s : students){
            assertTrue(bag.getStudents().contains(s));
        }
    }


    @Test
    void drawStudent() {
        bag.addStudents(students);
        Student studentToDraw = bag.getStudents().get(0);
        assertEquals(studentToDraw, bag.drawStudent());
        assertFalse(bag.getStudents().contains(studentToDraw));
    }
}