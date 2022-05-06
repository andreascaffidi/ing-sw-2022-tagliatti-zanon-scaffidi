package it.polimi.ingsw.model.islands;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
    Island island;
    int randomId;
    private List<Student> students;

    @BeforeEach
    void init() {
        this.randomId = new Random().nextInt(12);
        this.island = new Island(randomId);
        students = new ArrayList<>();

        //Random students to add
        int numStudents = new Random().nextInt(10)+1;

        for(int i = 0; i<numStudents; i++){
            // Random Color
            int indexColor = new Random().nextInt(ColorS.values().length);
            // new random student added
            students.add(new Student(ColorS.values()[indexColor]));
        }
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getId() {
        assertEquals(randomId,this.island.getId());
    }

    @Test
    void addStudent() {
        this.island.addStudent(students.get(0));
        assertTrue(island.getStudents().contains(students.get(0)));
    }

    @Test
    void addStudents() {
        this.island.addStudents(students);
        for (Student s : students) {
            assertTrue(island.getStudents().contains(s));
        }
        for(Student s: island.getStudents()){
            assertTrue(students.contains(s));
        }
    }



    @Test
    void numStudent() {
        this.island.addStudents(students);
        ColorS randColor = ColorS.values()[new Random().nextInt(ColorS.values().length)];
        int numOfColor = this.students
                .stream()
                .filter((student)->student.getColor().equals(randColor))
                .collect(Collectors.toList())
                .size();
        assertEquals(numOfColor,island.numStudent(randColor));
    }

    @Test
    void setMotherNature() {
        MotherNature motherNature = new MotherNature(island);
        island.setMotherNature(true);
        assertTrue(island.isMotherNature());
    }

    @Test
    void isMotherNature() {
        MotherNature motherNature = new MotherNature(island);
        island.setMotherNature(true);
        assertTrue(island.isMotherNature());
    }


    @Test
    void getStudents() {
        this.island.addStudents(students);
        for (Student s : students) {
            assertTrue(island.getStudents().contains(s));
        }
        for(Student s: island.getStudents()){
            assertTrue(students.contains(s));
        }
    }

    @Test
    void setTower() {
        Player player = new Player("Test");
        Tower tower = new Tower(ColorT.values()[new Random().nextInt(ColorT.values().length)],player);
        island.setTower(tower);
        assertEquals(tower,island.getTower());
    }

    @Test
    void getTower() {
        Player player = new Player("Test");
        Tower tower = new Tower(ColorT.values()[new Random().nextInt(ColorT.values().length)],player);
        island.setTower(tower);
        assertEquals(tower,island.getTower());
    }


    @Test
    void setNumOfTowers() {
        //I use randomId because is a random int from 0 to 12
        island.setNumOfTowers(randomId);
        assertEquals(randomId,island.getNumOfTowers());
    }
    @Test
    void getNumOfTowers() {
        //I use randomId because is a random int from 0 to 12
        island.setNumOfTowers(randomId);
        assertEquals(randomId,island.getNumOfTowers());
    }


    @Test
    void changeId() {
        int decrement = 1;
        island.changeId(decrement);
        assertEquals(randomId-decrement, island.getId());
    }


}