package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.TableExpertMode;

public interface TypeOfCard {
    /**
     * activates the effect of the Card
     * @param table
     */
    public void effect(TableExpertMode table);

    /**
     * sets up the Card
     * @param table
     */
    public void setup(TableExpertMode table);
}
