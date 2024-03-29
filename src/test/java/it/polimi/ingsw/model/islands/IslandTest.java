package it.polimi.ingsw.model.islands;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.network.client.reducedModel.ReducedIsland;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class purpose is to test all methods of the Island class
 */
class IslandTest {
    Island island;
    int randomId;
    private List<Student> students;

    /**
     *  Initialises a new random Island and adds in it a random amount of students
     *  <br>
     *  <u>It's called before each test</u>
     */
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

    /**
     * Sets to null every attribute
     * <br>
     * <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        this.island = null;
        this.randomId = 0;
        this.students = null;
    }

    /**
     * Tests if it returns the id set during initialization
     */
    @Test
    void getId() {
        assertEquals(randomId,this.island.getId());
    }

    /**
     * Tests if a students is added to the island
     */
    @Test
    void addStudent() {
        this.island.addStudent(students.get(0));
        assertTrue(island.getStudents().contains(students.get(0)));
    }

    /**
     * Tests if an arraylist of students are added to the island
     */
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


    /**
     * Tests if it returns the number of students given a color
     */
    @Test
    void numStudent() {
        this.island.addStudents(students);
        ColorS randColor = ColorS.values()[new Random().nextInt(ColorS.values().length)];
        int numOfColor = (int) this.students
                .stream()
                .filter((student) -> student.getColor().equals(randColor)).count();
        assertEquals(numOfColor,island.numStudent(randColor));
    }

    /**
     * Tests if mother nature is set
     */
    @Test
    void setMotherNature() {
        island.setMotherNature(true);
        assertTrue(island.isMotherNature());
    }

    /**
     * Tests if mother nature is on the island
     */
    @Test
    void isMotherNature() {
        island.setMotherNature(true);
        assertTrue(island.isMotherNature());
    }


    /**
     * Tests if every student added is then returned
     */
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

    /**
     * Tests that a tower is set (and so got) correctly;
     */
    @Test
    void setTower() {
        Player player = new Player("Test");
        Tower tower = new Tower(ColorT.values()[new Random().nextInt(ColorT.values().length)],player);
        island.setTower(tower);
        assertEquals(tower,island.getTower());
    }

    /**
     * Tests that the username is set (and so got) correctly;
     */
    @Test
    void getTower() {
        Player player = new Player("Test");
        Tower tower = new Tower(ColorT.values()[new Random().nextInt(ColorT.values().length)],player);
        island.setTower(tower);
        assertEquals(tower,island.getTower());
    }

    /**
     * Tests that the number of towers on the island is set (and so got) correctly;
     */
    @Test
    void setNumOfTowers() {
        //I use randomId because is a random int from 0 to 12
        island.setNumOfTowers(randomId);
        assertEquals(randomId,island.getNumOfTowers());
    }

    /**
     * Tests that the number of towers on the island is set (and so got) correctly;
     */
    @Test
    void getNumOfTowers() {
        //I use randomId because is a random int from 0 to 12
        island.setNumOfTowers(randomId);
        assertEquals(randomId,island.getNumOfTowers());
    }

    /**
     * Tests that the id of the island is changed;
     */
    @Test
    void changeId() {
        int newID = 1;
        island.changeId(newID);
        assertEquals(1, island.getId());
    }

    /**
     * Tests if an island is reduced correctly
     */
    @Test
    void reduceIsland(){
        island.setTower(new Tower(ColorT.BLACK, new Player("Test")));
        island.setMotherNature(true);
        island.addStudents(students);
        List<ColorS> colors = students.stream().map(Student::getColor).collect(Collectors.toList());
        ReducedIsland reducedIsland = island.reduceIsland();
        assertEquals(randomId, reducedIsland.getId());
        assertTrue(reducedIsland.isMotherNature());
        assertEquals(ColorT.BLACK, reducedIsland.getTower());
        assertEquals(colors, reducedIsland.getStudents());
    }
}