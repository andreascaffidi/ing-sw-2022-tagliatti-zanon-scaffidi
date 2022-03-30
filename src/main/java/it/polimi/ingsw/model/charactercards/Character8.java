package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character8 implements TypeOfCard {


    public Character8() {

    }

    /**
     * sets boolean attribute AdditionalInfluence of CurrentPlayer = true
     * @param table
     */

    @Override
    public void effect(TableExpertMode table)
    {
        table.setAdditionalInfluence(table.getCurrentPlayer(),true);
    }

    /**
     * doesn't need setup
     * @param table
     */

    @Override
    public void setup(TableExpertMode table) {

    }

}
