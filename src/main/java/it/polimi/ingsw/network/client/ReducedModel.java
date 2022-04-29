package it.polimi.ingsw.network.client;


import java.io.Serializable;
import java.util.Map;

public class ReducedModel implements Serializable {
    private Map <Integer, String> islands;

    public ReducedModel(Map <Integer, String> islands){
        this.islands = islands;
    }

    public Map<Integer, String> getIslands() {
        return islands;
    }
}
