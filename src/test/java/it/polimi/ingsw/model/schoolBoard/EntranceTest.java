package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntranceTest {

    private Entrance entrance;
    private List<Student> students;

    /**
     *  Initialises an entrance and a list of students
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init(){
        entrance = new Entrance();
        students = new ArrayList<>(Arrays.asList(new Student(ColorS.GREEN), new Student(ColorS.BLUE)));
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown(){
        entrance = null;
    }

    /**
     * Tests that students are added and got correctly
     */
    @Test
    void addAndGetStudent() {
        for (Student s : students) {
            entrance.addStudent(s);
        }
        assertEquals(students, entrance.getStudents());
    }

    /**
     * Tests that a student is removed correctly from the entrance
     */
    @Test
    void removeStudent() {
        for (Student s : students) {
            entrance.addStudent(s);
        }
        entrance.removeStudent(students.get(0));
        assertFalse(entrance.getStudents().contains(students.get(0)));
    }

    /**
     * Tests if a student's index is valid in the entrance
     */
    @Test
    void validStudentIndex() throws GameException {
        for(Student s : students){
            entrance.addStudent(s);
        }
        entrance.validStudentIndex(0);
        entrance.validStudentIndex(1);
        assertThrows(GameException.class, () -> entrance.validStudentIndex(2));
    }
}