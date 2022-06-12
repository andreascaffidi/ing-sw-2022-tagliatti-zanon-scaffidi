package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.islands.Island;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalInfluenceEffectTest {

    private AdditionalInfluenceEffect additionalInfluenceEffect;

    /**
     *  Initialises an AdditionalInfluenceEffect
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void setUp() {
        Player player = new Player("p1");
        additionalInfluenceEffect = new AdditionalInfluenceEffect(player);
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        additionalInfluenceEffect = null;
    }

    /**
     * Tests if influence has been changed for the effect owner and has become the same for the others
     */
    @Test
    void influenceEffect() {
        int influence = 3;

        //for this effect island doesn't need
        assertEquals(3, additionalInfluenceEffect.influenceEffect(influence, new Island(1), new Player("p2")));
        assertEquals(5, additionalInfluenceEffect.influenceEffect(influence, new Island(1), new Player("p1")));
    }

    /**
     * Tests if prints the correct effect string
     */
    @Test
    void testToString() {
        String result = "p1 has 2 more additional influence in this round";
        assertEquals(result, additionalInfluenceEffect.toString());
    }
}