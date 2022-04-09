package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.islands.Island;

public class AdditionalInfluenceEffect implements TypeOfEffect, InfluenceEffect {

    private final int additionalInfluence;
    private final Player effectOwner;

    public AdditionalInfluenceEffect(Player effectOwner){
        this.effectOwner = effectOwner;
        this.additionalInfluence = 2;
    }

    @Override
    public int influenceEffect(int influence, Island island, Player player) {
        if (player.equals(effectOwner)){
            influence = influence + additionalInfluence;
        }
        return influence;
    }
}
