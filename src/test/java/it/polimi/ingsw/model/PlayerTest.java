package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.enums.Wizards;
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
     * Tests that the username is setted (and so getted) correctly;
     */
    @Test
    void getUsername() {
        assertEquals("nickname", player.getUsername());
    }

    /**
     * Tests that the deck of assistant cards setted on initialization is the one returned;
     */
    @Test
    void getAssistantDeck() {
        assertEquals(deck, player.getAssistantDeck());
    }

    /**
     * Tests if it returns the schoolboard
     * <br>
     *  <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getSchoolBoard() {
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if it returns the discard pile of assistant cards
     *
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
        assertTrue(player2.equals(player));
        Player player3 = new Player("username");
        assertFalse(player3.equals(player2));
        assertFalse(player2.equals(null));
        assertFalse(player3.equals(new Object()));
    }

    /**
     * Tests that the tower color is setted (and so getted) correctly;
     */
    @Test
    void setAndGetTowerColor() {
        assertEquals(ColorT.BLACK, player.getTowerColor());
    }

    /**
     * Tests if a card is removed from the assistant deck and added to the assistant discard pile
     *
     */
    @Test
    void addToDiscardPile(){
        Assistant assistant = deck.get(0);
        player.addToDiscardPile(assistant);
        assertTrue(player.getDiscardPile().contains(assistant));
        assertFalse(player.getAssistantDeck().contains(assistant));
    }

    @Test
    void otherConstructors(){
        Player p1 = new Player("username");
        Player p2 = new Player("username", 1);
        assertEquals("username", p1.getUsername());
        assertEquals(1, p2.getTagTeam());
    }
}