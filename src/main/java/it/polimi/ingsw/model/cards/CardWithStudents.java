package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.exceptions.InvalidCardStudentException;
import it.polimi.ingsw.model.pawns.Student;

import java.util.*;

public class CardWithStudents {

    private List<Student> students;
    private int character;

    public CardWithStudents(List<Student> students, int character) {
        this.students = students;
        this.character = character;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getCharacter() {
        return character;
    }

    public void validStudent(int id) throws InvalidCardStudentException {
        if(id < 0 || id >= students.size())
        {
            throw new InvalidCardStudentException("Invalid Student");
        }
    }
}
