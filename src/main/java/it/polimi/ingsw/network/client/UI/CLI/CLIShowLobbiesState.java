package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractShowLobbiesState;
import it.polimi.ingsw.network.server.Lobby;

import java.util.List;
import java.util.Scanner;

public class CLIShowLobbiesState extends AbstractShowLobbiesState {
    private Client client;
    private Scanner in;
    private List<Lobby> availableLobbies;

    public CLIShowLobbiesState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        this.availableLobbies = client.getAvailableLobbies();
        System.out.println("Select the lobby to join by typing the username of the host:\n\n");
        for (Lobby l : availableLobbies){
            System.out.println(l.getHost() + " :    " + l.getGameMode() + " MODE  " +
                    l.getNumOfConnection() + "/" + l.getNumOfPlayers() + "\n");
        }

        boolean valid = false;
        while (!valid){
            String input = in.nextLine();
            for (Lobby l : availableLobbies){
                if (input.equals(l.getHost())) {
                    valid = true;
                    selectedLobby = input;
                    break;
                }
            }
            if (!valid){
                System.out.println("Invalid host ");
            }
        }

        System.out.println("Lobby joined, waiting for players...");
        notifyFromUI(client);
    }

}
