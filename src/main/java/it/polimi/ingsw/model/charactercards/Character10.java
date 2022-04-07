package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.pawns.Student;

import java.util.List;

public class Character10 extends Character {

    private List<Student> entranceStudentsChosen;
    private List<Student> diningRoomStudentsChosen;
    public Character10() {
        super("Character10",1);
        this.entranceStudentsChosen = null;
        this.diningRoomStudentsChosen = null;
    }

    /**
     * exchanges up to 2 chosen Students between the Entrance and the DiningRoom of the SchoolBoard of the CurrentPlayer
     * @param table
     */
    @Override
    public void effect(TableExpertMode table)
    {
        //TODO: rivedere l'implementazione
        //notify view scegliere studenti sala e studenti ingresso
        for (Student s : diningRoomStudentsChosen){
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().removeStudent(s);
            table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(s);
        }
        for (Student s : entranceStudentsChosen){
            table.getCurrentPlayer().getSchoolBoard().getEntrance().removeStudent(s);
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(s);
            //table.addCoins(table.getCurrentPlayer(), 1);
        }
    }

    /**
     * doesn't need setup
     * @param table
     */
    @Override
    public void setup(TableExpertMode table) {

    }

    public void setDiningRoomStudentsChosen(List<Student> diningRoomStudentsChosen) {
        this.diningRoomStudentsChosen = diningRoomStudentsChosen;
    }

    public void setEntranceStudentsChosen(List<Student> entranceStudentsChosen) {
        this.entranceStudentsChosen = entranceStudentsChosen;
    }

    public List<Student> getEntranceStudentsChosen() {
        return entranceStudentsChosen;
    }

    public List<Student> getDiningRoomStudentsChosen() {
        return diningRoomStudentsChosen;
    }
}
