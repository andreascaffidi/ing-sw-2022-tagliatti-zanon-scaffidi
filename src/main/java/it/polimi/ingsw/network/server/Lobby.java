package it.polimi.ingsw.network.server;

import java.io.Serializable;

public class Lobby implements Serializable {
    private String host;
    private String gameMode;
    private int numOfPlayers;
    private int numOfConnection;

    public Lobby(String host, String gameMode, int numOfPlayers){
        this.host = host;
        this.gameMode = gameMode;
        this.numOfPlayers = numOfPlayers;
        this.numOfConnection = 1;
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
}
