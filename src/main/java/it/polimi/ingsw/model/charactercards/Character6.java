package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character6 implements TypeOfCard {

    private int islandChosen;

    public Character6() {
        islandChosen=0;
    }

    /**
     * sets the boolean attribute setCountTowers of the chosen Island = false
     * @param table
     */
    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegliere isola
        table.getIsland(islandChosen).setCountTowers(false);
    }

    /**
     * doesn't need setup
     * @param table
     */

    @Override
    public void setup(TableExpertMode table) {

    }

    public void setIslandChosen(int islandChosen) {
        this.islandChosen = islandChosen;
    }

    public int getIslandChosen() {
        return islandChosen;
    }
}
