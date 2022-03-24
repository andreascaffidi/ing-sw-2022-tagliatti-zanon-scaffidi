package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CloudTest {

    private List<Student> students;
    private Cloud cloud;

    @BeforeEach
    void init(){
        cloud = new Cloud();
        students = new ArrayList<>();
        students.add(new Student(ColorS.RED));
        students.add(new Student(ColorS.YELLOW));
        students.add(new Student(ColorS.PINK));
    }

    @AfterEach
    void tearDown(){
        this.students = null;
    }

    @Test
    void getStudents(){
        assertTrue(true, "tested in other methods");
    }

    @Test
    void addAndTakeStudents() {
        for (Student s : students){
            cloud.addStudent(s);
        }
        assertEquals(students, cloud.takeAllStudents());
        assertTrue(cloud.getStudents().isEmpty());
    }
}