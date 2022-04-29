package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractPlayAssistantState;
import it.polimi.ingsw.network.server.Lobby;

import java.util.List;
import java.util.Scanner;

public class CLIPlayAssistantState extends AbstractPlayAssistantState {

    private Client client;
    private Scanner in;

    public CLIPlayAssistantState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        CLI.showModel(client.getReducedModel());
    }

}
