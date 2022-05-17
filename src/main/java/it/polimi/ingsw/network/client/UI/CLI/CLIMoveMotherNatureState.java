package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedAssistant;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.gameMessages.MoveMotherNatureMessage;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CLIMoveMotherNatureState extends AbstractClientState {
    private Client client;
    private Scanner in;

    private int movement;

    public CLIMoveMotherNatureState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        CLI.showModel(client.getReducedModel());
        System.out.println("It's your turn! Move Mother Nature by typing the number of movement you want, ");

        int num = 0;
        int maxMovement = 0;

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        if (myBoard != null){
            maxMovement = myBoard.getAssistantDeck().getPlayedAssistant().getMotherNatureMovements();
            System.out.println("maximum movements: " + maxMovement);
        }

        while (num < 1 || num > maxMovement){
            try {
                num = Integer.parseInt(in.nextLine());
                if (num < 1 || num > maxMovement){
                    System.out.println("Invalid Mother Nature Movement ");
                }else{
                    movement = num;
                }
            }catch (NumberFormatException e){
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("Mother Nature moved, waiting for the other players...");
        client.send(new MoveMotherNatureMessage(movement));
    }

    @Override
    public void serverError(String message) {
        System.out.println(message);
        int num = 0;
        int maxMovement = 0;

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        if (myBoard != null){
            maxMovement = myBoard.getAssistantDeck().getPlayedAssistant().getMotherNatureMovements();
        }

        while (num < 1 || num > maxMovement){
            try {
                num = Integer.parseInt(in.nextLine());
                if (num < 1 || num > maxMovement){
                    System.out.println("Invalid Mother Nature Movement ");
                }else{
                    movement = num;
                }
            }catch (NumberFormatException e){
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("Mother Nature moved, waiting for the other players...");
        client.send(new MoveMotherNatureMessage(movement));
    }
}
