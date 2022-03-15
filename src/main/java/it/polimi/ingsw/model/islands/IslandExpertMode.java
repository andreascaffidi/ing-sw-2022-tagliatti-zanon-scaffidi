package it.polimi.ingsw.model.islands;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;

public class IslandExpertMode extends Island{
    private boolean entryTile;
    private boolean countTowers;
    private boolean additionalInfluence;
    private ColorS noInfluenceColor;

    public IslandExpertMode(int id){
        super(id);
        this.entryTile=false;
        this.countTowers=false;
        this.additionalInfluence=false;
        this.noInfluenceColor=null;
    }

}
