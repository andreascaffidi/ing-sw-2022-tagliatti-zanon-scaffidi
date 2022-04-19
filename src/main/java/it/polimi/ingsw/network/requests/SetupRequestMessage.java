package it.polimi.ingsw.network.requests;

import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketConnection;

import java.io.Serializable;

public interface SetupRequestMessage extends Serializable {
    void setSocketConnection(SocketConnection socketConnection);
    void execute(Server server);
}
