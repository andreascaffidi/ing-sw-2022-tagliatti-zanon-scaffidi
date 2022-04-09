package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.islands.Island;

public class Effect5 implements TypeOfEffect, SupremacyEffect{

    private Island noEntryTileIsland;

    public Effect5(Island island){
        this.noEntryTileIsland = island;
    }

    @Override
    public void supremacyEffect(Island island) {

    }
}
