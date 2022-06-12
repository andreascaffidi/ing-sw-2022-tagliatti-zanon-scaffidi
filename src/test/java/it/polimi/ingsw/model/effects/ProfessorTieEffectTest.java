package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTieEffectTest {

    private ProfessorTieEffect professorTieEffect;
    private Player player;

    /**
     *  Initialises ProfessorTieEffect and a player
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void setUp() {
        player = new Player("p1");
        professorTieEffect = new ProfessorTieEffect(player);
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        professorTieEffect = null;
        player = null;
    }

    /**
     * Tests if counts another student for professor owner
     */
    @Test
    void professorOwnerEffect() {
        int students = 3;

        assertEquals(3, professorTieEffect.professorOwnerEffect(students, ColorS.RED, new Player("p2")));
        assertEquals(4, professorTieEffect.professorOwnerEffect(students, ColorS.RED, player));
    }

    /**
     * Tests if prints the correct effect string
     */
    @Test
    void testToString() {
        String result = "p1 gets professors even in parity cases";
        assertEquals(result, professorTieEffect.toString());
    }

}