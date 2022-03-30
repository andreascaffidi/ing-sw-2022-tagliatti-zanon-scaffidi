package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

public class Character12 extends Character {

    private static final int NUM_OF_STUDENTS = 3;
    private ColorS color;

    public Character12() {
        super("Character12",3);
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
        for (Player p : table.getPlayers())
        {
            if (p.getSchoolBoard().getDiningRoom().getLine(this.color).size() < NUM_OF_STUDENTS) {
                int n = p.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(this.color);
                for (int i = 0; i < n; i++) {
                    table.getBag().addStudent(p.getSchoolBoard().getDiningRoom().removeStudent(this.color));
                }
            } else {
                for (int i = 0; i < NUM_OF_STUDENTS; i++) {
                    Student student = p.getSchoolBoard().getDiningRoom().removeStudent(color);
                    table.getBag().addStudent(student);
                }

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
