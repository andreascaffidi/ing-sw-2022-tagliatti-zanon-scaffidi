package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
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
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character10();
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect(TableExpertMode table) {
        //creo una lista di students
        List<Student> students= new ArrayList<Student>();
        Student student = new Student(ColorS.BLUE);
        students.add(student);

        //scelgo di toglierli alla Entrance (quindi sono lì)
        character.setEntranceStudentsChosen(students);
        //una volta chiamato l'effect
        effect(this.table);
        //li ho spostati sulla DinnerRoom e non sono più nella Entrance
        assertEquals(character.getDinnerRoomStudentsChosen(), students);
        assertFalse(character.getEntranceStudentsChosen().contains(students));

        character.setDinnerRoomStudentsChosen(null);
        //scelgo di toglierli dalla DinnerRoom (quindi sono lì)
        character.setDinnerRoomStudentsChosen(students);
        //una volta chiamato l'effect
        effect(this.table);
        //li ho spostati sulla Entrance e non sono più nella DinnerRoom
        assertEquals(character.getEntranceStudentsChosen(), students);
        assertFalse(character.getEntranceStudentsChosen().contains(students));
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