package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;

import java.util.Scanner;

public class CLIWelcomeState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private String username;

    public CLIWelcomeState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        System.out.println("Welcome to the magic world of Eriantys:\nInsert a nickname: ");
        username = in.nextLine();
        client.setUsername(username);
        client.send(new SetupRequestMessage(SetupResponsesTypes.USERNAME, username));
    }

    @Override
    public void serverError(String message) {
        System.out.println(message);
        username = in.nextLine();
        client.setUsername(username);
        client.send(new SetupRequestMessage(SetupResponsesTypes.USERNAME, username));
    }
}
