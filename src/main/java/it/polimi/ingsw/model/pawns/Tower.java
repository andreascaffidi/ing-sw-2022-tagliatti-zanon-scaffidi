package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.Player;

/**
 * tower pawn
 */
public class Tower {
    private final ColorT color;
    private final Player owner;

    /**
     * builds tower pawn
     * @param color tower color
     * @param owner tower owner
     */
    public Tower(ColorT color, Player owner){
        this.color=color;
        this.owner=owner;
    }

    /**
     * gets tower owner
     * @return tower owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * gets tower color
     * @return tower color
     */
    //FIXME: only used in tests
    public ColorT getColor() {
        return color;
    }
}
