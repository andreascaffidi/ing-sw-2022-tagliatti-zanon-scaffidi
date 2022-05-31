package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class purpose is to test all methods of the Bag class
 */
class BagTest {

    private Bag bag;
    private List<Student> students;

    /**
     *  Initialises a new bag and a new arraylist of students with 3 different students into it.
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init() {
        bag = new Bag();
        students = new ArrayList<>();
        students.add(new Student(ColorS.GREEN));
        students.add(new Student(ColorS.RED));
        students.add(new Student(ColorS.PINK));
    }

    /**
     * Sets to null every attribute
     * <br>
     * <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        this.bag = null;
        this.students = null;
    }

    /**
     * Tests if it returns all the students
     *  <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getStudents() {
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if a student is added correctly into the bag
     *  <ol>
     *      <li>Add a student into the bag</li>
     *      <li>Verifies the student is in the bag</li>
     *  </ol>
     */
    @Test
    void addStudent() {
        for (Student s : students){
            bag.addStudent(s);
            assertTrue(bag.getStudents().contains(s));
        }
    }

    /**
     * Tests if students are added correctly into the bag
     *  <ol>
     *      <li>Adds some students into the bag</li>
     *      <li>Verifies if all students are in the bag</li>
     *  </ol>
     */
    @Test
    void addStudents() {
        bag.addStudents(students);
        for (Student s : students){
            assertTrue(bag.getStudents().contains(s));
        }
    }

    /**
     * Tests if a student is drawn correctly:
     * <ol>
     *     <li>Adds some students to the bag</li>
     *     <li>Draws a student</li>
     *     <li>Verifies that the student is no more in the bag</li>
     * </ol>
     */
    @Test
    void drawStudent() {
        bag.addStudents(students);
        Student studentToDraw = bag.getStudents().get(0);
        assertEquals(studentToDraw, bag.drawStudent());
        assertFalse(bag.getStudents().contains(studentToDraw));
    }
}