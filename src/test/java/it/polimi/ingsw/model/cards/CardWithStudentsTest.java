package it.polimi.ingsw.model.cards;


import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardWithStudentsTest {

    private List<Student> students;

    /**
     *  Initialises a list of students
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void setup()
    {
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
    }

    /**
     * Tests if return students on the card correctly
     */
    @Test
    void getStudents() {
        CardWithStudents card = new CardWithStudents(students, 3);
        assertEquals(students, card.getStudents());
    }

    /**
     * Tests if return the correct character ID of the card
     */
    @Test
    void getCharacter() {
        CardWithStudents card = new CardWithStudents(students, 3);
        assertEquals(3, card.getCharacter());
    }

    /**
     * Tests if a student on the card is valid
     */
    @Test
    void validStudent() {
        CardWithStudents card = new CardWithStudents(students, 3);
        assertThrows(GameException.class, () -> card.validStudent(4));
    }
}