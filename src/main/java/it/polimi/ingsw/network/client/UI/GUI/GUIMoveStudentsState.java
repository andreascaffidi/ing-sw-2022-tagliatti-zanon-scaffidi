package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;

import java.util.Scanner;

public class GUIMoveStudentsState extends AbstractClientState {
    private Client client;
    private Scanner in;

    private String studentColor;
    private int islandId;
    private String destination;

    public GUIMoveStudentsState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    //TODO: adattare al reduced model
    @Override
    public void render(){

    }
}
