package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.exceptions.ColorNotFoundException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.reducedModel.ReducedCharacter;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.requests.gameMessages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CLIPlayCharacterState extends AbstractClientState {
    private final Client client;
    private final Scanner in;

    public CLIPlayCharacterState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render()
    {
        System.out.println("Select a character card from the following on the table or" +
                " type EXIT to come back to the game ");
        List<ReducedCharacter> characters = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters();
        for (ReducedCharacter character : characters) {
            System.out.println(character.getId() + " : cost = " + character.getCost());
        }

        int characterChosen;
        boolean exit = false;

        List<Integer> possibleChoices = characters.stream().map(ReducedCharacter::getId).collect(Collectors.toList());

        while (!exit){
            String input = in.nextLine();
            if (input.equalsIgnoreCase("EXIT")){
                    exit = true;
                    client.changeState(client.getBackState());
            }else {
                try {
                    characterChosen = Integer.parseInt(input);
                    if (!possibleChoices.contains(characterChosen)) {
                        System.out.println("Character not available in this match ");
                    } else if ( ((ReducedModelExpertMode) client.getReducedModel()).getCoins().get(client.getUsername())
                            < characters.get(characterChosen).getCost()) {
                        System.out.println("You haven't got enough coins to pay this card ");
                    } else {
                        exit = true;
                        System.out.println("You have payed the character card ");
                        client.send(characterMessage(characterChosen));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You have to insert a number ");
                }
            }
        }
    }

    private RequestMessage characterMessage(int characterChosen){
        switch (characterChosen){
            case 1 : return character1();
            case 2 : return character2();
            case 3 : return character3();
            case 4 : return character4();
            case 5 : return character5();
            case 6 : return character6();
            case 7 : return character7();
            case 8 : return character8();
            case 9 : return character9();
            case 10 : return character10();
            case 11 : return character11();
            case 12 : return character12();
            default: return null;
        }
    }

    private PayCharacter1Message character1(){
        System.out.println("Choose a student from the card by typing its position ");

        ReducedCharacter character1 = ((ReducedModelExpertMode)client.getReducedModel()).getCharacters().
                stream().filter(c -> c.getId()==1).findFirst().orElse(null);

        List<ColorS> studentsOnCard = character1 != null ? character1.getStudents() : new ArrayList<>();

        System.out.println("Students on card: ");
        for (ColorS student : studentsOnCard){
            System.out.println("1 : " + student.toString());
        }

        int studentChosen = 0;
        int islandChosen = 0;
        boolean exit = false;

        while (!exit){
            try {
                studentChosen = Integer.parseInt(in.nextLine());
                if (studentChosen < 1 || studentChosen > studentsOnCard.size()) {
                    System.out.println("Invalid student position ");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }

        System.out.println("Choose an island to place the student on it ");
        exit = false;
        while (!exit){
            try {
                islandChosen = Integer.parseInt(in.nextLine());
                if (islandChosen < 1 || islandChosen > client.getReducedModel().getIslands().size()) {
                    System.out.println("Invalid island, choose another one");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("Student moved ");
        return new PayCharacter1Message(islandChosen, studentChosen);
    }

    private PayCharacter2Message character2(){
        return new PayCharacter2Message();
    }

    private PayCharacter3Message character3(){
        System.out.println("Choose an island to place the student on it ");
        boolean exit = false;
        int islandChosen = 0;
        while (!exit){
            try {
                islandChosen = Integer.parseInt(in.nextLine());
                if (islandChosen < 1 || islandChosen > client.getReducedModel().getIslands().size()) {
                    System.out.println("Invalid island, choose another one");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("Island's supremacy calculated ");
        return new PayCharacter3Message(islandChosen);
    }

    private PayCharacter4Message character4(){
        //FIXME: ci sono errori nel modello
        System.out.println("Insert a number of additional movement (maximum 2) ");
        boolean exit = false;
        int movementChosen = 0;
        while (!exit){
            try {
                movementChosen = Integer.parseInt(in.nextLine());
                if (movementChosen < 1 || movementChosen > 2) {
                    System.out.println("Invalid additional movement, choose 1 or 2");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("Additional movement set ");
        return new PayCharacter4Message(movementChosen);
    }

    private PayCharacter5Message character5(){
        System.out.println("Choose an island to place a No-Entry Tile ");
        boolean exit = false;
        int islandChosen = 0;
        while (!exit){
            try {
                islandChosen = Integer.parseInt(in.nextLine());
                if (islandChosen < 1 || islandChosen > client.getReducedModel().getIslands().size()) {
                    System.out.println("Invalid island, choose another one");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("No-Entry Tile placed ");
        return new PayCharacter5Message(islandChosen);
    }

    private PayCharacter6Message character6(){
        System.out.println("Choose an island on which towers don't count for supremacy calculation ");
        boolean exit = false;
        int islandChosen = 0;
        while (!exit){
            try {
                islandChosen = Integer.parseInt(in.nextLine());
                if (islandChosen < 1 || islandChosen > client.getReducedModel().getIslands().size()) {
                    System.out.println("Invalid island, choose another one");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("Effect activated ");
        return new PayCharacter6Message(islandChosen);
    }

    private PayCharacter7Message character7(){
        System.out.println("Insert the number of students you want do switch (maximum 3) ");

        ReducedCharacter character7 = ((ReducedModelExpertMode)client.getReducedModel()).getCharacters().
                stream().filter(c -> c.getId()==7).findFirst().orElse(null);

        List<ColorS> studentsOnCard = character7 != null ? character7.getStudents() : new ArrayList<>();

        List<Integer> entranceAvailable = new ArrayList<>();
        for (int i = 1; i < client.getReducedModel().getBoards().get(0).getEntranceStudents().size()+1; i++){
            entranceAvailable.add(i);
        }

        List<Integer> entranceChosen = new ArrayList<>();
        List<Integer> cardChosen = new ArrayList<>();

        int choices = 0;
        boolean exit = false;

        System.out.println("Students on card: ");
        for (ColorS student : studentsOnCard){
            System.out.println("1 : " + student.toString());
        }

        while (!exit){
            try {
                choices = Integer.parseInt(in.nextLine());
                if (choices < 1 || choices > 3){
                    System.out.println("Invalid number, please select between 1 and 3");
                }else{
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }

        while (choices > 0) {
            exit = false;
            int entranceStudent = 0;
            System.out.println("Choose a student from your entrance by typing its position");
            while (!exit) {
                try {
                    entranceStudent = Integer.parseInt(in.nextLine());
                    if (!entranceAvailable.contains(entranceStudent)) {
                        System.out.println("Invalid student position ");
                    } else {
                        entranceAvailable.remove((Integer) entranceStudent);
                        exit = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You have to insert a number ");
                }
            }
            int cardStudent = 0;
            exit = false;
            System.out.println("Choose a student from the card by typing its position");
            while (!exit) {
                try {
                    cardStudent = Integer.parseInt(in.nextLine());
                    if (cardStudent < 1 || cardStudent > studentsOnCard.size()) {
                        System.out.println("Invalid student position ");
                    } else {
                        exit = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You have to insert a number ");
                }
            }
            entranceChosen.add(entranceStudent);
            cardChosen.add(cardStudent);
            choices--;
        }
        System.out.println("Student switched ");
        return new PayCharacter7Message(cardChosen, entranceChosen);
    }

    private PayCharacter8Message character8(){
        return new PayCharacter8Message();
    }

    private PayCharacter9Message character9(){
        System.out.println("Choose a color that has no influence in this round ");
        boolean exit = false;
        ColorS colorChosen = null;
        while (!exit){
            try {
                colorChosen = ColorS.parseToColor(in.nextLine());
                exit = true;
            } catch (ColorNotFoundException e) {
                System.out.println("Invalid color ");
            }
        }
        System.out.println("Effect activated ");
        return new PayCharacter9Message(colorChosen);
    }

    private PayCharacter10Message character10(){
        System.out.println("Insert the number of students you want do switch (maximum 2) ");
        boolean exit = false;
        int choices = 0;

        List<Integer> entranceAvailable = new ArrayList<>();
        for (int i = 1; i < client.getReducedModel().getBoards().get(0).getEntranceStudents().size()+1; i++){
            entranceAvailable.add(i);
        }

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        int diningAvailable = 0;
        //FIXME:
        /*
        if (myBoard != null){
            diningAvailable = myBoard.getBlueStudents() + myBoard.getRedStudents() + myBoard.getGreenStudents()
            + myBoard.getPinkStudents() + myBoard.getYellowStudents();
        }
*/
        List<Integer> entranceChosen = new ArrayList<>();
        List<ColorS> diningChosen = new ArrayList<>();

        while (!exit){
            try {
                choices = Integer.parseInt(in.nextLine());
                if (choices < 1 || choices > 2){
                    System.out.println("Invalid number, please select 1 or 2");
                }else if (choices > diningAvailable) {
                    System.out.println("You don't have enough students in dining room");
                    //FIXME cosa succede se non ci sono proprio studenti
                }else{
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        while (choices > 0) {
            exit = false;
            int studentChosen = 0;
            System.out.println("Choose a student from your entrance by typing its position");
            while (!exit) {
                try {
                    studentChosen = Integer.parseInt(in.nextLine());
                    if (!entranceAvailable.contains(studentChosen)) {
                        System.out.println("Invalid student position ");
                    } else {
                        entranceAvailable.remove((Integer) studentChosen);
                        exit = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You have to insert a number ");
                }
            }
            exit = false;
            ColorS colorChosen = null;
            System.out.println("Choose a color from the dining room ");
            //FIXME:
            /*
            while (!exit) {
                try {
                    colorChosen = ColorS.parseToColor(in.nextLine());
                    int numOfStudents = 0;
                    switch (colorChosen){
                        case BLUE: numOfStudents = myBoard.getBlueStudents();
                        break;
                        case PINK: numOfStudents = myBoard.getPinkStudents();
                        break;
                        case RED: numOfStudents = myBoard.getRedStudents();
                        break;
                        case YELLOW: numOfStudents = myBoard.getYellowStudents();
                        break;
                        case GREEN: numOfStudents = myBoard.getGreenStudents();
                        break;
                    }
                    if (numOfStudents == 0){
                        System.out.println("There aren't students in the dining room");
                    }else{
                        exit = true;
                    }
                } catch (ColorNotFoundException e) {
                    System.out.println("Invalid color ");
                }
            }
            */

            entranceChosen.add(studentChosen);
            diningChosen.add(colorChosen);
            choices--;
        }
        System.out.println("Students switched ");
        return new PayCharacter10Message(diningChosen, entranceChosen);
    }

    private PayCharacter11Message character11(){
        System.out.println("Choose a student from the card by typing its position ");

        ReducedCharacter character11 = ((ReducedModelExpertMode)client.getReducedModel()).getCharacters().
                stream().filter(c -> c.getId()==11).findFirst().orElse(null);

        List<ColorS> studentsOnCard = character11 != null ? character11.getStudents() : new ArrayList<>();

        System.out.println("Students on card: ");
        for (ColorS student : studentsOnCard){
            System.out.println("1 : " + student.toString());
        }

        int studentChosen = 0;
        boolean exit = false;

        while (!exit){
            try {
                studentChosen = Integer.parseInt(in.nextLine());
                if (studentChosen < 1 || studentChosen > studentsOnCard.size()) {
                    System.out.println("Invalid student position ");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        System.out.println("Student moved ");
        return new PayCharacter11Message(studentChosen);
    }

    private PayCharacter12Message character12(){
        System.out.println("Choose a color ");
        boolean exit = false;
        ColorS colorChosen = null;
        while (!exit){
            try {
                colorChosen = ColorS.parseToColor(in.nextLine());
                exit = true;
            } catch (ColorNotFoundException e) {
                System.out.println("Invalid color ");
            }
        }
        System.out.println("Effect activated ");
        return new PayCharacter12Message(colorChosen);
    }

    @Override
    public void serverError(String message) {
        System.out.println(message);
        render();
    }
}
