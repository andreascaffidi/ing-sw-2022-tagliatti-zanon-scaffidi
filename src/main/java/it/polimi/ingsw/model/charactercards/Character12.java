package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

public class Character12 implements TypeOfCard {

    private static final int NUM_OF_STUDENTS = 3;
    private ColorS color;

    public Character12() {
        this.color = null;
    }

    /**
     * every Player should return three Students of the color chosen from their DiningRoom to the Bag
     * if any Player has less than 3 Students of that color, places all their Students into the Bag
     * @param table
     */

    @Override
    public void effect(TableExpertMode table)
    {
        //Chi avesse meno di 3 studenti di quel colore, rimetterà tutti quelli che ha
        //notify view scegli colore
        for (Player p : table.getPlayers()){
            for (int i = 0; i < NUM_OF_STUDENTS; i++){
                //TODO: implementare expert mode di schoolboard
                Student student = p.getSchoolBoard().getDiningRoom().removeStudent(color);
                table.getBag().addStudent(student);
            }
        }
    }

    /**
     * doesn't need setup
     * @param table
     */

    @Override
    public void setup(TableExpertMode table) {

    }

    public void setColor(ColorS color) {
        this.color = color;
    }

    public ColorS getColor() {
        return color;
    }
}
