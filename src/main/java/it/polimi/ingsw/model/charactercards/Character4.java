package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character4 implements TypeOfCard {

    private int additionalMovements;

    public Character4() {
        this.additionalMovements=0;
    }

    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegli intero da 1 a 2
        //TODO: verificare timing

        /*  Aggiungere intero in tableexpertmode e qunado il controller verificherà
         * il numero massimo di mosse disponibili, terrà conto anche di queste
         * mosse bonus
         */
        table.moveMotherNature(this.additionalMovements);
    }

    @Override
    public void setup(TableExpertMode table) {

    }

    public void setAdditionalMovements(int additionalMovements) {
        this.additionalMovements = additionalMovements;
    }
}
