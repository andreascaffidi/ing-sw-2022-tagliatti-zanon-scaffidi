package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.islands.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotherNatureTest {

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