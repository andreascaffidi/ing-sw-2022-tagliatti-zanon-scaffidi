package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.ChooseTeamMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
import it.polimi.ingsw.network.server.Lobby;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CLIShowLobbiesState extends AbstractClientState{
    private Client client;
    private Scanner in;
    private List<Lobby> availableLobbies;
    private String selectedHost;
    private Lobby selectedLobby;

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
                if (input.equalsIgnoreCase(l.getHost())) {
                    valid = true;
                    selectedHost = input;
                    selectedLobby = l;
                    break;
                }
            }
            if (!valid){
                System.out.println("Invalid host ");
            }
        }

        if (selectedLobby.getNumOfPlayers() == 4){
            System.out.println("Match for 4 players, choose a team by typing 1 or 2: ");
            System.out.println("Team 1:");
            for (String player : selectedLobby.getPlayersByTeam(1)){
                System.out.println(player + "\t");
            }

            System.out.println("Team 2:");
            for (String player : selectedLobby.getPlayersByTeam(2)){
                System.out.println(player + "\t");
            }

            int num = 0;
            while (num < 1 || num > 2) {
                try {
                    num = Integer.parseInt(in.nextLine());
                    if (num < 1 || num > 2) {
                        System.out.println("Invalid team ");
                    } else {
                        client.send(new ChooseTeamMessage(num, selectedHost));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You have to insert a number ");
                }
            }
        }else {
            client.send(new SetupRequestMessage(SetupResponsesTypes.JOIN_LOBBY, selectedHost));
        }
    }

    @Override
    public void serverError(String message) {
        System.out.println(message);
    }
}
