package it.polimi.ingsw.network.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lobby implements Serializable {
    private String host;
    private String gameMode;
    private int numOfPlayers;
    private int numOfConnection;

    private Map<Integer, List<String>> teams;

    public Lobby(String host, String gameMode, int numOfPlayers){
        this.host = host;
        this.gameMode = gameMode;
        this.numOfPlayers = numOfPlayers;
        this.numOfConnection = 1;
        this.teams = new HashMap<>();
    }

    public void addConnection(){this.numOfConnection++;}

    public void removeConnection(){this.numOfConnection--;}

    public String getHost() {
        return host;
    }

    public String getGameMode() {
        return gameMode;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int getNumOfConnection() {
        return numOfConnection;
    }

    public void setTeams(int team, List<String> players){
        teams.put(team, players);
    }

    public List<String> getPlayersByTeam(int team){
        return teams.get(team);
    }
}
