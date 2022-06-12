package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CountTowersEffectTest {

    private CountTowersEffect countTowersEffect;
    private Island island;

    /**
     *  Initialises CountTowersEffect
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void setUp() {
        island = new Island(4);
        island.setTower(new Tower(ColorT.BLACK, new Player("p1")));
        countTowersEffect = new CountTowersEffect(island);
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        countTowersEffect = null;
    }

    /**
     * Tests if towers aren't counted in influence calculation
     */
    @Test
    void influenceEffect() {
        int influence = 3;

        assertEquals(3, countTowersEffect.influenceEffect(influence, new Island(1), new Player("p1")));
        assertEquals(3, countTowersEffect.influenceEffect(influence, island, new Player("p2")));
        assertEquals(2, countTowersEffect.influenceEffect(influence, island, new Player("p1")));
    }

    /**
     * Tests if prints the correct effect string
     */
    @Test
    void testToString() {
        String result = "Towers on island 5 aren't counted for influence in this round";
        assertEquals(result, countTowersEffect.toString());
    }

}