package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    private Professor prof;

    @BeforeEach
    void init(){
        prof = new Professor(ColorS.BLUE);
    }

    @Test
    void getColor() {
        assertEquals(ColorS.BLUE, prof.getColor());
    }

    @Test
    void setAndGetOwner() {
        Player owner = new Player("nickname");
        prof.setOwner(owner);
        assertEquals(owner, prof.getOwner());
    }

    @AfterEach
    void tearDown(){
        this.prof = null;
    }

}