package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.AssistantNotFoundException;
import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private List<Assistant> deck;

    /**
     *  Initialises a new player with deck of 3 assistant cards
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init() {
        Assistant a1 = new Assistant(1,2, Wizards.WIZARD_1);
        Assistant a2 = new Assistant(2,3, Wizards.WIZARD_1);
        Assistant a3 = new Assistant(3,3, Wizards.WIZARD_1);
        deck = new ArrayList<>(Arrays.asList(a1, a2, a3));
        player = new Player("nickname", 1);
        player.setTowerColor(ColorT.BLACK);
        player.getAssistantDeck().addAll(deck);
    }

    /**
     * Sets to null every attribute
     * <br>
     * <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        player = null;
        deck = null;
    }

    /**
     * Tests that the username is set (and so got) correctly;
     */
    @Test
    void getUsername() {
        assertEquals("nickname", player.getUsername());
    }

    /**
     * Tests that the deck of assistant cards set on initialization is the one returned;
     */
    @Test
    void getAssistantDeck() {
        assertEquals(deck, player.getAssistantDeck());
    }

    /**
     * Tests that the school board is set (and so got) correctly;
     */
    @Test
    void setAndGetSchoolBoard() {
        assertTrue(true, "tested in other methods");
        SchoolBoard schoolBoard = new SchoolBoard();
        Player player = new Player("p2");
        player.setSchoolBoard(schoolBoard);
        assertEquals(schoolBoard, player.getSchoolBoard());
    }

    /**
     * Tests if it returns the discard pile of assistant cards
     */
    @Test
    void getDiscardPile() {
        assertEquals(new ArrayList<Assistant>(), player.getDiscardPile());
    }

    /**
     * Tests if it returns the correct team
     */
    @Test
    void getTagTeam() {
        assertEquals(1, player.getTagTeam());
    }

    /**
     * Tests that 2 player object with different usernames
     * are recognized as 2 different player and that 2 player object
     * with the same username are recognized as the same player
     */
    @Test
    void testEquals() {
        Player player2 = new Player("nickname");
        assertEquals(player2, player);
        Player player3 = new Player("username");
        assertNotEquals(player3, player2);
        assertNotEquals(player2, null);
        assertNotEquals(player3, new Object());
    }

    /**
     * Tests that the tower color is set (and so got) correctly;
     */
    @Test
    void setAndGetTowerColor() {
        assertEquals(ColorT.BLACK, player.getTowerColor());
    }

    /**
     * Tests if a card is removed from the assistant deck and added to the assistant discard pile
     */
    @Test
    void addToDiscardPile(){
        Assistant assistant = deck.get(0);
        player.addToDiscardPile(assistant);
        assertTrue(player.getDiscardPile().contains(assistant));
        assertFalse(player.getAssistantDeck().contains(assistant));
    }

    /**
     * Tests if it returns the correct assistant card, it also tests AssistantNotFoundException
     */
    @Test
    void getAssistant() throws AssistantNotFoundException {
        Assistant assistant = player.getAssistant(3);
        assertEquals(3, assistant.getValue());

        //test AssistantNotFound exception
        assertThrows(AssistantNotFoundException.class, () -> player.getAssistant(5));
    }

    /**
     * Tests if a mother nature movement is valid and in case it tests also GameException
     */
    @Test
    void validMovement() throws GameException {
        Assistant assistant = deck.get(0);
        player.addToDiscardPile(assistant);
        player.validMovement(1);
        player.validMovement(2);
        assertThrows(GameException.class, () -> player.validMovement(3));
    }

    /**
     * Tests if it returns the correct reduced board
     *  <ol>
     *      <li>Creates a school board with random values</li>
     *      <li>Verifies if the reduced board has the same values</li>
     *  </ol>
     */
    @Test
    void reduceBoard() {
        SchoolBoard schoolBoard = new SchoolBoard();
        schoolBoard.getDiningRoom().addStudent(new Student(ColorS.BLUE));
        schoolBoard.getDiningRoom().addStudent(new Student(ColorS.RED));
        schoolBoard.getEntrance().addStudent(new Student(ColorS.BLUE));
        schoolBoard.getEntrance().addStudent(new Student(ColorS.RED));
        schoolBoard.getProfessorTable().addProfessor(new Professor(ColorS.BLUE));
        schoolBoard.getProfessorTable().addProfessor(new Professor(ColorS.RED));
        schoolBoard.getTowerBoard().addTower(new Tower(ColorT.BLACK, player));
        player.setSchoolBoard(schoolBoard);
        Assistant discardPile = new Assistant(5, 1, Wizards.WIZARD_1);
        player.getAssistantDeck().add(discardPile);
        player.addToDiscardPile(discardPile);

        ReducedBoard reducedBoard = player.reduceBoard();
        assertEquals("nickname", reducedBoard.getPlayer());
        assertEquals(ColorT.BLACK, reducedBoard.getTowerColor());
        assertTrue(reducedBoard.getStudents().containsKey(ColorS.BLUE));
        assertEquals(1, reducedBoard.getStudents().get(ColorS.BLUE));
        assertTrue(reducedBoard.getEntranceStudents().contains(ColorS.BLUE));
        assertTrue(reducedBoard.getEntranceStudents().contains(ColorS.RED));
        assertFalse(reducedBoard.getEntranceStudents().contains(ColorS.YELLOW));
        assertTrue(reducedBoard.getProfessors().contains(ColorS.BLUE));
        assertTrue(reducedBoard.getProfessors().contains(ColorS.RED));
        assertFalse(reducedBoard.getProfessors().contains(ColorS.YELLOW));
        assertEquals(1, reducedBoard.getNumOfTowers());
        assertEquals(1, reducedBoard.getAssistantDeck().getAssistantCards().get(0).getId());
        assertEquals(5, reducedBoard.getAssistantDeck().getPlayedAssistant().getId());
    }
}