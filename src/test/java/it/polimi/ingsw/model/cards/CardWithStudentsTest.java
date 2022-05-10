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
    private int character1;
    private int character2;

    @BeforeEach
    void setup()
    {
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
    }

    @Test
    void getStudents() {
        CardWithStudents card = new CardWithStudents(students, 3);
        assertEquals(students, card.getStudents());
    }

    @Test
    short getCharacter() {
        CardWithStudents card = new CardWithStudents(students, 3);
        assertEquals(3, this.getCharacter());
        return 0;
    }

    @Test
    void validStudent() {
        CardWithStudents card = new CardWithStudents(students, 3);
        CardWithStudents card2 = new CardWithStudents(students, 1);
        Exception exception = new GameException("Invalid Student");
        exception = assertThrows(GameException.class, () -> card.validStudent(4));
        assertEquals("Invalid Student", exception.getMessage());
    }
}