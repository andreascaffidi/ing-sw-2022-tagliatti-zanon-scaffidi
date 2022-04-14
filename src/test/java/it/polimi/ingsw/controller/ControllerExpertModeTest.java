package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.AssistantNotFoundException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.requestMessage.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExpertModeTest {

    private ControllerExpertMode controllerExpertMode;
    private TableExpertMode tableExpertMode;

    @BeforeEach
    void setUp() {
        List<Player> players = new ArrayList<>(Arrays.asList(new Player("p1"), new Player("p2")));
        tableExpertMode = new TableExpertMode(players);
        controllerExpertMode = new ControllerExpertMode(tableExpertMode);
    }

    @AfterEach
    void tearDown() {
        tableExpertMode = null;
        controllerExpertMode = null;
    }

    @Test
    void update() {
        assertTrue(true, "tested in all other methods");
    }

    //tests no-ExpertMode messages

    @Test
    void playAssistant() {
        Message message = new Message(new PlayAssistantMessage(3), "p1");
        controllerExpertMode.update(message);

        //check if assistant is played: removed from player's deck, and added to player's discard pile

        Exception exception = assertThrows(AssistantNotFoundException.class, () -> tableExpertMode.getPlayers()[0].getAssistant(3));
        assertEquals("Assistant not found", exception.getMessage());
        assertEquals(3, tableExpertMode.getPlayers()[0].getDiscardPile().peek().getValue());

        //check if next Player is correctly set

        assertEquals(tableExpertMode.getPlayers()[1], tableExpertMode.getCurrentPlayer());

        //check if WrongPlayerException is caught (tbd)

        Message message2 = new Message(new PlayAssistantMessage(3), "p1");
        controllerExpertMode.update(message2);

        //check if AssistantNotFoundException is caught (tbd)

        Message message3 = new Message(new PlayAssistantMessage(11), "p2");
        controllerExpertMode.update(message3);

        //check if AssistantNotPlayableException is caught (tbd)

        Message message4 = new Message(new PlayAssistantMessage(3), "p2");
        controllerExpertMode.update(message4);
    }

    @Test
    void moveStudentToIsland() {
        Message message = new Message(new MoveStudentMessage("island", 1, 3), "p1");
        Student studentToMove = tableExpertMode.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().get(2);
        controllerExpertMode.update(message);

        //check if student has been moved:

        assertFalse(tableExpertMode.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(studentToMove));
        assertTrue(tableExpertMode.getIsland(0).getStudents().contains(studentToMove));

        //check if WrongPlayerException is caught (tbd)

        Message message2 = new Message(new MoveStudentMessage("island", 1, 3), "p2");
        controllerExpertMode.update(message2);

        //check if IslandNotValidException is caught (tbd)

        Message message3 = new Message(new MoveStudentMessage("island", 13, 3), "p1");
        controllerExpertMode.update(message3);

        //check if StudentIndexOutOfBoundsException is caught (tbd)

        Message message4 = new Message(new MoveStudentMessage("island", 1, -1), "p1");
        controllerExpertMode.update(message4);
    }

    @Test
    void moveStudentToDining() {
        Message message = new Message(new MoveStudentMessage("dining", 3), "p1");
        Student studentToMove = tableExpertMode.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().get(2);
        ColorS studentColor = studentToMove.getColor();
        controllerExpertMode.update(message);

        //check if student has been moved:

        assertFalse(tableExpertMode.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(studentToMove));
        assertTrue(tableExpertMode.getPlayers()[0].getSchoolBoard().getDiningRoom().getLine(studentColor).contains(studentToMove));

        //check if WrongPlayerException is caught (tbd)

        Message message2 = new Message(new MoveStudentMessage("dining", 3), "p2");
        controllerExpertMode.update(message2);

        //check if StudentIndexOutOfBoundsException is caught (tbd)

        Message message3 = new Message(new MoveStudentMessage("dining", 8), "p1");
        controllerExpertMode.update(message3);
    }

    @Test
    void moveMotherNature() {
        Message message = new Message(new MoveMotherNatureMessage(3), "p1");
        tableExpertMode.getCurrentPlayer().addToDiscardPile(new Assistant(8, 4, Wizards.WIZARD_1));
        int motherNatureIsland = tableExpertMode.motherNatureIsland().getId();
        controllerExpertMode.update(message);

        //check if mother nature has been moved:

        assertFalse(tableExpertMode.getIsland(motherNatureIsland).isMotherNature());

        //check if WrongPlayerException is caught (tbd)

        Message message2 = new Message(new MoveMotherNatureMessage(3), "p2");
        controllerExpertMode.update(message2);

        //check if MovementNotValidException is caught (tbd)

        Message message3 = new Message(new MoveMotherNatureMessage(5), "p1");
        controllerExpertMode.update(message3);
    }

    @Test
    void chooseCloud() {
        Message message = new Message(new ChooseCloudMessage(1), "p1");
        tableExpertMode.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        tableExpertMode.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        tableExpertMode.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        List<Student> cloudStudents = tableExpertMode.getClouds().get(0).getStudents();
        controllerExpertMode.update(message);

        //check if students were taken from cloud:

        assertTrue(tableExpertMode.getClouds().get(0).getStudents().isEmpty());
        for (Student s : cloudStudents) {
            assertTrue(tableExpertMode.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(s));
        }

        //check if next Player is correctly set

        assertEquals(tableExpertMode.getPlayers()[1], tableExpertMode.getCurrentPlayer());

        //check if WrongPlayerException is caught (tbd)

        Message message2 = new Message(new ChooseCloudMessage(1), "p1");
        controllerExpertMode.update(message2);

        //check if CloudNotValidException is caught (tbd)

        Message message3 = new Message(new ChooseCloudMessage(1), "p2");
        controllerExpertMode.update(message3);
    }

    //test ExpertMode messages

    @Test
    void payCharacter1() {
    }

    //test abbozzato da completare
    @Test
    void payCharacter2() {
        Message message = new Message(new PayCharacter2Message(), "p1", true);
        controllerExpertMode.update(message);
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
}