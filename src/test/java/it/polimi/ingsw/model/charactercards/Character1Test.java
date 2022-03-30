package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

class Character1Test {

    private Character1 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character1();
        players = new ArrayList<Player>();
        player1 = new Player("1");
        player2 = new Player("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {
        //setup della bag
        Student student3 = new Student(ColorS.BLUE);

        List<Student> students = new ArrayList<Student>();

        students.add(student3);

        this.table.getBag().setStudents(students);

        //creo lo studente
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);

        //setto IslandChosen a 1
        character.setIslandChosen(1);

        //aggiungo lo student alla carta
        character.getStudents().add(student1);
        character.getStudents().add(student2);

        //setto lo studente scelto
        character.setStudentChosen(student1);

        //dopo l'effetto mi aspetto che
        character.effect(this.table);

        //l'island scelta contenga lo studente scelto
        assertTrue(table.getIsland(1).getStudents().contains(student1));

        //la carta non contenga più lo studente scelto
        assertFalse(character.getStudents().contains(student1));

        //poi dalla bag estraggo un altro student e lo metto sulla carta
        assertTrue(character.getStudents().contains(student3));
        assertFalse(table.getBag().getStudents().contains(student3));
    }

    @Test
    void setup() {
        //aggiungo 4 Students alla Bag
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student4 = new Student(ColorS.BLUE);

        List<Student> students = new ArrayList<Student>();

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        this.table.getBag().setStudents(students);

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
        assertEquals(1, character.getIslandChosen());
    }

    @Test
    void setAndGetStudentChosen() {
        Student studentChosen = new Student(ColorS.BLUE);
        character.setStudentChosen(studentChosen);
        assertEquals(character.getStudentChosen(), studentChosen);
    }
}