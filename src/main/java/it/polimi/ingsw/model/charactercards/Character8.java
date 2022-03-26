package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;

public class Character8 implements TypeOfCard {

    private Player player;

    public Character8() {
        this.player=null;
    }

    /**
     * sets boolean attribute AdditionalInfluence of CurrentPlayer = true
     * @param table
     */

    @Override
    public void effect(TableExpertMode table)
    {
        table.getCurrentPlayer().setAdditionalInfluence(true);
    }

    /**
     * doesn't need setup
     * @param table
     */

    @Override
    public void setup(TableExpertMode table) {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
