package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.exceptions.GetCoinException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;
import it.polimi.ingsw.model.pawns.Student;

import java.util.List;

public class Character10 implements TypeOfCard {

    private List<Student> entranceStudentsChosen;
    private List<Student> dinnerRoomStudentsChosen;
    public Character10() {
        this.entranceStudentsChosen = null;
        this.dinnerRoomStudentsChosen = null;
    }

    @Override
    public void effect(TableExpertMode table)
    {
        //TODO: rivedere l'implementazione
        //notify view scegliere studenti sala e studenti ingresso
        for (Student s : dinnerRoomStudentsChosen){
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().removeStudent(s);
            table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(s);
        }
        for (Student s : entranceStudentsChosen){
            table.getCurrentPlayer().getSchoolBoard().getEntrance().removeStudent(s);
            try {
                table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(s);
            }catch(GetCoinException exception){
                table.getCurrentPlayer().addCoins(1);
            }
        }
    }

    @Override
    public void setup(TableExpertMode table) {

    }

    public void setDinnerRoomStudentsChosen(List<Student> dinnerRoomStudentsChosen) {
        this.dinnerRoomStudentsChosen = dinnerRoomStudentsChosen;
    }

    public void setEntranceStudentsChosen(List<Student> entranceStudentsChosen) {
        this.entranceStudentsChosen = entranceStudentsChosen;
    }
}
