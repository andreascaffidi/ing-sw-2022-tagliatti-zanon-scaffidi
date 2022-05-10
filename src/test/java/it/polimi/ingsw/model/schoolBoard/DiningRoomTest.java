package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.AfterEach;
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
        Exception e = assertThrows(RuntimeException.class,()-> diningRoom.getLine(null));
        System.out.println(e.getMessage());
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
    void removeStudentByColor() {
        for (Student s : yellowStudents) {
            diningRoom.addStudent(s);
        }
        diningRoom.removeStudent(ColorS.YELLOW);
        assertFalse(diningRoom.getLine(ColorS.YELLOW).contains(yellowStudents.get(yellowStudents.size()-1)));
    }

    @Test
    void validColors() throws GameException {
        diningRoom.addStudent(new Student(ColorS.BLUE));
        List<String> colors = new ArrayList<>();
        colors.add("blue");
        diningRoom.validColors(colors);
        diningRoom.getLine(ColorS.BLUE).remove(0);
        assertThrows(GameException.class, () -> diningRoom.validColors(colors));
        diningRoom.addStudent(new Student(ColorS.BLUE));
        diningRoom.addStudent(new Student(ColorS.BLUE));
        colors.add("blue");
        diningRoom.validColors(colors);
        diningRoom.getLine(ColorS.BLUE).remove(0);
        assertThrows(GameException.class, () -> diningRoom.validColors(colors));
        diningRoom.addStudent(new Student(ColorS.RED));
        colors.remove(0);
        colors.add("red");
        diningRoom.validColors(colors);
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