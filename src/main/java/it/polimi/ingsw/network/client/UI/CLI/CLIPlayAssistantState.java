package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedAssistant;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CLIPlayAssistantState extends AbstractClientState {

    private final Client client;
    private final Scanner in;

    private int id;

    public CLIPlayAssistantState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        CLI.showModel(client.getReducedModel());
        System.out.println("It's your turn! Play an Assistant Card by typing the id ");

        int num = 0;
        List<Integer> possibleChoices = null;

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        if (myBoard != null){
            possibleChoices = myBoard.getAssistantDeck().getAssistantCards().stream()
                    .map(ReducedAssistant::getId).collect(Collectors.toList());
        }

        while (possibleChoices != null && !possibleChoices.contains(num)){
            try {
                num = Integer.parseInt(in.nextLine());
                if (!possibleChoices.contains(num)){
                    System.out.println("You can't play this assistant ");
                }else{
                    id = num;
                }
            }catch (NumberFormatException e){
                System.out.println("You have to insert a number ");
            }
        }

        client.send(new PlayAssistantMessage(id));
    }

    @Override
    public void serverError(String message) {
        System.out.println(message);
        int num = 0;
        List<Integer> possibleChoices = null;

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        if (myBoard != null){
            possibleChoices = myBoard.getAssistantDeck().getAssistantCards().stream()
                    .map(ReducedAssistant::getId).collect(Collectors.toList());
        }

        while (possibleChoices != null && !possibleChoices.contains(num)){
            try {
                num = Integer.parseInt(in.nextLine());
                if (!possibleChoices.contains(num)){
                    System.out.println("You can't play this assistant ");
                }else{
                    id = num;
                }
            }catch (NumberFormatException e){
                System.out.println("You have to insert a number ");
            }
        }

        client.send(new PlayAssistantMessage(id));
    }
}