package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.Player;

public class Tower {
    private ColorT color;
    private Player owner;

    public Tower(ColorT color, Player owner){
        this.color=color;
        this.owner=owner;
    }

    public Player getOwner() {
        return owner;
    }

    public ColorT getColor() {
        return color;
    }
}
