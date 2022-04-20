package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;

/**
 * interface for professor owner effects
 */
public interface ProfessorOwnerEffect {
    /**
     * modifies the count of students to choose the professor's owner
     * @param students number of students to modify
     * @param color professor's color
     * @param player students' owner
     * @return modified number of students
     */
    int professorOwnerEffect(int students, ColorS color, Player player);
}
