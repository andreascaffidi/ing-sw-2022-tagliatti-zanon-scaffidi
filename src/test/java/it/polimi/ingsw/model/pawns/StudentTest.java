package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.enums.ColorS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getColor() {
        Student student = new Student(ColorS.BLUE);
        assertEquals(ColorS.BLUE, student.getColor());
    }

}