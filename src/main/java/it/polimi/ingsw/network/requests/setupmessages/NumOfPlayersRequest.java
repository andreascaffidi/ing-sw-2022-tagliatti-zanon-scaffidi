package it.polimi.ingsw.network.requests.setupmessages;

import it.polimi.ingsw.network.requests.SetupRequestMessage;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketConnection;

public class NumOfPlayersRequest implements SetupRequestMessage {
    private int numOfPlayers;
    private SocketConnection socketConnection;


    public NumOfPlayersRequest(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    @Override
    public void setSocketConnection(SocketConnection socketConnection) {
        this.socketConnection = socketConnection;
    }

    @Override
    public void execute(Server server) {
        server.setNumOfPlayers(numOfPlayers);
    }
}
