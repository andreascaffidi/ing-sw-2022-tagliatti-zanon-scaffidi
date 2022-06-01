package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class purpose is to test all methods of the Tower class
 */
class TowerTest {

    /**
     * Initialises a new Tower and tests that the method returns the correct owner
     */
    @Test
    void getOwner() {
        Player owner = new Player("nickname");
        Tower tower = new Tower(ColorT.BLACK, owner);
        assertEquals(owner, tower.getOwner());
    }
}