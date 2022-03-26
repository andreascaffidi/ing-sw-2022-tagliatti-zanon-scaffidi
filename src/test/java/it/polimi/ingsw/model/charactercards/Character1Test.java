package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerExpertMode;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Character1Test {

    private Character1 character;
    private TableExpertMode table;
    private PlayerExpertMode player1;
    private PlayerExpertMode player2;
    private List<PlayerExpertMode> players;

    @BeforeEach
    void init() {
        character = new Character1();
        players = new ArrayList<PlayerExpertMode>();
        player1 = new PlayerExpertMode("1");
        player2 = new PlayerExpertMode("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {
        //setto IslandChosen ad 1
        //aggiungo student alla carta
        //decido di selezionare student come studentChosen
        character.setIslandChosen(1);
        Student student = new Student(ColorS.BLUE);
        character.getStudents().add(student);
        character.setStudentChosen(student);

        character.effect(this.table);

        //dopo l'effetto mi aspetto che:

        //l'island scelta contenga student
        assertTrue(table.getIsland(1).getStudents().contains(student));

        //la carta non contenga più lo student
        assertFalse(character.getStudents().contains(student));
    }

    @Test
    void setup() {
        //aggiungo 4 Students alla Bag
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student4 = new Student(ColorS.BLUE);
        this.table.getBag().addStudent(student1);
        this.table.getBag().addStudent(student2);
        this.table.getBag().addStudent(student3);
        this.table.getBag().addStudent(student4);

        character.setup(this.table);

        //dopo la chiamata di setup assumo che quei 4 studenti siano sulla card
        assertTrue(character.getStudents().contains(student1));
        assertTrue(character.getStudents().contains(student2));
        assertTrue(character.getStudents().contains(student3));
        assertTrue(character.getStudents().contains(student4));

        //e non siano più nella Bag
        assertFalse(this.table.getBag().getStudents().contains(student1));
        assertFalse(this.table.getBag().getStudents().contains(student2));
        assertFalse(this.table.getBag().getStudents().contains(student3));
        assertFalse(this.table.getBag().getStudents().contains(student4));
    }

    @Test
    void setAndGetIslandChosen() {
        character.setIslandChosen(1);
        assertEquals(character.getStudentChosen(), 1);
    }

    @Test
    void setAndGetStudentChosen() {
        Student studentChosen = new Student(ColorS.BLUE);
        character.setStudentChosen(studentChosen);
        assertEquals(character.getStudentChosen(), studentChosen);
    }
}