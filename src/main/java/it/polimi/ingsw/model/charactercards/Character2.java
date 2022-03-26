package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character2 implements TypeOfCard {

    public Character2(){

    }

    /**
     * sets boolean attribute of Table ProfessorTie = true
     * @param table
     */
    @Override
    public void effect(TableExpertMode table){
        table.setProfessorTie(true);
    }

    /**
     * doesn't need setup
     * @param table
     */
    @Override
    public void setup(TableExpertMode table){

    }
}
