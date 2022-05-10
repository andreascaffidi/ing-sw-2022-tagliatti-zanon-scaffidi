package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.pawns.Student;

import java.util.*;

/**
 * character card with students on it
 */
public class CardWithStudents {

    private final List<Student> students;
    private final int character;

    /**
     * builds a card with students on it
     * @param students students to put on the card
     * @param character character id associated to the card
     */
    public CardWithStudents(List<Student> students, int character) {
        this.students = students;
        this.character = character;
    }

    /**
     * gets all the students on the card
     * @return students on the card
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * gets character id of the card
     * @return character id
     */
    public int getCharacter() {
        return character;
    }

    /**
     * checks if is possible to pick up a student from the card
     * @param id position of the student
     * @throws GameException if the position is invalid
     */
    public void validStudent(int id) throws GameException {
        if(id < 0 || id >= students.size())
        {
            throw new GameException("Invalid Student");
        }
    }
}
