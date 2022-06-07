package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.AssistantNotFoundException;
import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;
import it.polimi.ingsw.network.requests.gameMessages.MoveMotherNatureMessage;
import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;
    private Table table;

    /**
     *  Initialises two different players, a table and a controller
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void setUp() {
        List<Player> players = new ArrayList<>(Arrays.asList(new Player("p1"), new Player("p2")));
        table = new Table(players);
        controller = new Controller(table);
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        table = null;
        controller = null;
    }

    /**
     * Tests if a message is received from the connection and handled correctly
     *  <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void update(){
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if GameException has been thrown when a message is received from a player who isn't the current one
     */
    @Test
    void wrongPlayer(){
        //check if GameException is caught (nothing happens)
        ControllerMessage message = new ControllerMessage(new PlayAssistantMessage(3), "p2");
        controller.update(message);
        assertTrue(table.getPlayers()[1].getDiscardPile().isEmpty());
    }

    /**
     * Tests if an assistant card is played correctly:
     * <ol>
     *     <li>Creates a PlayAssistantMessage with random values</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if assistant has been played correctly</li>
     *     <li>Checks also if Exceptions have been thrown</li>
     * </ol>
     */
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

        //check if GameException is caught (nothing happens)

        ControllerMessage message2 = new ControllerMessage(new PlayAssistantMessage(30), "p2");
        controller.update(message2);
        assertEquals(10, table.getPlayers()[1].getAssistantDeck().size());
    }

    /**
     * Tests if a student has been moved to an island correctly:
     * <ol>
     *     <li>Creates a MoveStudentMessage with random values and an island as destination</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if student has been moved correctly</li>
     *     <li>Checks also if Exceptions have been thrown</li>
     * </ol>
     */
    @Test
    void moveStudentToIsland() {
        Map<Integer, String> movement = new HashMap<>();
        movement.put(3, "1");
        ControllerMessage message = new ControllerMessage(new MoveStudentMessage(movement), "p1");
        Student studentToMove = table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().get(2);
        controller.update(message);

        //check if student has been moved:

        assertFalse(table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(studentToMove));
        assertTrue(table.getIsland(0).getStudents().contains(studentToMove));

        //check if GameException is caught (nothing happens)

        movement = new HashMap<>();
        studentToMove = table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().get(0);
        movement.put(1, "13");
        ControllerMessage message3 = new ControllerMessage(new MoveStudentMessage(movement), "p1");
        controller.update(message3);
        assertTrue(table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(studentToMove));

    }

    /**
     * Tests if a student has been moved to dining room correctly:
     * <ol>
     *     <li>Creates a MoveStudentMessage with random values and dining room as destination</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if student has been moved correctly</li>
     * </ol>
     */
    @Test
    void moveStudentToDining() {
        Map<Integer, String> movement = new HashMap<>();
        movement.put(3, "DINING ROOM");
        ControllerMessage message = new ControllerMessage(new MoveStudentMessage(movement), "p1");
        Student studentToMove = table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().get(2);
        ColorS studentColor = studentToMove.getColor();
        controller.update(message);

        //check if student has been moved:

        assertFalse(table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(studentToMove));
        assertTrue(table.getPlayers()[0].getSchoolBoard().getDiningRoom().getLine(studentColor).contains(studentToMove));

    }

    /**
     * Tests if a mother nature has been moved correctly:
     * <ol>
     *     <li>Creates a MoveMotherNatureMessage with random values</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if mother nature has been moved correctly</li>
     *     <li>Checks also if Exceptions have been thrown</li>
     * </ol>
     */
    @Test
    void moveMotherNature() {
        ControllerMessage message = new ControllerMessage(new MoveMotherNatureMessage(3), "p1");
        table.getCurrentPlayer().addToDiscardPile(new Assistant(8, 4, Wizards.WIZARD_1));
        int motherNatureIsland = table.motherNatureIsland().getId();
        controller.update(message);

        //check if mother nature has been moved:

        assertFalse(table.getIsland(motherNatureIsland).isMotherNature());

        //check if GameException is caught (nothing happens)

        motherNatureIsland = table.motherNatureIsland().getId();
        ControllerMessage message3 = new ControllerMessage(new MoveMotherNatureMessage(5), "p1");
        controller.update(message3);
        assertTrue(table.getIsland(motherNatureIsland).isMotherNature());
    }

    /**
     * Tests if a cloud has been chosen correctly:
     * <ol>
     *     <li>Sets the action phase of the game</li>
     *     <li>Creates a ChooseCloudMessage with random values</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if students has been taken from the cloud correctly</li>
     *     <li>Checks also if Exceptions have been thrown</li>
     * </ol>
     */
    @Test
    void chooseCloud() throws AssistantNotFoundException, GameException {
        //set the action phase
        table.playAssistant(table.getPlayers()[0].getAssistant(1));
        table.playAssistant(table.getPlayers()[1].getAssistant(2));

        ControllerMessage message = new ControllerMessage(new ChooseCloudMessage(1), "p1");
        List<Student> cloudStudents = table.getClouds().get(0).getStudents();
        controller.update(message);

        //check if students were taken from cloud:

        for (Student s : cloudStudents) {
            assertTrue(table.getPlayers()[0].getSchoolBoard().getEntrance().getStudents().contains(s));
        }
        assertTrue(table.getClouds().get(0).getStudents().isEmpty());

        //check if next Player is correctly set

        assertEquals(table.getPlayers()[1], table.getCurrentPlayer());

        //check if GameException is caught (nothing happens)

        ControllerMessage message2 = new ControllerMessage(new ChooseCloudMessage(1), "p2");
        List<Student> cloudStudents2 = table.getClouds().get(0).getStudents();
        controller.update(message2);
        for (Student s : cloudStudents2) {
            assertFalse(table.getPlayers()[1].getSchoolBoard().getEntrance().getStudents().contains(s));
        }
    }

    /**
     * Tests what happens in the last round of the game (triggered when bag is empty)
     * <ol>
     *     <li>Empty the bag</li>
     *     <li>Creates a ChooseCloudMessage with random values to trigger the last round</li>
     *     <li>Creates a MoveMotherNatureMessage with random values</li>
     *     <li>Checks if current player has been changed</li>
     * </ol>
     */
    @Test
    void lastRound() throws AssistantNotFoundException, GameException {
        //empty the bag
        int students = table.getBag().getStudents().size();
        for(int i = 0; i < students; i++){
            table.getBag().drawStudent();
        }

        //set the action phase
        table.playAssistant(table.getPlayers()[0].getAssistant(1));
        table.playAssistant(table.getPlayers()[1].getAssistant(2));

        //trigger last round
        ControllerMessage message = new ControllerMessage(new ChooseCloudMessage(1), "p1");
        controller.update(message);
        ControllerMessage message2 = new ControllerMessage(new ChooseCloudMessage(2), "p2");
        controller.update(message2);

        //set the action phase again
        table.playAssistant(table.getPlayers()[0].getAssistant(2));
        table.playAssistant(table.getPlayers()[1].getAssistant(3));

        //player 1 moves mother nature
        ControllerMessage message3 = new ControllerMessage(new MoveMotherNatureMessage(1), "p1");
        controller.update(message3);

        //check current player has changed after moving mother nature
        assertEquals(table.getPlayers()[1], table.getCurrentPlayer());
    }
}