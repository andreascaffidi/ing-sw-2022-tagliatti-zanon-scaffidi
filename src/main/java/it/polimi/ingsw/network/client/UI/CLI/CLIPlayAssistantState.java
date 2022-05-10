package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;

import java.util.Scanner;

public class CLIPlayAssistantState extends AbstractClientState {

    private Client client;
    private Scanner in;

    private int id;

    public CLIPlayAssistantState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        System.out.println("It's your turn! Play an Assistant Card by typing the id ");
        CLI.showModel(client.getReducedModel());

        int num = 0;

        //TODO: effettuare il controllo su l'effettivo assistant deck
        while (num < 1 || num > 10){
            try {
                num = Integer.parseInt(in.nextLine());
                if (num < 1 || num > 10){
                    System.out.println("Invalid Assistant index ");
                }else{
                    id = num;
                }
            }catch (NumberFormatException e){
                System.out.println("You have to insert a number ");
            }
        }

        System.out.print("Sending to the server...");
        client.send(new PlayAssistantMessage(id));
        System.out.print("\t Sent \n");
    }
}