package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;

/**
 * character card effect that gives the control of a professor even if the number of students is the same
 */
public class ProfessorTieEffect implements Effect, ProfessorOwnerEffect {

    private final Player effectOwner;

    /**
     * builds "ProfessorTie" effect
     * @param player player who creates the effect
     */
    public ProfessorTieEffect(Player player){
        this.effectOwner = player;
    }

    /**
     * implements ProfessorOwner interface
     * modifies the count of students to choose the professor's owner adding a student
     * @param students number of students to modify
     * @param color professor's color
     * @param player students' owner
     * @return modified number of students
     */
    @Override
    public int professorOwnerEffect(int students, ColorS color, Player player) {
        if (player.equals(effectOwner)){
            students++;
        }
        return students;
    }

    /**
     * override of toString() method
     * @return specified effect
     */
    @Override
    public String toString() {
        return effectOwner.getUsername() + " gets professors even in parity cases";
    }
}
