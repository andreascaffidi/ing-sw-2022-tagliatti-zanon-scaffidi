package it.polimi.ingsw.network.requests.setupMessages;

import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.requests.SetupExecute;
import it.polimi.ingsw.network.server.Connection;

public class CreateLobbyMessage implements RequestMessage, SetupExecute {
    private String host;
    private String gameMode;
    private int numOfPlayers;

    public CreateLobbyMessage(String host, String gameMode, int numOfPlayers) {
        this.host = host;
        this.gameMode = gameMode;
        this.numOfPlayers = numOfPlayers;
    }

    @Override
    public void execute(Connection connection) {
        connection.lobbySettings(this);
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getHost() {
        return host;
    }
}
