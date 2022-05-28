package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;

import java.util.*;

/**
 * CLI move students state class
 */
public class CLIMoveStudentsState extends AbstractClientState {
    private final Client client;
    private final Scanner in;
    private int choices;

    /**
     * builds a CLI move students state class and print the main information
     * @param client client
     */
    public CLIMoveStudentsState(Client client){
        this.client = client;
        in = new Scanner(System.in);

        client.getUI().showModel(client.getReducedModel());
        choices = client.getReducedModel().getBoards().size() == 3 ? 4 : 3;
        System.out.println("It's your turn! Choose " + choices +" students from your entrance and move them" +
                " to an Island or your Dining room");
        if (client.getReducedModel() instanceof ReducedModelExpertMode){
            System.out.println("Or you can even pay a character card from the available, by typing " +
                    "PAY CHARACTER (you can pay a character card only one time per round)");
        }
    }

    /**
     * displays move students state on command line
     */
    @Override
    public void render(){
        Map<Integer, String> movementsChosen = new HashMap<>();

        boolean payCharacter = false;

        List<Integer> studentsAvailable = new ArrayList<>();
        for (int i = 1; i < client.getReducedModel().getBoards().get(0).getEntranceStudents().size()+1; i++){
            studentsAvailable.add(i);
        }

        while (choices > 0)
        {
            System.out.println("Choose a student from your entrance by typing its position ");

            int studentChosen = 0;
            boolean exit = false;

            while (!exit){
                String input = in.nextLine();
                if (client.getReducedModel() instanceof ReducedModelExpertMode &&
                        input.equalsIgnoreCase("PAY CHARACTER")){
                    if ( ((ReducedModelExpertMode) client.getReducedModel()).isCharacterAlreadyPlayed()){
                        System.out.println("You have already played a character in this round ");
                    }else{
                        exit = true;
                        choices = 0;
                        payCharacter = true;
                        client.changeState(ClientState.PLAY_CHARACTER);
                    }
                }else {
                    try {
                        studentChosen = Integer.parseInt(input);
                        if (!studentsAvailable.contains(studentChosen)) {
                            System.out.println("Invalid student position ");
                        } else {
                            studentsAvailable.remove((Integer) studentChosen);
                            exit = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You have to insert a number ");
                    }
                }
            }
            if (!payCharacter) {
                System.out.println("Choose a destination for this student by typing DINING ROOM or a number of an island ");

                String destination;
                exit = false;
                while (!exit) {
                    String input = in.nextLine();
                    if (client.getReducedModel() instanceof ReducedModelExpertMode &&
                            input.equalsIgnoreCase("PAY CHARACTER")){
                        if ( ((ReducedModelExpertMode) client.getReducedModel()).isCharacterAlreadyPlayed()){
                            System.out.println("You have already played a character in this round ");
                        }else{
                            exit = true;
                            choices = 0;
                            payCharacter = true;
                            client.changeState(ClientState.PLAY_CHARACTER);
                        }
                    }else {
                        destination = input.toUpperCase();
                        if (destination.equals("DINING ROOM")) {
                            exit = true;
                            choices--;
                            movementsChosen.put(studentChosen, destination);
                        } else {
                            try {
                                int islandChosen = Integer.parseInt(destination);
                                if (islandChosen < 1 || islandChosen > client.getReducedModel().getIslands().size()) {
                                    System.out.println("Invalid island, choose another one");
                                } else {
                                    exit = true;
                                    choices--;
                                    movementsChosen.put(studentChosen, destination);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("You have to insert the number of an island or DINING ROOM");
                            }
                        }
                    }
                }
            }
        }

        if (!payCharacter) {
            System.out.println("Students moved, waiting for players...");
            client.send(new MoveStudentMessage(movementsChosen));
        }
    }

    /**
     * manages server error on command line
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        System.out.println(message);
        render();
    }
}
