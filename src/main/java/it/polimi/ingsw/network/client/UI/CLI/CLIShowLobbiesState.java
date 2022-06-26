package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.ChooseTeamMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import it.polimi.ingsw.network.server.Lobby;

import java.util.List;
import java.util.Scanner;

/**
 * CLI show lobbies state class
 */
public class CLIShowLobbiesState extends AbstractClientState{
    private final Client client;
    private final Scanner in;
    private String selectedHost;
    private Lobby selectedLobby;

    /**
     * builds a CLI show lobbies state class
     * @param client client
     */
    public CLIShowLobbiesState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    /**
     * displays show lobbies state on command line
     */
    @Override
    public void render(){
        List<Lobby> availableLobbies = client.getAvailableLobbies();
        System.out.print("Select the lobby to join by typing the "+CLI.Ansi.colorize("username", CLI.Ansi.UNDERLINE)
                +" of the host: \n\n");
        for (Lobby l : availableLobbies){
            System.out.println(l.getHost() + " :    " + l.getGameMode() + " MODE  " +
                    l.getNumOfConnection() + "/" + l.getNumOfPlayers() + "\n");
        }

        boolean valid = false;
        while (!valid){
            CLI.CTA("Insert the selected host");
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
                CLI.error("Invalid host \n");
            }
        }

        if (selectedLobby.getNumOfPlayers() == 4){
            System.out.println("Team 1:");
            for (String player : selectedLobby.getPlayersByTeam(1)){
                System.out.println(player + "\t");
            }

            System.out.println("Team 2:");
            for (String player : selectedLobby.getPlayersByTeam(2)){
                System.out.println(player + "\t");
            }

            CLI.CTA("Match for 4 players, choose a team by typing "+CLI.Ansi.colorize("1", CLI.Ansi.UNDERLINE)
                    +" or "+CLI.Ansi.colorize("2", CLI.Ansi.UNDERLINE));

            int num = 0;
            while (num < 1 || num > 2) {
                try {
                    num = Integer.parseInt(in.nextLine());
                    if (num < 1 || num > 2) {
                        CLI.error("Invalid team ");
                    } else {
                        client.send(new ChooseTeamMessage(num, selectedHost));
                    }
                } catch (NumberFormatException e) {
                    CLI.error("You have to insert a number ");
                }
            }
        }else {
            client.send(new SetupRequestMessage(SetupRequestTypes.JOIN_LOBBY, selectedHost));
        }
    }

    /**
     * manages server error on command line
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        CLI.error(message);
    }
}
