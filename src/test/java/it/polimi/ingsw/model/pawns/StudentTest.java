package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.enums.ColorS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class purpose is to test all methods of the Student class
 */
class StudentTest {

    /**
     * Initialises a new student and tests that the method returns the correct student
     */
    @Test
    void getColor() {
        Student student = new Student(ColorS.BLUE);
        assertEquals(ColorS.BLUE, student.getColor());
    }

}