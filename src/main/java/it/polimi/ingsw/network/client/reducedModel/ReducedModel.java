package it.polimi.ingsw.network.client.reducedModel;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReducedModel implements Serializable {

    private List<ReducedIsland> islands;
    private List<ReducedCloud> clouds;
    private String currentPlayer;
    //TODO convertire in mappa -> username : board
    private List<ReducedBoard> boards;

    public ReducedModel(List<ReducedIsland> islands, List<ReducedCloud> clouds,
                        String currentPlayer, List<ReducedBoard> boards) {
        this.islands = islands;
        this.clouds = clouds;
        this.currentPlayer = currentPlayer;
        this.boards = boards;
    }

    public List<ReducedBoard> getBoards() {
        return boards;
    }

    public List<ReducedCloud> getClouds() {
        return clouds;
    }

    public List<ReducedIsland> getIslands() {
        return islands;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}
