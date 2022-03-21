package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character5 implements TypeOfCard {

    private int islandChosen;
    private int numOfEntryTile;
    private static final int MAX_ENTRY_TILE = 4;

    public Character5() {
        this.islandChosen=0;
        this.numOfEntryTile=0;
    }

    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegliere isola
        if (this.numOfEntryTile < MAX_ENTRY_TILE){
            table.getIsland(islandChosen).setEntryTile(true);
            this.numOfEntryTile++;
        }
    }

    @Override
    public void setup(TableExpertMode table)
    {

    }

    public void setIslandChosen(int islandChosen) {
        this.islandChosen = islandChosen;
    }
}
