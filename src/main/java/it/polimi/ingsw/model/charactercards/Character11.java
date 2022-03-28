package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.exceptions.GetCoinException;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;
import it.polimi.ingsw.model.pawns.Student;

import java.util.ArrayList;
import java.util.List;

public class Character11 implements TypeOfCard {

    private static final int NUM_OF_STUDENTS = 4;
    private List<Student> students;
    private Student studentChosen;

    public Character11() {
        this.students = new ArrayList<>();
        this.studentChosen = null;
    }

    /**
     * takes one chosen Student from the Card and places it in the DiningRoom of the CurrentPlayer
     * then draws a Student from the Bag and places it on this Card
     * @param table
     */

    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegli studente carta
        table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(studentChosen);
        this.students.add(table.getBag().drawStudent());
    }

    /**
     * places 4 students on the Card
     * @param table
     */

    @Override
    public void setup(TableExpertMode table) {
        for(int i = 0; i < NUM_OF_STUDENTS; i++)
        {
            this.students.add(table.getBag().drawStudent());
        }
    }

    public void setStudentChosen(Student studentChosen) {
        this.studentChosen = studentChosen;
    }

    public Student getStudentChosen() {
        return studentChosen;
    }

    public List<Student> getStudents() {
        return students;
    }
}
