package it.polimi.ingsw.model.islands;
import java.util.*;

public class IslandGroup extends Island{
    private List<Island> islands;

    public IslandGroup(int id, List<Island> islands){
        super(id);
        this.islands=islands;
    }
}
