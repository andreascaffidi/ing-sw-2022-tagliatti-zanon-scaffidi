package it.polimi.ingsw.network.requests.setupMessages;

import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.requests.SetupExecute;
import it.polimi.ingsw.network.server.Connection;

/**
 * create lobby message
 */
public class CreateLobbyMessage implements RequestMessage, SetupExecute {
    private final String host;
    private final String gameMode;
    private final int numOfPlayers;

    /**
     * builds create lobby message
     * @param host lobby's host (sender of the message)
     * @param gameMode game mode (expert or normal)
     * @param numOfPlayers number of players selected
     */
    public CreateLobbyMessage(String host, String gameMode, int numOfPlayers) {
        this.host = host;
        this.gameMode = gameMode;
        this.numOfPlayers = numOfPlayers;
    }

    /**
     * implements SetupExecute interface
     * executes lobbySettings() method on the connection class
     * @param connection connection
     */
    @Override
    public void execute(Connection connection) {
        connection.lobbySettings(this);
    }

    /**
     * gets the number of players selected
     * @return number of players
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * gets the game mode selected
     * @return game mode selected
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * gets the lobby's host
     * @return lobby's host
     */
    public String getHost() {
        return host;
    }
}
