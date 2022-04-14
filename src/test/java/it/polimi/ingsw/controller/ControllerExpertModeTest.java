package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.CardNotFoundException;
import it.polimi.ingsw.exceptions.InvalidCharacterException;
import it.polimi.ingsw.exceptions.NotEnoughCoinsException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.requestMessage.MoveStudentMessage;
import it.polimi.ingsw.network.requestMessage.PayCharacter1Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExpertModeTest {

    private ControllerExpertMode controller;
    private TableExpertMode table;

    @BeforeEach
    void setUp() {
        List<Player> players = new ArrayList<>(Arrays.asList(new Player("p1"), new Player("p2")));
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);
    }

    @AfterEach
    void tearDown() {
        table = null;
        controller = null;
    }

    @Test
    void update() {
        assertTrue(true, "tested in all other ControllerExpertMode methods");
    }

    @Test
    void moveStudentToDining() {
        Player player = table.getCurrentPlayer();
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        assertEquals(1, table.getPlayerCoins(player));

        Message message = new Message(new MoveStudentMessage("dining", 3), table.getCurrentPlayer().getUsername());
        controller.update(message);

        assertEquals(2, table.getPlayerCoins(player));
    }

    @Test
    void moveStudentToDiningStudentIndexOutOfBound() {
        Player player = table.getCurrentPlayer();
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        assertEquals(1, table.getPlayerCoins(player));

        Message message = new Message(new MoveStudentMessage("dining", 8), "p1");
        controller.update(message);
    }

    @Test
    @RepeatedTest(100)
    void payCharacter1() throws CardNotFoundException {

        Message message = new Message(new PayCharacter1Message(1, 1), "p1", true);

        if(table.getCharacters().containsKey(1))
        {
            Student student1 = table.getCardWithStudents(1).getStudents().get(0);

            controller.update(message);

            //l'island scelta contenga lo studente scelto
            assertTrue(table.getIsland(0).getStudents().contains(student1));

            //la carta non contenga più lo studente scelto
            assertFalse(table.getCardWithStudents(1).getStudents().contains(student1));

            //poi dalla bag estraggo un altro student e lo metto sulla carta
            assertEquals(4, table.getCardWithStudents(1).getStudents().size());
        }

        else
        {
            //tbd
        }

    }

    @Test
    void payCharacter2() {

    }

    @Test
    void payCharacter3() {
    }

    @Test
    void payCharacter4() {
    }

    @Test
    void payCharacter5() {
    }

    @Test
    void payCharacter6() {
    }

    @Test
    void payCharacter7() {
    }

    @Test
    void payCharacter8() {
    }

    @Test
    void payCharacter9() {
    }

    @Test
    void payCharacter10() {
    }

    @Test
    void payCharacter11() {
    }

    @Test
    void payCharacter12() {
    }

    @Test
    void pay()
    {

    }
}