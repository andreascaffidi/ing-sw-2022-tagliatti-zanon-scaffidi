package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;

public class Character5 extends Character {

    private int islandChosen;

    public Character5() {
        super("Character5",2);
        this.islandChosen = 0;
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
        if (table.getNumOfEntryTile() > 0){
            table.setEntryTile(table.getIsland(islandChosen),true);
            table.decrementEntryTile();
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

}
