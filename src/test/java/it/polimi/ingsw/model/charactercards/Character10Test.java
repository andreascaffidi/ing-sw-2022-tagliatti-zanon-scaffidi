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

class Character10Test {

    private Character10 character;
    private TableExpertMode table;
    private PlayerExpertMode player1;
    private PlayerExpertMode player2;
    private List<PlayerExpertMode> players;

    @BeforeEach
    void init() {
        character = new Character10();
        players = new ArrayList<PlayerExpertMode>();
        player1 = new PlayerExpertMode("1");
        player2 = new PlayerExpertMode("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {
        //creo una lista di students
        List<Student> students= new ArrayList<Student>();
        Student student = new Student(ColorS.BLUE);
        students.add(student);

        List<Student> students2= new ArrayList<Student>();
        Student student2 = new Student(ColorS.BLUE);
        students2.add(student2);

        table.setCurrentPlayer(player1);

        //scelgo di toglierli alla Entrance (quindi sono lì)
        character.setEntranceStudentsChosen(students);
        character.setDinnerRoomStudentsChosen(students2);

        //una volta chiamato l'effect
        character.effect(this.table);

        //li ho spostati sulla DinnerRoom e non sono più nella Entrance
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().containsAll(students2));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).containsAll(students2));

        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().containsAll(students));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).containsAll(students));
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
        character.setDinnerRoomStudentsChosen(students);
        assertEquals(character.getDinnerRoomStudentsChosen(), students);
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