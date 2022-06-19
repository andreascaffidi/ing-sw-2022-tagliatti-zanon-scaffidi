package it.polimi.ingsw.network.client.reducedModel;


import java.io.Serializable;
import java.util.List;

/**
 * reduced serializable version of the entire model
 */
public class ReducedModel implements Serializable {

    private final List<ReducedIsland> islands;
    private final List<ReducedCloud> clouds;
    private final String currentPlayer;
    private final List<ReducedBoard> boards;

    /**
     * builds a reduced model
     * @param islands reduced islands
     * @param clouds reduced clouds
     * @param currentPlayer current player's username
     * @param boards reduced boards
     */
    public ReducedModel(List<ReducedIsland> islands, List<ReducedCloud> clouds,
                        String currentPlayer, List<ReducedBoard> boards) {
        this.islands = islands;
        this.clouds = clouds;
        this.currentPlayer = currentPlayer;
        this.boards = boards;
    }

    /**
     * gets reduced boards
     * @return reduced boards
     */
    public List<ReducedBoard> getBoards() {
        return boards;
    }

    /**
     * gets reduced clouds
     * @return reduced clouds
     */
    public List<ReducedCloud> getClouds() {
        return clouds;
    }

    /**
     * gets reduced islands
     * @return reduced islands
     */
    public List<ReducedIsland> getIslands() {
        return islands;
    }

    /**
     * gets current player's username
     * @return current player's username
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * gets a board of a specific player
     * @param player board's owner
     * @return reduced board of player
     */
    public ReducedBoard getBoard(String player){
        return getBoards().stream()
                .filter(b -> b.getPlayer().equals(player))
                .findFirst().orElse(null);
    }
}
