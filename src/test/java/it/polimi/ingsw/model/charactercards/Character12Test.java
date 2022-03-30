package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

class Character12Test {

    private Character12 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character12();
        character.setColor(ColorS.BLUE);
        players = new ArrayList<Player>();
        player1 = new Player("1");
        player2 = new Player("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {

        ArrayList<Student> students = new ArrayList<Student>();

        table.getBag().setStudents(students);

        //Player1 ha 4 studenti BLU,
        // quindi quando chiamo effect(), 3 di questi andranno nella bag e 1 rimane in schoolboard
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student7 = new Student(ColorS.BLUE);

        Student student4 = new Student(ColorS.BLUE);
        Student student5 = new Student(ColorS.BLUE);
        Student student6 = new Student(ColorS.RED);


        player1.getSchoolBoard().getDiningRoom().addStudent(student1);
        player1.getSchoolBoard().getDiningRoom().addStudent(student2);
        player1.getSchoolBoard().getDiningRoom().addStudent(student3);
        player1.getSchoolBoard().getDiningRoom().addStudent(student7);

        //Player2 ha 2 studenti BLU e 1 ROSSO,
        //quindi quando chiamo effect(), devo metterli tutti nella bag e nessuno rimane in schoolboard

        player2.getSchoolBoard().getDiningRoom().addStudent(student4);
        player2.getSchoolBoard().getDiningRoom().addStudent(student5);
        player2.getSchoolBoard().getDiningRoom().addStudent(student6);

        //System.out.println(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).size());

        character.effect(this.table);

        //System.out.println(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).size());

        assertTrue(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student1));
        assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student2));
        assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student3));
        assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student7));


        assertFalse(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student4));
        assertFalse(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student5));
        assertTrue(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.RED).contains(student6));


        assertFalse(table.getBag().getStudents().contains(student1));
        assertTrue(table.getBag().getStudents().contains(student2));
        assertTrue(table.getBag().getStudents().contains(student3));
        assertTrue(table.getBag().getStudents().contains(student7));

        //Player2 ha 2 studenti BLU e 1 ROSSO,
        //quindi quando chiamo effect(), devo metterli tutti nella bag e nessuno rimane in schoolboard

        //character.effect(table);

        assertTrue(table.getBag().getStudents().contains(student4));
        assertTrue(table.getBag().getStudents().contains(student5));
        assertFalse(table.getBag().getStudents().contains(student6));
    }

    @Test
    void setup() {
        assertTrue("not needed", true);
    }

    @Test
    void setAndGetColor() {
        character.setColor(ColorS.BLUE);
        assertEquals(character.getColor(), ColorS.BLUE);
    }
}