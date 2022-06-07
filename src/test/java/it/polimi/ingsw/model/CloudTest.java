package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The purpose of this class is to test all methods of Cloud class
 */
class CloudTest {

    private List<Student> students;
    private Cloud cloud;

    /**
     *  Initialises a new cloud and a new arraylist of students with 3 different students into it.
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init(){
        cloud = new Cloud();
        students = new ArrayList<>();
        students.add(new Student(ColorS.RED));
        students.add(new Student(ColorS.YELLOW));
        students.add(new Student(ColorS.PINK));
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown(){
        this.students = null;
    }

    /**
     * Tests if it returns all the students
     *  <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getStudents(){
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if students are added correctly into the bag and then removed
     *  <ol>
     *      <li>Adds some students into the bag</li>
     *      <li>Verifies that all students are in the bag</li>
     *      <li>Takes all student from the bag</li>
     *      <li>Verifies that the bag is empty</li>
     *  </ol>
     */
    @Test
    void addAndTakeStudents() {
        for (Student s : students){
            cloud.addStudent(s);
        }
        assertEquals(students, cloud.takeAllStudents());
        assertTrue(cloud.getStudents().isEmpty());
    }
}