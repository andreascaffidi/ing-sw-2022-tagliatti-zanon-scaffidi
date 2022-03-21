package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character8 implements TypeOfCard {

    private Player player;

    public Character8() {
        this.player=null;
    }

    @Override
    public void effect(TableExpertMode table)
    {
        //TODO: In questo turno, durante il calcolo dell'influenza hai 2 punti di influenza addizionali
        game.getCurrentPlayer().setAdditionalInfluence(true);
    }

    @Override
    public void setup(TableExpertMode table) {

    }

}
