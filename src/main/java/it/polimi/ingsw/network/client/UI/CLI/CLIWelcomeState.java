package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractWelcomeState;

import java.util.Scanner;

public class CLIWelcomeState extends AbstractWelcomeState {
    private Client client;
    private Scanner in;

    public CLIWelcomeState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        System.out.println("Welcome to the magic world of Eriantys:\nInsert a nickname: ");
        username = in.nextLine();
        client.setUsername(username);
        notifyFromUI(client);
    }

    @Override
    public void clientError(String message) {
        System.out.println(message);
        username = in.nextLine();
        client.setUsername(username);
        notifyFromUI(client);
    }
}
