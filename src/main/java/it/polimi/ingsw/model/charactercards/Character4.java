package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;

public class Character4 extends Character {

    private int additionalMovements;

    public Character4() {
        super("Character4",1);
        this.additionalMovements=0;
    }

    /**
     * adds zero, one or two steps to the movement of motherNature
     * @param table
     */
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

    /**
     * doesn't need setup
     * @param table
     */

    @Override
    public void setup(TableExpertMode table) {

    }

    public void setAdditionalMovements(int additionalMovements) {
        this.additionalMovements = additionalMovements;
    }

    public int getAdditionalMovements() {
        return additionalMovements;
    }
}
