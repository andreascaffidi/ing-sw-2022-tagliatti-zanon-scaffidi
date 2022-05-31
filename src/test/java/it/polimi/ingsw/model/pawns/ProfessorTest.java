package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class purpose is to test all methods of the Professor class
 */
class ProfessorTest {

    private Professor prof;

    /**
     *  Initialises a new professor.
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init(){
        prof = new Professor(ColorS.BLUE);
    }

    /**
     * Tests if it returns the color setted during initialization
     */
    @Test
    void getColor() {
        assertEquals(ColorS.BLUE, prof.getColor());
    }

    /**
     * Tests that the owner is setted (and so getted) correctly;
     */
    @Test
    void setAndGetOwner() {
        Player owner = new Player("nickname");
        prof.setOwner(owner);
        assertEquals(owner, prof.getOwner());
    }

    /**
     * Sets to null every attribute
     * <br>
     * <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown(){
        this.prof = null;
    }

}