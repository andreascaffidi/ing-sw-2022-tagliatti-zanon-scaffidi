package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;

public class ProfessorTieEffect implements TypeOfEffect, ProfessorOwnerEffect {

    private final Player effectOwner;

    public ProfessorTieEffect(Player player){
        this.effectOwner = player;
    }

    @Override
    public int professorOwnerEffect(int students, ColorS color, Player player) {
        if (player.equals(effectOwner)){
            students++;
        }
        return students;
    }
}
