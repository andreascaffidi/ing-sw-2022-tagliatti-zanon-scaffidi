package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;

public interface TypeOfCard {
    public void effect(TableExpertMode table);
    public void setup(TableExpertMode table);
}
