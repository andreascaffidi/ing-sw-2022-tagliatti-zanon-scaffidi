package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractMoveMotherNatureState;

import java.util.Scanner;

public class CLIMoveMotherNatureState extends AbstractMoveMotherNatureState {
    private Client client;
    private Scanner in;

    public CLIMoveMotherNatureState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        System.out.println("It's your turn! Move Mother Nature by typing the number of steps she has to take ");
        CLI.showModel(client.getReducedModel());

        int num = 0;

        //TODO: effettuare il controllo sull'effettivo assistant giocato
        while (num < 1 || num > 5){
            try {
                num = Integer.parseInt(in.nextLine());
                if (num < 1 || num > 5){
                    System.out.println("Invalid Mother Nature Movement ");
                }else{
                    id = num;
                }
            }catch (NumberFormatException e){
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("Mother Nature moved, waiting for the other players...");
        notifyFromUI(client);
    }
}
