package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;

import java.util.Scanner;

public class CLICreateLobbyState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private String gameMode;
    private String host;
    private int numOfPlayers;

    public CLICreateLobbyState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        System.out.println("Insert the game mode by typing: NORMAL or EXPERT ");
        boolean valid = false;
        while (!valid){
            String input = in.nextLine().toUpperCase();
            if (input.equals("NORMAL")){
                gameMode = input;
                valid = true;
            }else if(input.equals("EXPERT")){
                gameMode = input;
                valid = true;
            }else{
                System.out.println("Unknown command, please type: NORMAL or EXPERT ");
            }
        }

        System.out.println("Insert the number of players (maximum 4 players) ");
        int num=0;
        while (num < 2 || num > 4){
            try {
                num = Integer.parseInt(in.nextLine());
                if (num < 2 || num > 4){
                    System.out.println("Invalid number of players ");
                }else{
                    numOfPlayers = num;
                }
            }catch (NumberFormatException e){
                System.out.println("You have to insert a number ");
            }
        }

        host = client.getUsername();
        client.send(new CreateLobbyMessage(host, gameMode, numOfPlayers));
    }

}
