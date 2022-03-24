package it.polimi.ingsw.model.schoolBoard;

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

    @BeforeEach
    void init(){
        entrance = new Entrance();
        students = new ArrayList<Student>(Arrays.asList(new Student(ColorS.GREEN), new Student(ColorS.BLUE)));
    }

    @AfterEach
    void tearDown(){
        entrance = null;
    }

    @Test
    void addAndGetStudent() {
        for (Student s : students) {
            entrance.addStudent(s);
        }
        assertEquals(students, entrance.getStudents());
    }

    @Test
    void removeStudent() {
        for (Student s : students) {
            entrance.addStudent(s);
        }
        entrance.removeStudent(students.get(0));
        assertFalse(entrance.getStudents().contains(students.get(0)));
    }
}