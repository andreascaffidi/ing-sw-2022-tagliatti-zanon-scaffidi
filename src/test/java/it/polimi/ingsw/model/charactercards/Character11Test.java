package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

class Character11Test {

    private Character11 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;
    private List<Student> students;
    private Student studentChosen;
    private static final int NUM_OF_STUDENTS = 4;

    @BeforeEach
    void init() {
        character = new Character11();
        players = new ArrayList<Player>();
        player1 = new Player("1");
        player2 = new Player("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }


    @Test
    void effect() {

        table.setCurrentPlayer(player1);
        ArrayList<Student> students = new ArrayList<Student>();

        //creo 3 studenti
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.RED);

        //2 li aggiungo sulla carta
        character.getStudents().add(student1);
        character.getStudents().add(student2);

        //1 lo aggiungo nella bag
        students.add(student3);
        table.getBag().setStudents(students);

        //lo studente scelto è student1
        character.setStudentChosen(student1);

        character.effect(this.table);

        //lo studente scelto sarà nella dining room del currentplayer
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student1.getColor()).contains(student1));
        //e non sarà più sulla carta
        assertFalse(character.getStudents().contains(student1));

        //dato che l'unico studente nella bag è student3, lo prendo
        assertFalse(table.getBag().getStudents().contains(student3));
        //e lo metto sulla carta
        assertTrue(character.getStudents().contains(student3));

    }

    @Test
    void setup() {
        //creo 4 studenti
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student4 = new Student(ColorS.BLUE);

        ArrayList<Student> students = new ArrayList<Student>();

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        table.getBag().setStudents(students);

        character.setup(table);

        //dovrebbero essere sulla carta
        assertTrue(character.getStudents().contains(student1));
        assertTrue(character.getStudents().contains(student2));
        assertTrue(character.getStudents().contains(student3));
        assertTrue(character.getStudents().contains(student4));

        //e non dovrebbero essere più nella bag
        assertFalse(table.getBag().getStudents().contains(student1));
        assertFalse(table.getBag().getStudents().contains(student2));
        assertFalse(table.getBag().getStudents().contains(student3));
        assertFalse(table.getBag().getStudents().contains(student4));
    }

    @Test
    void setAndGetStudentChosen() {
        studentChosen = new Student(ColorS.BLUE);
        character.setStudentChosen(studentChosen);
        assertEquals(character.getStudentChosen(), studentChosen);
    }
}