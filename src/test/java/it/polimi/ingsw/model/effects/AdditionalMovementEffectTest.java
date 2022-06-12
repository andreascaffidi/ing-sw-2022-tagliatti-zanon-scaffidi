package it.polimi.ingsw.model.effects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalMovementEffectTest {

    private AdditionalMovementEffect additionalMovementEffect;

    /**
     *  Initialises AdditionalMovementEffect
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void setUp() {
        additionalMovementEffect = new AdditionalMovementEffect(4);
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        additionalMovementEffect = null;
    }

    /**
     * Tests if mother nature has been moved correctly
     */
    @Test
    void motherNatureEffect() {
        assertEquals(5, additionalMovementEffect.motherNatureEffect(1));
    }

    /**
     * Tests if prints the correct effect string
     */
    @Test
    void testToString() {
        String result = "Current player can move mother nature up to 4 movements more";
        assertEquals(result, additionalMovementEffect.toString());
    }
}