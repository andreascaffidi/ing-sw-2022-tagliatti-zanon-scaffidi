package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.RoundPhases;
import it.polimi.ingsw.model.enums.Wizards;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnManagerTest {

    /**
     * Tests if the next player is chosen correctly
     * <ol>
     *     <li>Adds an assistant card to each player to set the planning order</li>
     *     <li>Calls nextPlayer() both in planning and action phases</li>
     *     <li>Checks if the next player is correct</li>
     * </ol>
     */
    @Test
    void nextPlayer() {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Player p3 = new Player("p3");
        p1.addToDiscardPile(new Assistant(2, 1, Wizards.WIZARD_1));
        p2.addToDiscardPile(new Assistant(1, 1, Wizards.WIZARD_1));
        p3.addToDiscardPile(new Assistant(2, 1, Wizards.WIZARD_1));
        List<Player> players = new ArrayList<>(Arrays.asList(p1,p2,p3));
        TurnManager turnManager = new TurnManager(players);
        turnManager.orderPlayer(p1);
        turnManager.orderPlayer(p2);
        turnManager.orderPlayer(p3);
        assertEquals(p2, turnManager.nextPlayer());
        assertEquals(p3, turnManager.nextPlayer());
        assertEquals(p2, turnManager.nextPlayer());
        assertEquals(p1, turnManager.nextPlayer());
        assertEquals(p3, turnManager.nextPlayer());
        assertEquals(p2, turnManager.nextPlayer());
        assertEquals(p3, turnManager.nextPlayer());
        assertEquals(p1, turnManager.nextPlayer());
    }

    /**
     * Tests if return the correct current phase of the game
     * <ol>
     *     <li>Sets a random order for planning phase</li>
     *     <li>The first phase of the game is planning</li>
     *     <li>Than after two nextPlayer() the phase is action</li>
     * </ol>
     */
    @Test
    void getCurrentPhase(){
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        List<Player> players = new ArrayList<>(Arrays.asList(p1,p2));
        TurnManager turnManager = new TurnManager(players);
        p1.addToDiscardPile(new Assistant(2, 1, Wizards.WIZARD_1));
        p2.addToDiscardPile(new Assistant(1, 1, Wizards.WIZARD_1));
        turnManager.orderPlayer(p1);
        turnManager.orderPlayer(p2);
        turnManager.nextPlayer();
        assertEquals(RoundPhases.PLANNING, turnManager.getCurrentPhase());

        turnManager.nextPlayer();
        assertEquals(RoundPhases.ACTION, turnManager.getCurrentPhase());
    }
}