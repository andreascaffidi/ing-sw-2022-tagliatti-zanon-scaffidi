package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character2 implements TypeOfCard {

    public Character2(){

    }

    @Override
    public void effect(TableExpertMode table){
        table.setProfessorTie(true);
    }

    @Override
    public void setup(TableExpertMode table){

    }
}
