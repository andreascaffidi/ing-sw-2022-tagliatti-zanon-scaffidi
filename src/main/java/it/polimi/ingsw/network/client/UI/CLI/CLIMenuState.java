package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractMenuState;

import java.util.Scanner;

public class CLIMenuState extends AbstractMenuState {
    private Client client;
    private Scanner in;

    public CLIMenuState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        System.out.println("Create or join a lobby by typing: CREATE or JOIN ");
        boolean valid = false;
        while (!valid){
            String input = in.nextLine().toUpperCase();
            if (input.equals("JOIN")){
                command = input;
                valid = true;
            }else if(input.equals("CREATE")){
                command = input;
                valid = true;
            }else{
                System.out.println("Unknown command, please type: CREATE or JOIN ");
            }
        }
        notifyFromUI(client);
    }
}
