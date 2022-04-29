package it.polimi.ingsw.network.responses.setupMessages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.server.Lobby;

import java.util.List;

public class ShowLobbyMessage implements ResponseMessage, ClientExecute {

    private List<Lobby> lobbies;

    public ShowLobbyMessage(List<Lobby> lobbies){
        this.lobbies = lobbies;
    }

    @Override
    public void execute(Client client) {
        client.setAvailableLobbies(lobbies);
        client.changeState(ClientState.JOIN_LOBBY);
    }
}
