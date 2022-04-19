package it.polimi.ingsw.network.requests.setupmessages;

import it.polimi.ingsw.network.requests.SetupRequestMessage;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketConnection;

public class ConnectionRequest implements SetupRequestMessage {
    private String username;
    private SocketConnection socketConnection;

    public ConnectionRequest(String username) {
        this.username = username;
    }

    @Override
    public void setSocketConnection(SocketConnection socketConnection) {
        this.socketConnection = socketConnection;
    }

    @Override
    public void execute(Server server) {
        server.connect(username, socketConnection);
    }
}
