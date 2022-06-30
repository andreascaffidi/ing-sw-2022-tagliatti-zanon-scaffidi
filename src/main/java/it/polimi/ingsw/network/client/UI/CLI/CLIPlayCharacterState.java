package it.polimi.ingsw.network.client.UI.CLI;

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

/**
 * CLI play character state class
 */
public class CLIPlayCharacterState extends AbstractClientState {
    private final Client client;
    private final Scanner in;
    private final List<ReducedCharacter> characters;
    private final ReducedModelExpertMode reducedModelExpertMode;

    /**
     * builds a CLI play character state class and print the main information
     * @param client client
     */
    public CLIPlayCharacterState(Client client){
        this.client = client;
        in = new Scanner(System.in);

        reducedModelExpertMode = (ReducedModelExpertMode) client.getReducedModel();
        characters = reducedModelExpertMode.getCharacters();
    }

    /**
     * displays play character state on command line
     */
    @Override
    public void render()
    {
        System.out.println("Select a character card from the following on the table by typing its ID:");
        for (ReducedCharacter character : characters) {
            System.out.println("#" + character.getId() + "   " + character.getName() + ": cost = " + character.getCost());
        }
        CLI.CTA("Or type "+Ansi.colorize("EXIT", Ansi.UNDERLINE)+ " to come back to the game");

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
                        CLI.error("Character not available in this match ");
                    }
                    else if (reducedModelExpertMode.getCoins().get(client.getUsername())
                            < reducedModelExpertMode.getCharacterById(characterChosen).getCost()) {
                        CLI.error("You haven't got enough coins to pay this card ");
                    } else {
                        exit = true;
                        System.out.println("Card selected! "+Ansi.colorize("EXIT", Ansi.UNDERLINE)+" no more available ");
                        client.send(characterMessage(characterChosen));
                    }
                } catch (NumberFormatException e) {
                    CLI.error("You have to insert a number ");
                }
            }
        }
    }

    /**
     * handles the correct character message based on the given character ID
     * @param characterChosen character ID
     * @return input request for the character chosen
     */
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

    /**
     * requests the input for character 1
     * @return pay character 1 message
     */
    private PayCharacter1Message character1(){
        CLI.CTA("Choose a student from the card by typing its position");
        int studentChosen = CLI.chooseValidStudentOnCard(reducedModelExpertMode, 1);
        CLI.CTA("Choose an island to place the student on it");
        int islandChosen = CLI.chooseValidIsland(reducedModelExpertMode);
        System.out.println("Student moved ");
        return new PayCharacter1Message(islandChosen, studentChosen);
    }

    /**
     * requests the input for character 2 (this character doesn't need any input)
     * @return pay character 2 message
     */
    private PayCharacter2Message character2(){
        return new PayCharacter2Message();
    }

    /**
     * requests the input for character 3
     * @return pay character 3 message
     */
    private PayCharacter3Message character3(){
        CLI.CTA("Choose an island on which calculate supremacy");
        int islandChosen = CLI.chooseValidIsland(reducedModelExpertMode);
        System.out.println("Island's supremacy calculated ");
        return new PayCharacter3Message(islandChosen);
    }

    /**
     * requests the input for character 4
     * @return pay character 4 message
     */
    private PayCharacter4Message character4(){
        CLI.CTA("Insert a number of additional movement (maximum 2)");
        boolean exit = false;
        int movementChosen = 0;
        while (!exit){
            try {
                movementChosen = Integer.parseInt(in.nextLine());
                if (movementChosen < 1 || movementChosen > 2) {
                    CLI.error("Invalid additional movement, choose 1 or 2");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                CLI.error("You have to insert a number ");
            }
        }
        System.out.println("Additional movement set ");
        return new PayCharacter4Message(movementChosen);
    }

    /**
     * requests the input for character 5
     * @return pay character 5 message
     */
    private PayCharacter5Message character5(){
        CLI.CTA("Choose an island to place a No-Entry Tile");
        int islandChosen = CLI.chooseValidIsland(reducedModelExpertMode);
        System.out.println("No-Entry Tile placed ");
        return new PayCharacter5Message(islandChosen);
    }

    /**
     * requests the input for character 6
     * @return pay character 6 message
     */
    private PayCharacter6Message character6(){
        CLI.CTA("Choose an island on which towers don't count for supremacy calculation");
        int islandChosen = CLI.chooseValidIsland(reducedModelExpertMode);
        System.out.println("Effect activated ");
        return new PayCharacter6Message(islandChosen);
    }

    /**
     * requests the input for character 7
     * @return pay character 7 message
     */
    private PayCharacter7Message character7(){
        CLI.CTA("Insert the number of students you want do switch (maximum 3)");

        List<Integer> entranceChosen = new ArrayList<>();
        List<Integer> cardChosen = new ArrayList<>();

        int choices = 0;
        boolean exit = false;

        while (!exit){
            try {
                choices = Integer.parseInt(in.nextLine());
                if (choices < 1 || choices > 3){
                    CLI.error("Invalid number, please select between 1 and 3");
                }else{
                    exit = true;
                }
            } catch (NumberFormatException e) {
                CLI.error("You have to insert a number ");
            }
        }

        //player can make the same choices, in that case it's his fault
        while (choices > 0) {
            CLI.CTA("Choose a student from your entrance by typing its position");
            int entranceStudent = CLI.chooseValidEntranceStudent(reducedModelExpertMode, client.getUsername());
            CLI.CTA("Choose a student from the card by typing its position");
            int cardStudent = CLI.chooseValidStudentOnCard(reducedModelExpertMode, 7);
            entranceChosen.add(entranceStudent);
            cardChosen.add(cardStudent);
            choices--;
        }
        System.out.println("Student switched ");
        return new PayCharacter7Message(cardChosen, entranceChosen);
    }

    /**
     * requests the input for character 8 (this character doesn't need any input)
     * @return pay character 8 message
     */
    private PayCharacter8Message character8(){
        return new PayCharacter8Message();
    }

    /**
     * requests the input for character 9
     * @return pay character 9 message
     */
    private PayCharacter9Message character9(){
        CLI.CTA("Choose a color that has no influence in this round");
        ColorS colorChosen = CLI.chooseValidColor();
        System.out.println("Effect activated ");
        return new PayCharacter9Message(colorChosen);
    }

    /**
     * requests the input for character 10
     * @return pay character 10 message
     */
    private PayCharacter10Message character10(){
        List<Integer> entranceChosen = new ArrayList<>();
        List<ColorS> diningChosen = new ArrayList<>();

        int choices = 0;
        boolean exit = false;

        ReducedBoard myBoard = client.getReducedModel().getBoard(client.getUsername());

        int diningStudents = 0;

        if (myBoard != null){
            for (ColorS color : ColorS.values()){
                diningStudents += myBoard.getStudents().get(color);
            }
        }

        if (diningStudents == 0){
            return new PayCharacter10Message(null, null);
        }

        CLI.CTA("Insert the number of students you want do switch (maximum 2)");

        while (!exit){
            try {
                choices = Integer.parseInt(in.nextLine());
                if (choices < 1 || choices > 2){
                    CLI.error("Invalid number, please select 1 or 2");
                }else if (choices > diningStudents) {
                    CLI.error("You don't have enough students in your dining room");
                }else{
                    exit = true;
                }
            } catch (NumberFormatException e) {
                CLI.error("You have to insert a number ");
            }
        }

        //player can make the same choices, in that case it's his fault
        while (choices > 0) {
            CLI.CTA("Choose a student from your entrance by typing its position");
            int studentChosen = CLI.chooseValidEntranceStudent(reducedModelExpertMode, client.getUsername());
            CLI.CTA("Choose a color from the dining room");
            exit = false;
            ColorS colorChosen = null;
            while(!exit){
                colorChosen = CLI.chooseValidColor();
                int numOfStudents = myBoard.getStudents().get(colorChosen);
                if (numOfStudents == 0){
                    CLI.error("There aren't " + colorChosen + " students in your dining room");
                }else{
                    exit = true;
                }
            }
            entranceChosen.add(studentChosen);
            diningChosen.add(colorChosen);
            choices--;
        }
        System.out.println("Students switched ");
        return new PayCharacter10Message(diningChosen, entranceChosen);
    }

    /**
     * requests the input for character 11
     * @return pay character 11 message
     */
    private PayCharacter11Message character11(){
        CLI.CTA("Choose a student from the card by typing its position");
        int studentChosen = CLI.chooseValidStudentOnCard(reducedModelExpertMode, 11);
        System.out.println("Student moved ");
        return new PayCharacter11Message(studentChosen);
    }

    /**
     * requests the input for character 12
     * @return pay character 12 message
     */
    private PayCharacter12Message character12(){
        CLI.CTA("Choose a color");
        ColorS colorChosen = CLI.chooseValidColor();
        System.out.println("Effect activated ");
        return new PayCharacter12Message(colorChosen);
    }

    /**
     * manages server error on command line
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        CLI.error(message);
        render();
    }
}
