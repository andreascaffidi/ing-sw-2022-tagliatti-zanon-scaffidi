package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.exceptions.GetCoinException;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;
import it.polimi.ingsw.model.pawns.Student;

import java.util.List;

public class Character11 implements TypeOfCard {

    private static final int NUM_OF_STUDENTS = 4;
    private List<Student> students;
    private Student studentChosen;

    public Character11() {
        this.students = null;
        this.studentChosen = null;
    }

    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegli studente carta
        try{
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(studentChosen);
        }catch(
        GetCoinException exception){
            table.getCurrentPlayer().addCoins(1);
        }
        this.students.add(table.getBag().drawStudent());
    }

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
}
