package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;

import java.util.Scanner;

public class GUIMenuState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private String command;

    public GUIMenuState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){

    }
}
