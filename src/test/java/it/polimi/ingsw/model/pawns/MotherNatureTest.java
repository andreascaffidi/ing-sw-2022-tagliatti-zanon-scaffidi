package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.islands.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotherNatureTest {

    @Test
    void setAndGetIsland() {
        int id0 = 0;
        int id1 = 1;
        Island island0 = new Island(id0);
        Island island1 = new Island(id1);
        MotherNature motherNature = new MotherNature(island0);
        motherNature.setIsland(island1);
        assertEquals(island1, motherNature.getIsland());
    }

}