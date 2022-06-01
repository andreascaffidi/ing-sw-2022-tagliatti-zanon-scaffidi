package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.exceptions.ColorNotFoundException;
import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class purpose is to test all methods of the DiningRoom class
 */
class DiningRoomTest {

    private DiningRoom diningRoom;
    private List<Student> blueStudents;
    private List<Student> yellowStudents;
    private List<Student> redStudents;
    private List<Student> greenStudents;
    private List<Student> pinkStudents;

    /**
     *  Initialises and populates a  series of arraylists of the same color
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init(){
        diningRoom = new DiningRoom();
        blueStudents = new ArrayList<Student>();
        yellowStudents = new ArrayList<Student>();
        redStudents = new ArrayList<Student>();
        greenStudents = new ArrayList<Student>();
        pinkStudents = new ArrayList<Student>();
        blueStudents.add(new Student(ColorS.BLUE));
        blueStudents.add(new Student(ColorS.BLUE));
        yellowStudents.add(new Student(ColorS.YELLOW));
        yellowStudents.add(new Student(ColorS.YELLOW));
        redStudents.add(new Student(ColorS.RED));
        greenStudents.add(new Student(ColorS.GREEN));
    }

    /**
     * Tests that an exception is thrown if is passed a non valid color
     */
    @Test
    void getLine() {
        Exception e = assertThrows(RuntimeException.class,()-> diningRoom.getLine(null));
    }

    /**
     * Add some series of students by color and verifies that are returned correctly
     */
    @Test
    void addStudent() {
        for (Student s : blueStudents){
            diningRoom.addStudent(s);
        }
        for (Student s : yellowStudents){
            diningRoom.addStudent(s);
        }
        for (Student s : redStudents){
            diningRoom.addStudent(s);
        }
        for (Student s : greenStudents){
            diningRoom.addStudent(s);
        }
        assertEquals(blueStudents, diningRoom.getLine(ColorS.BLUE));
        assertEquals(yellowStudents, diningRoom.getLine(ColorS.YELLOW));
        assertEquals(redStudents, diningRoom.getLine(ColorS.RED));
        assertEquals(greenStudents, diningRoom.getLine(ColorS.GREEN));
        assertEquals(pinkStudents, diningRoom.getLine(ColorS.PINK));
    }

    /**
     * Adds a fixed amount of a fixed color of student and tests that is returned the correct number
     * of students for each color
     */
    @Test
    void getNumberOfStudentsPerColor() {
        for (Student s : blueStudents){
            diningRoom.addStudent(s);
        }
        for (Student s : redStudents){
            diningRoom.addStudent(s);
        }
        assertEquals(2, diningRoom.getNumberOfStudentsPerColor(ColorS.BLUE));
        assertEquals(1, diningRoom.getNumberOfStudentsPerColor(ColorS.RED));
    }

    /**
     * Tests if a students is removed
     */
    @Test
    void removeStudentByColor() {
        for (Student s : yellowStudents) {
            diningRoom.addStudent(s);
        }
        diningRoom.removeStudent(ColorS.YELLOW);
        assertFalse(diningRoom.getLine(ColorS.YELLOW).contains(yellowStudents.get(yellowStudents.size()-1)));
    }


    @Test
    void validColors() throws GameException, ColorNotFoundException {
        diningRoom.addStudent(new Student(ColorS.BLUE));
        List<ColorS> colors = new ArrayList<>();
        colors.add(ColorS.BLUE);
        diningRoom.validColors(colors);
        diningRoom.getLine(ColorS.BLUE).remove(0);
        assertThrows(GameException.class, () -> diningRoom.validColors(colors));
        diningRoom.addStudent(new Student(ColorS.BLUE));
        diningRoom.addStudent(new Student(ColorS.BLUE));
        colors.add(ColorS.BLUE);
        diningRoom.validColors(colors);
        diningRoom.getLine(ColorS.BLUE).remove(0);
        assertThrows(GameException.class, () -> diningRoom.validColors(colors));
        diningRoom.addStudent(new Student(ColorS.RED));
        colors.remove(0);
        colors.add(ColorS.RED);
        diningRoom.validColors(colors);
    }

    /**
     * Sets to null every attribute
     * <br>
     * <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown(){
        diningRoom = null;
        blueStudents = null;
        yellowStudents = null;
        redStudents = null;
        greenStudents = null;
        pinkStudents = null;
    }
}