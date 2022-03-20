package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;

import java.util.List;

public class TableExpertMode extends Table{
    private int bank;
    private Character[] characterCards;
    private boolean professorTie;

    public TableExpertMode(List<Player> players){
        super(players);

        //Pescare carte personaggio
    }

    public void deposit(int coins){
        this.bank += coins;
    }

    @Override
    public Player professorOwner(ColorS color){

    }

    public void resetCardsEffect(){

    }
}
