package it.polimi.ingsw.model.islands;
import java.util.*;

public class IslandGroupExpertMode extends IslandExpertMode{
    private List<IslandExpertMode> islands;

    public IslandGroupExpertMode(int id, List<IslandExpertMode> islands){
        super(id);
        this.islands=islands;
    }
}
