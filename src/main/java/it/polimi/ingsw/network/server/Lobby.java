package it.polimi.ingsw.network.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lobby class (serializable)
 */
public class Lobby implements Serializable {
    private final String host;
    private final String gameMode;
    private final int numOfPlayers;
    private int numOfConnection;

    private final Map<Integer, List<String>> teams;

    /**
     * builds a lobby class
     * @param host host username
     * @param gameMode game mode chosen
     * @param numOfPlayers number of player for the match chosen
     */
    public Lobby(String host, String gameMode, int numOfPlayers){
        this.host = host;
        this.gameMode = gameMode;
        this.numOfPlayers = numOfPlayers;
        this.numOfConnection = 1;
        this.teams = new HashMap<>();
    }

    /**
     * increments the counter of player connected to this lobby
     */
    public void addConnection(){this.numOfConnection++;}

    /**
     * decrements the counter of player connected to this lobby
     */
    public void removeConnection(){this.numOfConnection--;}

    /**
     * gets the host of the lobby
     * @return host username
     */
    public String getHost() {
        return host;
    }

    /**
     * gets the game mode for this lobby
     * @return game mode (normal or expert)
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * gets the number of player chosen for this lobby
     * @return number of player for the match
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * gets the number of player connected to this lobby
     * @return number of connections
     */
    public int getNumOfConnection() {
        return numOfConnection;
    }

    /**
     * sets a team adding a list of players to the team (1 or 2)
     * @param team team tag
     * @param players players of the same team
     */
    public void setTeams(int team, List<String> players){
        teams.put(team, players);
    }

    /**
     * gets a list of players' username of the same team
     * @param team team tag
     * @return players' username
     */
    public List<String> getPlayersByTeam(int team){
        return teams.get(team);
    }
}
