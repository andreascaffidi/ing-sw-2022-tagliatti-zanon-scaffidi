package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractChooseCloudState;
import it.polimi.ingsw.network.client.states.AbstractMoveMotherNatureState;

import java.util.Scanner;

public class CLIChooseCloudState extends AbstractChooseCloudState {
    private Client client;
    private Scanner in;

    public CLIChooseCloudState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        CLI.showModel(client.getReducedModel());
        System.out.println("It's your turn! Choose a cloud");
        System.out.println("Insert the Cloud ID: ");
        int num = 0;
        while (num < 2 || num > 4){
            try {
                num = Integer.parseInt(in.nextLine());
                if (num < 2 || num > 4){
                    System.out.println("Invalid Cloud id ");
                }else{
                    id = num;
                }
            }catch (NumberFormatException e){
                System.out.println("You have to insert a number ");
            }
        }

        System.out.println("Cloud chosen, waiting for players...");
        notifyFromUI(client);
    }
}
