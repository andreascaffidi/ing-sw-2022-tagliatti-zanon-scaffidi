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

    public boolean isCountTowers() {
        return countTowers;
    }

    public void setCountTowers(boolean countTowers) {
        this.countTowers = countTowers;
    }

    public boolean isEntryTile() {
        return entryTile;
    }
}
