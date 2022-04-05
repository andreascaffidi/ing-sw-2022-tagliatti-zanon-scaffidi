package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.Wizards;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnManagerTest {

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
}