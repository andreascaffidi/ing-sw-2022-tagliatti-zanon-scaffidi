package it.polimi.ingsw.network.responses.setupMessages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.server.Lobby;

import java.util.List;

/**
 * show lobby message
 */
public class ShowLobbyMessage implements ResponseMessage, ClientExecute {

    private final List<Lobby> lobbies;

    /**
     * builds show lobby message
     * @param lobbies lobbies to show
     */
    public ShowLobbyMessage(List<Lobby> lobbies){
        this.lobbies = lobbies;
    }

    /**
     * implements ClientExecute interface
     * sets the available lobbies to client and executes changeState() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client) {
        client.setAvailableLobbies(lobbies);
        client.changeState(ClientState.JOIN_LOBBY);
    }
}
