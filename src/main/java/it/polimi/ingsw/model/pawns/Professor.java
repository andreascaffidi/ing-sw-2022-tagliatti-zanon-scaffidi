package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.Player;

/**
 * professor pawn
 */
public class Professor {
    private final ColorS color;
    private Player owner;

    /**
     * builds professor pawn
     * @param color professor color
     */
    public Professor(ColorS color){
        this.color=color;
        this.owner=null;
    }

    /**
     * gets professor color
     * @return professor color
     */
    public ColorS getColor() {
        return color;
    }

    /**
     * gets professor owner
     * @return professor owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * sets professor owner
     * @param owner professor owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
