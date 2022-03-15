package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.islands.Island;

public class MotherNature {
    private Island island;

    public MotherNature(Island island){
        this.island=island;
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }
}
