package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.islands.Island;

/**
 * Mother Nature pawn
 */
public class MotherNature {
    private Island island;

    /**
     * builds Mother Nature pawn
     * @param island Mother Nature island
     */
    public MotherNature(Island island){
        this.island=island;
    }

    /**
     * gets Mother Nature island
     * @return Mother Nature island
     */
    public Island getIsland() {
        return island;
    }

    /**
     * sets Mother Nature island
     * @param island Mother Nature island
     */
    public void setIsland(Island island) {
        this.island = island;
    }
}
