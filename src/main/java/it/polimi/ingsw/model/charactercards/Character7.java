package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;
import it.polimi.ingsw.model.pawns.Student;

import java.util.List;

public class Character7 implements TypeOfCard {

    private List<Student> cardStudents;
    private static final int NUM_OF_STUDENTS = 6;
    private List<Student> cardStudentsChosen;
    private List<Student> entranceStudentChosen;
    private Player player;


    public Character7() {
        this.cardStudents=null;
        this.cardStudentsChosen=null;
        this.entranceStudentChosen=null;
        this.player=null;
    }

    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegliere numero scambi, studenti sulla carta e studenti in entrata
        for (Student s : cardStudentsChosen){
            this.cardStudents.remove(s);
            player.getSchoolBoard().getEntrance().addStudent(s);
        }
        for (Student s : entranceStudentChosen){
            this.cardStudents.add(s);
            player.getSchoolBoard().getEntrance().removeStudent(s);
        }

    }

    @Override
    public void setup(TableExpertMode table) {
        for(int i = 0; i < NUM_OF_STUDENTS; i++)
        {
            this.cardStudents.add(table.getBag().drawStudent());
        }
    }

    public void setCardStudentsChosen(List<Student> cardStudentsChosen) {
        this.cardStudentsChosen = cardStudentsChosen;
    }

    public void setEntranceStudentChosen(List<Student> entranceStudentChosen) {
        this.entranceStudentChosen = entranceStudentChosen;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
