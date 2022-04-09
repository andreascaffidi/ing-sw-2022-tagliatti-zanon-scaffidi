package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;

public class Effect2 implements TypeOfEffect, ProfessorOwnerEffect {

    private Player effectOwner;

    public Effect2(Player player){
        this.effectOwner = player;
    }

    @Override
    public void professorOwnerEffect(ColorS color, Player player) {

    }
}
