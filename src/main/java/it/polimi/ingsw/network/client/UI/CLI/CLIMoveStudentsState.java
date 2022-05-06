package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractMoveStudentsState;

import java.util.Scanner;

public class CLIMoveStudentsState extends AbstractMoveStudentsState {
    private Client client;
    private Scanner in;

    public CLIMoveStudentsState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        System.out.println("It's your turn! Choose 3 students from your entrance and move them to an Island or your Dining room");
        System.out.println("for example: yellow Island 1, blue Island 12, red Dining Room");
        CLI.showModel(client.getReducedModel());

        int comma = 0;
        boolean valid = false;

        while (comma < 3 && valid == false)
        {
            String input = in.nextLine().toUpperCase();
            if(input.equals("YELLOW") ||
                    input.equals("BLUE") || input.equals("RED") || input.equals("PINK") || input.equals("GREEN"))
            {
                studentColor = input;
            }
            else{
                System.out.println("Unknown command, please type a color");
            }

            if(input.equals("ISLAND"))
            {
                destination = input;
                int num = 0;
                while (num < 1 || num > 12){
                    try {
                        num = Integer.parseInt(in.nextLine());
                        if (num < 1 || num > 12){
                            System.out.println("Invalid island ");
                        }else{
                            islandId = num;
                            valid = true;
                        }
                    }catch (NumberFormatException e){
                        System.out.println("You have to insert a number ");
                    }
                }
            } else if (input.equals("DINING ROOM")) {
                destination = input;
                valid = true;
            } else {
                System.out.println("Unknown command, please type a destination");
            }

            comma ++;
        }
        System.out.println("Students moved, waiting for players...");
        notifyFromUI(client);
    }
}
