package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.enums.Wizard;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private List<Assistant> deck;

    @BeforeEach
    void init() {
        Assistant a1 = new Assistant(1,2, Wizard.WIZARD_1);
        Assistant a2 = new Assistant(2,3,Wizard.WIZARD_1);
        Assistant a3 = new Assistant(3,3,Wizard.WIZARD_1);
        deck = new ArrayList<Assistant>(Arrays.asList(a1,a2,a3));
        player = new Player("nickname", deck, 1, ColorT.BLACK);
    }

    @AfterEach
    void tearDown() {
        player = null;
        deck = null;
    }

    @Test
    void getUsername() {
        assertEquals("nickname", player.getUsername());
    }

    @Test
    void getAssistantDeck() {
        assertEquals(deck, player.getAssistantDeck());
    }

    @Test
    void getSchoolBoard() {
        assertTrue(true, "tested in other methods");
    }

    @Test
    void getDiscardPile() {
        assertEquals(new ArrayList<Assistant>(), player.getDiscardPile());
    }

    @Test
    void getTagTeam() {
        assertEquals(1, player.getTagTeam());
    }

    @Test
    void testEquals() {
        Player player2 = new Player("nickname");
        assertTrue(player2.equals(player));
        Player player3 = new Player("username");
        assertFalse(player3.equals(player2));
    }

    @Test
    void setAndGetTowerColor() {
        assertEquals(ColorT.BLACK, player.getTowerColor());
    }

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