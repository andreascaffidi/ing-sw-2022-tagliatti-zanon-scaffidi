package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character5 implements TypeOfCard {

    private int islandChosen;
    private int numOfEntryTile;
    private static final int MAX_ENTRY_TILE = 4;

    public Character5() {
        this.islandChosen = 0;
        this.numOfEntryTile = 0;
    }

    /**
     * If the number of NoEntry tiles placed on the Islands is <4, sets the boolean attribute entryTile of a chosen Island = true
     * and increments the number of NoEntry tiles placed.
     * else does nothing.
     * @param table
     */

    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegliere isola
        if (this.numOfEntryTile < MAX_ENTRY_TILE){
            table.getIsland(islandChosen).setEntryTile(true);
            this.numOfEntryTile++;
            //todo: numOfEntryTile va decrementato in quache modo
        }
    }

    /**
     * doesn't need setup
     * @param table
     */

    @Override
    public void setup(TableExpertMode table)
    {

    }

    public void setIslandChosen(int islandChosen) {
        this.islandChosen = islandChosen;
    }

    public int getIslandChosen() {
        return islandChosen;
    }

    public int getNumOfEntryTile() {
        return numOfEntryTile;
    }

    public void setNumOfEntryTile(int numOfEntryTile) {
        this.numOfEntryTile = numOfEntryTile;
    }
}
