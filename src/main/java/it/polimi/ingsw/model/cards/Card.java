package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.exceptions.InvalidCharacterException;
import it.polimi.ingsw.exceptions.InvalidStudentException;
import it.polimi.ingsw.model.pawns.Student;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private List<Student> students = new ArrayList<>();
    private int character;

    public Card(List<Student> students, int character) {
        this.students = students;
        this.character = character;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getCharacter() {
        return character;
    }

    public void validStudent(int id) throws InvalidStudentException {
        if(id < 0 || id >= students.size())
        {
            throw new InvalidStudentException("Invalid Student");
        }
    }
}
