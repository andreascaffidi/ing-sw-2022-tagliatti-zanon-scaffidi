package it.polimi.ingsw.model.islands;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IslandExpertModeTest {
    IslandExpertMode island;
    @BeforeEach
    void init() {
        int randomId = new Random().nextInt(12);
        island = new IslandExpertMode(randomId);
        assertEquals(false,island.isCountTowers());
        assertEquals(false,island.isEntryTile());
    }

    @Test
    void isEntryTile() {
        boolean entryTile = new Random().nextBoolean();
        island.setEntryTile(entryTile);
        assertEquals(entryTile,island.isEntryTile());
    }

    @Test
    void setEntryTile() {
        boolean entryTile = new Random().nextBoolean();
        island.setEntryTile(entryTile);
        assertEquals(entryTile,island.isEntryTile());
    }

    @Test
    void isCountTowers() {
        boolean countTowers = new Random().nextBoolean();
        island.setCountTowers(countTowers);
        assertEquals(countTowers,island.isCountTowers());
    }

    @Test
    void setCountTowers() {
        boolean countTowers = new Random().nextBoolean();
        island.setCountTowers(countTowers);
        assertEquals(countTowers,island.isCountTowers());
    }


}