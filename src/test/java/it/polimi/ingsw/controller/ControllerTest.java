package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.AssistantNotFoundException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.requests.messages.ChooseCloudMessage;
import it.polimi.ingsw.network.requests.messages.MoveMotherNatureMessage;
import it.polimi.ingsw.network.requests.messages.MoveStudentMessage;
import it.polimi.ingsw.network.requests.messages.PlayAssistantMessage;
import it.polimi.ingsw.view.View;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;
    private Table table;

    @BeforeEach
    void setUp() {
        List<Player> players = new ArrayList<>(Arrays.asList(new Player("p1"), new Player("p2")));
        table = new Table(players);
        controller = new Controller(table);
    }

    @AfterEach
    void tearDown() {
        table = null;
        controller = null;
    }

    @Test
    void update(){
        assertTrue(true, "tested in all other Controller methods");
    }

    @Test
    void playAssistant() {
        ControllerMessage message = new ControllerMessage(new PlayAssistantMessage(3), "p1");
        controller.update(message);

        //check if assistant is played: removed from player's deck, and added to player's discard pile

        Exception exception = assertThrows(AssistantNotFoundException.class, () -> table.getPlayers()[0].getAssistant(3));
        assertEquals("Assistant not found", exception.getMessage());
        assertEquals(3, table.getPlayers()[0].getDiscardPile().peek().getValue());

        //check if next Player is correctly set

        assertEquals(table.getPlayers()[1], table.getCurrentPlayer());

        //check if WrongPlayerException is caught (tbd)

        ControllerMessage message2 = new ControllerMessage(new PlayAssistantMessage(3), "p1");
        controller.update(message2);

        //check if AssistantNotFoundException is caught (tbd)

        ControllerMessage message3 = new ControllerMessage(new PlayAssistantMessage(11), "p2");
        controller.update(message3);

        //check if AssistantNotPlayableException is caught (tbd)

        ControllerMessage message4 = new ControllerMessage(new PlayAssistantMessage(3), "p2");
        controller.update(message4);
    }

    @Test
    void moveStudentToIsland() {
        ControllerMessage message = new ControllerMessage(new MoveStudentMessage("island", 1, 3), "p1");
        Student studentToMove = table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().get(2);
        controller.update(message);

        //check if student has been moved:

        assertFalse(table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(studentToMove));
        assertTrue(table.getIsland(0).getStudents().contains(studentToMove));

        //check if WrongPlayerException is caught (tbd)

        ControllerMessage message2 = new ControllerMessage(new MoveStudentMessage("island", 1, 3), "p2");
        controller.update(message2);

        //check if IslandNotValidException is caught (tbd)

        ControllerMessage message3 = new ControllerMessage(new MoveStudentMessage("island", 13, 3), "p1");
        controller.update(message3);

        //check if StudentIndexOutOfBoundsException is caught (tbd)

        ControllerMessage message4 = new ControllerMessage(new MoveStudentMessage("island", 1, -1), "p1");
        controller.update(message4);
    }

    @Test
    void moveStudentToDining() {
        ControllerMessage message = new ControllerMessage(new MoveStudentMessage("dining", 3), "p1");
        Student studentToMove = table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().get(2);
        ColorS studentColor = studentToMove.getColor();
        controller.update(message);

        //check if student has been moved:

        assertFalse(table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(studentToMove));
        assertTrue(table.getPlayers()[0].getSchoolBoard().getDiningRoom().getLine(studentColor).contains(studentToMove));

        //check if WrongPlayerException is caught (tbd)

        ControllerMessage message2 = new ControllerMessage(new MoveStudentMessage("dining", 3), "p2");
        controller.update(message2);

        //check if StudentIndexOutOfBoundsException is caught (tbd)

        ControllerMessage message3 = new ControllerMessage(new MoveStudentMessage("dining", 8), "p1");
        controller.update(message3);
    }

    @Test
    void moveMotherNature() {
        ControllerMessage message = new ControllerMessage(new MoveMotherNatureMessage(3), "p1");
        table.getCurrentPlayer().addToDiscardPile(new Assistant(8, 4, Wizards.WIZARD_1));
        int motherNatureIsland = table.motherNatureIsland().getId();
        controller.update(message);

        //check if mother nature has been moved:

        assertFalse(table.getIsland(motherNatureIsland).isMotherNature());

        //check if WrongPlayerException is caught (tbd)

        ControllerMessage message2 = new ControllerMessage(new MoveMotherNatureMessage(3), "p2");
        controller.update(message2);

        //check if MovementNotValidException is caught (tbd)

        ControllerMessage message3 = new ControllerMessage(new MoveMotherNatureMessage(5), "p1");
        controller.update(message3);
    }

    @Test
    void chooseCloud() {
        ControllerMessage message = new ControllerMessage(new ChooseCloudMessage(1), "p1");
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        List<Student> cloudStudents = table.getClouds().get(0).getStudents();
        controller.update(message);

        //check if students were taken from cloud:

        assertTrue(table.getClouds().get(0).getStudents().isEmpty());
        for (Student s : cloudStudents) {
            assertTrue(table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(s));
        }

        //check if next Player is correctly set

        assertEquals(table.getPlayers()[1], table.getCurrentPlayer());

        //check if WrongPlayerException is caught (tbd)

        ControllerMessage message2 = new ControllerMessage(new ChooseCloudMessage(1), "p1");
        controller.update(message2);

        //check if CloudNotValidException is caught (tbd)

        ControllerMessage message3 = new ControllerMessage(new ChooseCloudMessage(1), "p2");
        controller.update(message3);
    }
}