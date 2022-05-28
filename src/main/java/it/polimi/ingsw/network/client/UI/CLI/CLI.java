package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.exceptions.ColorNotFoundException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.*;
import it.polimi.ingsw.network.client.reducedModel.ReducedIsland;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;

import java.io.IOException;
import java.util.*;


public class CLI implements UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public CLI(){

    }

    @Override
    public AbstractClientState getClientState(ClientState clientState, Client client) {
        switch (clientState){
            case WELCOME: return new CLIWelcomeState(client);
            case MENU: return new CLIMenuState(client);
            case CREATE_LOBBY: return new CLICreateLobbyState(client);
            case JOIN_LOBBY: return new CLIShowLobbiesState(client);
            case PLAY_ASSISTANT: return new CLIPlayAssistantState(client);
            case WAITING: return new CLIWaitingState(client);
            case MOVE_STUDENTS: return new CLIMoveStudentsState(client);
            case MOVE_MN: return new CLIMoveMotherNatureState(client);
            case CHOOSE_CLOUD: return new CLIChooseCloudState(client);
            case END_GAME: return new CLIEndGameState(client);
            case PLAY_CHARACTER: return new CLIPlayCharacterState(client);
            default : return null;
        }
    }

    /**
     * Clears the screen by erasing all the content on the current cli. <br>
     * Actually is like if the users scrolls down until all the older printed message are hidden
     */
    public void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
