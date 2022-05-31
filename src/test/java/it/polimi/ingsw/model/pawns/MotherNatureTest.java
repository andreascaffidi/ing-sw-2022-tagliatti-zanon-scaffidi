package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.islands.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class purpose is to test all methods of the MotherNature class
 */
class MotherNatureTest {

    /**
     * Tests that the island associated is setted (and so getted) correctly;
     */
    @Test
    void setAndGetIsland() {
        int id1 = 0, id2 = 0;
        Island island1 = new Island(id1);
        Island island2 = new Island(id2);
        MotherNature motherNature = new MotherNature(island1);
        motherNature.setIsland(island2);
        assertEquals(island2, motherNature.getIsland());
    }

}