package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Character7Test {

    private Character7 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character7();
        players = new ArrayList<>();
        player1 = new Player("1");
        player2 = new Player("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {

        table.setCurrentPlayer(player1);

        //creo 3 studenti blu e 2 rossi
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);

        Student student4 = new Student(ColorS.RED);
        Student student5 = new Student(ColorS.RED);

        //aggiungo 3 studenti blu alla carta
        character.getCardStudents().add(student1);
        character.getCardStudents().add(student2);
        character.getCardStudents().add(student3);

        //scelgo 2 studenti della carta (BLU)
        character.getCardStudentsChosen().add(student1);
        character.getCardStudentsChosen().add(student2);

        //scelgo 2 studenti dell'entrata (ROSSI)
        character.getEntranceStudentChosen().add(student4);
        character.getEntranceStudentChosen().add(student5);

        character.effect(this.table);

        //dopo l'effetto mi aspetto che:

        //la carta non contenga più gli studenti BLU
        assertFalse(character.getCardStudents().contains(student1));
        assertFalse(character.getCardStudents().contains(student2));
        assertTrue(character.getCardStudents().contains(student3));

        //ma contenga quelli rossi
        assertTrue(character.getCardStudents().contains(student4));
        assertTrue(character.getCardStudents().contains(student5));

        //l'entrata non contenga più gli studenti rossi
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student4));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student5));

        //ma quelli blu
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student1));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student2));
    }

    @Test
    void setup() {
        //aggiungo 6 Students alla Bag
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student4 = new Student(ColorS.BLUE);
        Student student5 = new Student(ColorS.BLUE);
        Student student6 = new Student(ColorS.BLUE);

        List<Student> students = new ArrayList<Student>();

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);

        table.getBag().setStudents(students);

        character.setup(this.table);

        //dopo la chiamata di setup assumo che quei 6 studenti siano sulla card
        assertTrue(character.getCardStudents().contains(student1));
        assertTrue(character.getCardStudents().contains(student2));
        assertTrue(character.getCardStudents().contains(student3));
        assertTrue(character.getCardStudents().contains(student4));
        assertTrue(character.getCardStudents().contains(student5));
        assertTrue(character.getCardStudents().contains(student6));

        //e non siano più nella Bag
        assertFalse(this.table.getBag().getStudents().contains(student1));
        assertFalse(this.table.getBag().getStudents().contains(student2));
        assertFalse(this.table.getBag().getStudents().contains(student3));
        assertFalse(this.table.getBag().getStudents().contains(student4));
        assertFalse(this.table.getBag().getStudents().contains(student5));
        assertFalse(this.table.getBag().getStudents().contains(student6));
    }

    @Test
    void setAndGetCardStudentsChosen() {
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        character.setCardStudentsChosen(students);
        assertEquals(character.getCardStudentsChosen(), students);
    }

    @Test
    void setAndGetEntranceStudentChosen() {
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        character.setEntranceStudentChosen(students);
        assertEquals(character.getEntranceStudentChosen(), students);
    }

    @Test
    void setAndGetCardStudents() {
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        character.setCardStudents(students);
        assertEquals(character.getCardStudents(), students);
    }
}