package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.exceptions.GetCoinException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DiningRoomTest {

    private DiningRoom diningRoom;
    private List<Student> blueStudents;
    private List<Student> yellowStudents;
    private List<Student> redStudents;
    private List<Student> greenStudents;
    private List<Student> pinkStudents;

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

    @Test
    void getLine() {
        Assertions.assertThrows(RuntimeException.class,()->diningRoom.getLine(null));
    }

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

    @Test
    void removeStudent() {
        for (Student s : blueStudents){
            diningRoom.addStudent(s);
        }
        diningRoom.removeStudent(blueStudents.get(0));
        assertFalse(diningRoom.getLine(ColorS.BLUE).contains(blueStudents.get(0)));
    }

    @Test
    void removeStudentByColor() {
        for (Student s : yellowStudents) {
            diningRoom.addStudent(s);
        }
        diningRoom.removeStudent(ColorS.YELLOW);
        assertFalse(diningRoom.getLine(ColorS.YELLOW).contains(yellowStudents.get(yellowStudents.size()-1)));
    }

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