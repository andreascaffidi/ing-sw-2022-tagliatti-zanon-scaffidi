package it.polimi.ingsw.model.islands;

import java.util.*;

public class IslandGroup extends Island{
    private List<Island> islands;

    public IslandGroup(int id, List<Island> islands){
        super(id);
        this.islands=islands;
    }

    /**
     * This Override is useful for table.getSupremacy() method to count the influence of towers on the islands
     * @return number of towers on the group of islands
     */
    @Override
    public int numOfTowers(){
        return islands.size();
    }

}
