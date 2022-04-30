package it.polimi.ingsw.network.client.reducedModel;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReducedModel implements Serializable {

    private List<ReducedIsland> islands;
    private List<ReducedCloud> clouds;
    private String currentPlayer;
    private List<ReducedBoard> boards;
}
