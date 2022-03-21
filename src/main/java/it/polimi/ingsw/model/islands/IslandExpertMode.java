package it.polimi.ingsw.model.islands;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;

public class IslandExpertMode extends Island{
    private boolean entryTile;
    private boolean countTowers;

    public IslandExpertMode(int id){
        super(id);
        this.entryTile=false;
        this.countTowers=false;
    }

    public void setEntryTile(boolean entryTile) {
        this.entryTile = entryTile;
    }

    @Override
    public Player getSupremacy(){

    }
}
