package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;
import it.polimi.ingsw.model.enums.ColorS;

public class Character9 implements TypeOfCard {

    private ColorS color;

    public Character9() {
        this.color=null;
    }

    /**
     * sets the attribute NoInfluenceColor of Table to a chosen Color
     * @param table
     */

    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegli colore
        table.setNoInfluenceColor(color);
    }

    /**
     * doesn't need setup
     * @param table
     */

    @Override
    public void setup(TableExpertMode table) {

    }

    public void setColor(ColorS color) {
        this.color = color;
    }

    public ColorS getColor() {
        return color;
    }
}
