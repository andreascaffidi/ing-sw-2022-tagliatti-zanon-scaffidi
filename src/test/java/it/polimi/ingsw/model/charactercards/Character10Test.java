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
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Character10Test {

    private Character10 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character10();
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

        //creo una lista di students
        List<Student> students= new ArrayList<Student>();
        Student student = new Student(ColorS.BLUE);
        Student student1 = new Student(ColorS.BLUE);
        students.add(student);

        List<Student> students2= new ArrayList<Student>();
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        students2.add(student2);

        //student = students no dining, student1 si dining
        table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(student);
        table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(student1);

        //student2 = students2 no entrance, student3 si entrance
        table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(student2);
        table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(student3);

        //scelgo di toglierli alla Entrance (quindi sono lì)
        character.setEntranceStudentsChosen(students2);
        character.setDiningRoomStudentsChosen(students);

        //una volta chiamato l'effect
        character.effect(this.table);

        //c'è il cambio

        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student.getColor()).containsAll(students));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student3.getColor()).contains(student3));

        assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student2.getColor()).containsAll(students2));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student1.getColor()).contains(student1));

        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().containsAll(students2));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student3));

        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().containsAll(students));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student1));
    }

    //no setup
    @Test
    void setup() {
        assertTrue(true, "not needed");
    }

    //test di set e get
    @Test
    void setAndGetDinnerRoomStudentsChosen() {
        List<Student> students= new ArrayList<Student>();
        Student student = new Student(ColorS.BLUE);
        students.add(student);
        character.setDiningRoomStudentsChosen(students);
        assertEquals(character.getDiningRoomStudentsChosen(), students);
    }

    @Test
    void setAndGetEntranceStudentsChosen() {
        List<Student> students= new ArrayList<Student>();
        Student student = new Student(ColorS.BLUE);
        students.add(student);
        character.setEntranceStudentsChosen(students);
        assertEquals(character.getEntranceStudentsChosen(), students);
    }
}