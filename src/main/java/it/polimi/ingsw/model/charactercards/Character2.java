package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;

public class Character2 extends Character {

    public Character2(){
        super("Character2",2);
    }

    /**
     * sets boolean attribute of Table ProfessorTie = true
     * @param table
     */
    @Override
    public void effect(TableExpertMode table){
        table.setProfessorTie(table.getCurrentPlayer(), true);
    }

    /**
     * doesn't need setup
     * @param table
     */
    @Override
    public void setup(TableExpertMode table){

    }
}
