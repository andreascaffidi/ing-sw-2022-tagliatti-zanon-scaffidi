package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;

public interface ProfessorOwnerEffect {
    int professorOwnerEffect(int students, ColorS color, Player player);
}
