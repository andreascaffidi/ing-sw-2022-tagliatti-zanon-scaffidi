package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.Player;

public class Professor {
    private ColorS color;
    private Player owner;

    public Professor(ColorS color){
        this.color=color;
        this.owner=null;
    }

    public ColorS getColor() {
        return color;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
