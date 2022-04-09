package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;

public class NoInfluenceColorEffect implements TypeOfEffect, InfluenceEffect{

    private ColorS noInfluenceColor;

    public NoInfluenceColorEffect(ColorS color){
        this.noInfluenceColor = color;
    }

    @Override
    public int influenceEffect(int influence, Island island, Player player) {
        return influence - island.numStudent(noInfluenceColor);
    }
}
