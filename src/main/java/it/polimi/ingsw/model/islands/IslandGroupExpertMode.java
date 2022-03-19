package it.polimi.ingsw.model.islands;
import java.util.*;

public class IslandGroupExpertMode extends IslandExpertMode{
    private List<IslandExpertMode> islands;

    public IslandGroupExpertMode(int id, List<IslandExpertMode> islands){
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
