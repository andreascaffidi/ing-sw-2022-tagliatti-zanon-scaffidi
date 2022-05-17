package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.server.Lobby;

import java.util.List;
import java.util.Scanner;

public class GUIShowLobbiesState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private List<Lobby> availableLobbies;
    private String selectedHost;
    private Lobby selectedLobby;

    public GUIShowLobbiesState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){

    }
}
