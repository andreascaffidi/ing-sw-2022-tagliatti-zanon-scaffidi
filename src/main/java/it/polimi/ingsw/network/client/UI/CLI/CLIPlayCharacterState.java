package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;

import java.util.Scanner;

public class CLIPlayCharacterState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private String command;

    public CLIPlayCharacterState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render()
    {

    }
}
