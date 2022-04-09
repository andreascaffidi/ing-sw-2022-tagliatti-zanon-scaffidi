package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.islands.Island;

public class CountTowersEffect implements TypeOfEffect, InfluenceEffect{

    private Island islandEffect;

    public CountTowersEffect(Island island){
        this.islandEffect = island;
    }

    @Override
    public int influenceEffect(int influence, Island island, Player player) {
        if (islandEffect.equals(island) && island.getTower() != null && player.equals(island.getTower().getOwner())){
            influence -= island.getNumOfTowers();
        }
        return influence;
    }
}
