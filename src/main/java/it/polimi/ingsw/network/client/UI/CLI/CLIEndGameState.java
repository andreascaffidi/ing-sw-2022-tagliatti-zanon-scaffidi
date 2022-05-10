package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;

import java.util.Scanner;

public class CLIEndGameState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private String command;

    public CLIEndGameState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render()
    {
        if(client.getUsername().equals(client.getWinner()))
        {
            System.out.println("Congrats! =) You are the winner. Wow you are so intelligent! :)");
        }else{
            System.out.println("The winner is... not you! It's " + client.getWinner());
        }
    }
}
