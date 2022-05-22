package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;

import java.util.Scanner;

public class CLIChooseCloudState extends AbstractClientState {
    private final Client client;
    private final Scanner in;


    public CLIChooseCloudState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        CLI.showModel(client.getReducedModel());
        System.out.println("It's your turn! Choose a cloud by typing the id ");
        if (client.getReducedModel() instanceof ReducedModelExpertMode){
            System.out.println("Or you can even pay a character card from the available, by typing " +
                    "PAY CHARACTER (you can pay a character card only one time per round)");
        }

        int cloudChosen = 0;
        boolean exit = false;
        boolean payCharacter = false;

        while (!exit){
            String input = in.nextLine();
            if (client.getReducedModel() instanceof ReducedModelExpertMode &&
                    input.equalsIgnoreCase("PAY CHARACTER")){
                if ( ((ReducedModelExpertMode) client.getReducedModel()).isCharacterAlreadyPlayed()){
                    System.out.println("You have already played a character in this round ");
                }else{
                    exit = true;
                    payCharacter = true;
                    client.changeState(ClientState.PLAY_CHARACTER);
                }
            }else {
                try {
                    cloudChosen = Integer.parseInt(input);
                    if (cloudChosen < 1 || cloudChosen > client.getReducedModel().getClouds().size()) {
                        System.out.println("Invalid Cloud id ");
                    } else {
                        System.out.println("Cloud chosen, waiting for players...");
                        exit = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You have to insert a number ");
                }
            }
        }

        if (!payCharacter) {
            client.send(new ChooseCloudMessage(cloudChosen));
        }
    }

    @Override
    public void serverError(String message) {
        System.out.println(message);
        render();
    }
}