public static void showLogo(){
    final String logo =  " _______ .______     ____    ____  ___      .__   __. .___________. __       _______.\n" +
            "|   ____||   _  \\    \\   \\  /   / /   \\     |  \\ |  | |           ||  |     /       |\n" +
            "|  |__   |  |_)  |    \\   \\/   / /  ^  \\    |   \\|  | `---|  |----`|  |    |   (----`\n" +
            "|   __|  |      /      \\_    _/ /  /_\\  \\   |  . `  |     |  |     |  |     \\   \\    \n" +
            "|  |____ |  |\\  \\----.   |  |  /  _____  \\  |  |\\   |     |  |     |  | .----)   |   \n" +
            "|_______|| _| `._____|   |__| /__/     \\__\\ |__| \\__|     |__|     |__| |_______/    \n" +
            "                                                                                     \n\n";
    System.out.print(Ansi.colorize(logo,Ansi.CYAN));
}
    @Override
    public void showModel(ReducedModel reducedModel){
        //FIXME: è un test
        if (reducedModel instanceof ReducedModelExpertMode){
            showExpert((ReducedModelExpertMode)reducedModel);
        }
        System.out.println("\n");
        String model = "------------------------------------------------------------\n";

        model += "C U R R E N T   P L A Y E R: ";
        model += Ansi.colorize(" "+reducedModel.getCurrentPlayer()+" ",Ansi.BACKGROUND_YELLOW,Ansi.BLACK,Ansi.HIGH_INTENSITY);
        model += "\n";

        model += "\n\uD83C\uDFDD \uD83C\uDFDD \uD83C\uDFDD "+ Ansi.colorize(" I S L A N D S ",Ansi.HIGH_INTENSITY,Ansi.BACKGROUND_GREEN,Ansi.BLACK) + " \uD83C\uDFDD \uD83C\uDFDD \uD83C\uDFDD \n";
        model += islandsToString(reducedModel.getIslands());

        model += "\n️☁️️ ☁️️ ☁️️ "+ Ansi.colorize(" C L O U D S ",Ansi.HIGH_INTENSITY,Ansi.BACKGROUND_CYAN,Ansi.BLACK)+" ☁️️ ☁️️ ☁️️ \n";
        model += cloudsToString(reducedModel.getClouds());

        model += "\n\uD83C\uDFB2 \uD83C\uDFB2 \uD83C\uDFB2 " + Ansi.colorize(" B O A R D S ",Ansi.BACKGROUND_RED,Ansi.BLACK,Ansi.HIGH_INTENSITY)+" \uD83C\uDFB2 \uD83C\uDFB2 \uD83C\uDFB2\n\n";

        for(ReducedBoard b : reducedModel.getBoards())
        {
            model += boardToString(b);
            model += assistantDeckToString(b.getAssistantDeck().getAssistantCards());
            model += "\n";
        }

        //model += "\n\uD83C\uDCCF \uD83C\uDCCF \uD83C\uDCCF " + Ansi.colorize(" A S S I S T A N T   D E C K ",Ansi.BACKGROUND_BLUE,Ansi.BLACK,Ansi.HIGH_INTENSITY)+" \uD83C\uDCCF \uD83C\uDCCF \uD83C\uDCCF\n\n";


        System.out.println(model);
    }

    public static String[] cloudToStringArray(ReducedCloud cloud){
        String[]  cloudStrArray = new String[3];
        cloudStrArray[0] = Ansi.colorize("┌────┬───────────┐",Ansi.CYAN);
        cloudStrArray[1] =  Ansi.colorize("│ ",Ansi.CYAN)+"#"+cloud.getId()+ Ansi.colorize(" │ ",Ansi.CYAN);
        for(ColorS color : ColorS.values()){
            cloudStrArray[1] += Ansi.colorize(String.valueOf(cloud.getStudents(color).size()), color.getAnsiEscapeCode())+" ";
        }
        cloudStrArray[1] +=  Ansi.colorize("│",Ansi.CYAN);
        cloudStrArray[2] =  Ansi.colorize("└────┴───────────┘",Ansi.CYAN);
        return cloudStrArray;
    }

    public static String cloudsToString(List<ReducedCloud> clouds){
        String cloudStr = "";
        for(int i = 0; i<3; i++) {
            for (ReducedCloud cloud : clouds) {
                cloudStr += cloudToStringArray(cloud)[i];
                cloudStr += "  ";
            }
            cloudStr += "\n";
        }
        return cloudStr;
    }

    public static String[] islandToStringArray(ReducedIsland island){
        String[]  islandStrArray = new String[5];
        islandStrArray[0] =  Ansi.colorize("┌─────┬───┬──────┐",Ansi.GREEN);
        islandStrArray[1] =  Ansi.colorize("│",Ansi.GREEN)+" #"
                +(island.getId() < 10 ? island.getId()+"  " : island.getId()+" ")+Ansi.colorize("│",Ansi.GREEN)
                + (island.getTower()!=null ? Ansi.colorize(" T ",island.getTower().getAnsiEscapeCode()) : "   ")
                +Ansi.colorize("│",Ansi.GREEN)+ (island.isMotherNature() ? "  MN  ":"      ")
                +Ansi.colorize("│",Ansi.GREEN);
        islandStrArray[2] = Ansi.colorize("├─────┴───┴──────┤",Ansi.GREEN);
        islandStrArray[3] = Ansi.colorize("│",Ansi.GREEN)+" ";
        for(ColorS color : ColorS.values()){
            islandStrArray[3] += Ansi.colorize(String.valueOf(island.getStudents(color).size()), color.getAnsiEscapeCode())+"  ";
        }
        islandStrArray[3] += ""+Ansi.colorize("│",Ansi.GREEN);
        islandStrArray[4] = Ansi.colorize("└────────────────┘",Ansi.GREEN);
        return islandStrArray;
    }

    public static String islandsToString(List<ReducedIsland> islands){
        final int islandsPerRow = 6;

        String islandStr = "";
        List<ReducedIsland> islandFirstRow = new ArrayList<>(islands.subList(0,(islands.size()<islandsPerRow?islands.size():islandsPerRow)));
        for(int i = 0; i<5; i++) {
            for (ReducedIsland island : islandFirstRow) {
                islandStr += islandToStringArray(island)[i];
                islandStr += "  ";
            }
            islandStr += "\n";
        }
        if(islands.size()>islandsPerRow){
            List<ReducedIsland> islandSecondRow =  new ArrayList<>(islands.subList(islandsPerRow,islands.size()));
            for(int i = 0; i<5; i++) {
                for (ReducedIsland island : islandSecondRow) {
                    islandStr += islandToStringArray(island)[i];
                    islandStr += "  ";
                }
                islandStr += "\n";
            }
        }

        return islandStr;
    }

    public static String boardToString(ReducedBoard b){
        ColorS colors[] = ColorS.values();
        String model = "";
            model +="		 "+Ansi.colorize(" "+b.getPlayer()+" ",Ansi.BACKGROUND_YELLOW,Ansi.BLACK)+"’s BOARD                                    \n";
            model +="╔══════╦══════════╦═══════════════════════════╦══════╦════════╗\n";
            model +="║      ║ ENTRANCE ║ DINING ROOM               ║ PROF ║ TOWERS ║\n";
            model +="╠══════╬══════════╬═══════════════════════════╬══════╬════════╣\n";

        for(ColorS color : colors){
            model +="║"+Ansi.colorize(color.toString().toUpperCase(Locale.ROOT)+(String.join("", Collections.nCopies(6-color.toString().length(), " "))),color.getAnsiEscapeCode())+"║    "+b.getEntranceStudents().stream().filter(c-> c == color).count()+"     ║ ";
            int students = b.getStudents().get(color).intValue();
            for(int i = 0; i<10; i++){
                String placeholder = i < students ? "S" : "_";

                if(i == 2 || i == 5 || i == 8){
                    model += "("+Ansi.colorize(placeholder, color.getAnsiEscapeCode())+") ";
                }else{
                    model += Ansi.colorize(placeholder+" ", color.getAnsiEscapeCode());
                }
            }
            model += "║  ";
            // PROFESSORS
            model += b.getProfessors().contains(color) ? 'P' : '_';
            model += "   ║  ";
            // TOWERS
            int towers = b.getNumOfTowers();
            int printedTowres = 0;
            for(int i = 0; i<2;i++){
                if(printedTowres<towers){
                    model += "T";
                    printedTowres++;
                }else{
                    model += "_";
                }
                model +="  ";
            }
            model += "║\n";
        }
        model +="╚══════╩══════════╩═══════════════════════════╩══════╩════════╝\n";
        return model;
    }

    public static String assistantDeckToString(List<ReducedAssistant> cards){
        String cardsStr = "";
        for(int i = 0; i<3; i++) {
            for (ReducedAssistant card : cards) {
                cardsStr += assistantToStringArray(card)[i]+" ";
            }
            cardsStr += "\n";
        }
        return cardsStr;
    }

    public static String[] assistantToStringArray(ReducedAssistant card){
        String[]  cardStrArray = new String[3];
        cardStrArray[0] = "┌───┬───┐";
        cardStrArray[1] = "│ "+card.getId()+" │ "+card.getMotherNatureMovements()+" │";
        cardStrArray[2] = "└───┴───┘";
        return cardStrArray;
    }


    public static void error(String string){
        System.out.print(Ansi.colorize(string,Ansi.RED));
    }
    public static void showExpert(ReducedModelExpertMode reducedModelExpertMode){
        if (reducedModelExpertMode.getCurrentEffect()!=null) {
            System.out.println(reducedModelExpertMode.getCurrentEffect());
        }
        for (String player : reducedModelExpertMode.getCoins().keySet()){
            System.out.println(player + " has " + reducedModelExpertMode.getCoins().get(player) + " coins");
        }
        for (ReducedIsland island : reducedModelExpertMode.getNoEntryTiles().keySet()){
            System.out.println(island.getId() + " has " + reducedModelExpertMode.getNoEntryTiles().get(island));
        }
        for (ReducedCharacter character : reducedModelExpertMode.getCharacters()){
            System.out.println(character.getId() + " costs " + character.getCost());
        }
    }

    public void print(String string){
        System.out.println(string);
    }

    /**
     * chooses a valid island from the reduced model
     * @param reducedModel reduced model
     * @return island chosen
     */
    public static int chooseValidIsland(ReducedModel reducedModel){
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        int islandChosen = 0;
        while (!exit){
            try {
                islandChosen = Integer.parseInt(in.nextLine());
                if (islandChosen < 1 || islandChosen > reducedModel.getIslands().size()) {
                    System.out.println("Invalid island, choose another one");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        return islandChosen;
    }

    /**
     * chooses a valid student from the character card
     * @param reducedModelExpertMode reduced model
     * @param character character card
     * @return student chosen
     */
    public static int chooseValidStudentOnCard(ReducedModelExpertMode reducedModelExpertMode, int character){
        Scanner in = new Scanner(System.in);
        ReducedCharacter card = reducedModelExpertMode.getCharacters().
                stream().filter(c -> c.getId()==character).findFirst().orElse(null);

        List<ColorS> studentsOnCard = card != null ? card.getStudents() : new ArrayList<>();

        System.out.println("Students on card: ");
        for (int i = 0; i < studentsOnCard.size(); i++){
            System.out.println((i+1) + " : " + studentsOnCard.get(i).toString());
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
        return studentChosen;
    }

    /**
     * chooses a valid color from the available
     * @return color chosen
     */
    public static ColorS chooseValidColor(){
        Scanner in = new Scanner(System.in);
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
        return colorChosen;
    }

    /**
     * chooses a valid student from the entrance
     * @param reducedModel reduced model
     * @param player board's owner
     * @return student chosen
     */
    public static int chooseValidEntranceStudent(ReducedModel reducedModel, String player){
        List<Integer> entranceAvailable = new ArrayList<>();

        ReducedBoard myBoard = reducedModel.getBoards().stream()
                .filter(b -> b.getPlayer().equals(player))
                .findFirst().orElse(null);

        if (myBoard != null) {
            for (int i = 1; i < myBoard.getEntranceStudents().size() + 1; i++) {
                entranceAvailable.add(i);
            }
        }
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        int studentChosen = 0;
        while (!exit){
            try {
                studentChosen = Integer.parseInt(in.nextLine());
                if (!entranceAvailable.contains(studentChosen)) {
                    System.out.println("Invalid student position ");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have to insert a number ");
            }
        }
        return studentChosen;
    }
}
