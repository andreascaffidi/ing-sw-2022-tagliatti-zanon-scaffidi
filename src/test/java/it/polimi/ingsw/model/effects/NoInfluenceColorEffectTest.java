package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class NoInfluenceColorEffectTest {

    private NoInfluenceColorEffect noInfluenceColorEffect;
    private Island island;

    /**
     *  Initialises NoInfluenceColorEffect and an island
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void setUp() {
        noInfluenceColorEffect = new NoInfluenceColorEffect(ColorS.BLUE);
        island = new Island(4);
        island.addStudent(new Student(ColorS.BLUE));
        island.addStudent(new Student(ColorS.BLUE));
        island.addStudent(new Student(ColorS.BLUE));
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        noInfluenceColorEffect = null;
    }

    /**
     * Tests if influence isn't counted for a specific color
     */
    @Test
    void influenceEffect() {
        int influence = 10;
        Player player = new Player("p1");
        player.setSchoolBoard(new SchoolBoard());
        player.getSchoolBoard().getProfessorTable().addProfessor(new Professor(ColorS.BLUE));

        Player player2 = new Player("p2");
        player2.setSchoolBoard(new SchoolBoard());

        assertEquals(10, noInfluenceColorEffect.influenceEffect(influence, new Island(1), player));
        assertEquals(7, noInfluenceColorEffect.influenceEffect(influence, island, player));
        assertEquals(10, noInfluenceColorEffect.influenceEffect(influence, new Island(1), player2));
        assertEquals(10, noInfluenceColorEffect.influenceEffect(influence, island, player2));
    }

    /**
     * Tests if prints the correct effect string
     */
    @Test
    void testToString() {
        String result = "BLUE has no influence in this round";
        assertEquals(result, noInfluenceColorEffect.toString());
    }
}