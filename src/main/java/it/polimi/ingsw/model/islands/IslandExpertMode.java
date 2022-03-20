package it.polimi.ingsw.model.islands;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;

public class IslandExpertMode extends Island{
    private boolean entryTile;
    private boolean countTowers;
    private boolean additionalInfluence;            //in realtà sarebbe meglio mettere l'attributo sul playerExpertMode
    private ColorS noInfluenceColor;

    public IslandExpertMode(int id){
        super(id);
        this.entryTile=false;
        this.countTowers=true;
        this.additionalInfluence=false;
        this.noInfluenceColor=null;
    }

    public ColorS getNoInfluenceColor() {
        return noInfluenceColor;
    }

    public boolean isEntryTile(){
        return entryTile;
    }

    public boolean isAdditionalInfluence() {
        return additionalInfluence;
    }

    public boolean isCountTowers() {
        return countTowers;
    }

    public void setAdditionalInfluence(boolean additionalInfluence) {
        this.additionalInfluence = additionalInfluence;
    }

    public void setCountTowers(boolean countTowers) {
        this.countTowers = countTowers;
    }

    public void setEntryTile(boolean entryTile) {
        this.entryTile = entryTile;
    }

    public void setNoInfluenceColor(ColorS noInfluenceColor) {
        this.noInfluenceColor = noInfluenceColor;
    }
}
