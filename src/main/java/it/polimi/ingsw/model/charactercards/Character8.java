package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;

public class Character8 extends Character {


    public Character8() {
        super("Character8",2);
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
