package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;

import java.util.*;

public class CLIMoveStudentsState extends AbstractClientState {
    private Client client;
    private Scanner in;

    public CLIMoveStudentsState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render(){
        CLI.showModel(client.getReducedModel());
        int choices = 3;
        if (client.getReducedModel().getBoards().size() == 3){
            choices = 4;
        }
        System.out.println("It's your turn! Choose " + choices +" students from your entrance and move them" +
                " to an Island or your Dining room");

        Map<Integer, String> movements = new HashMap<>();

        int numOfEntranceStudent = client.getReducedModel().getBoards().get(0).getEntranceStudents().size();
        List<Integer> students = new ArrayList<>();
        for (int i = 1; i < numOfEntranceStudent+1; i++){
            students.add(i);
        }


        while (choices > 0)
        {
            boolean valid = false;
            int student = 0;
            System.out.println("Choose a student from your entrance by typing its position ");
            while (!students.contains(student) && !valid){
                try {
                    student = Integer.parseInt(in.nextLine());
                    if (!students.contains(student)) {
                        System.out.println("Invalid student position ");
                    } else {
                        students.remove((Integer) student);
                        valid = true;
                    }
                } catch (NumberFormatException e){
                    System.out.println("You have to insert a number ");
                }
            }
            System.out.println("Choose a destination for this student by typing DINING ROOM or a number of an island ");

            String destination;
            valid = false;
            while (!valid) {
                destination = in.nextLine().toUpperCase();
                if (destination.equals("DINING ROOM")) {
                    valid = true;
                    movements.put(student, destination);
                } else {
                    try {
                        int verify = Integer.parseInt(destination);
                        if (verify < 1 || verify > client.getReducedModel().getIslands().size()) {
                            System.out.println("Invalid island, choose another one");
                        } else {
                            valid = true;
                            movements.put(student, destination);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You have to insert the number of an island or DINING ROOM");
                    }
                }
            }
            choices --;
        }
        System.out.println("Students moved, waiting for players...");

        client.send(new MoveStudentMessage(movements));
    }

    @Override
    public void serverError(String message) {
        System.out.println(message);
        render();
    }
}
