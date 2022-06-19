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

/**
 * CLI class (command line input UI)
 */
public class CLI implements UI {

    /**
     * gets the client state based on the given ClientState enum
     * @param clientState client state enum
     * @param client client
     * @return abstract client state
     */
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

    //TODO: tests this method on windows terminal
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
        } catch (IOException | InterruptedException ignored) {}
    }

    /**
     * prints the logo of the game
     */
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

    /**
     * prints the model
     * @param reducedModel reduced model
     */
    public static void showModel(ReducedModel reducedModel){
        String _ISLANDS = "\n\uD83C\uDFDD \uD83C\uDFDD \uD83C\uDFDD "+ Ansi.colorize(" I S L A N D S ",Ansi.HIGH_INTENSITY,Ansi.BACKGROUND_GREEN,Ansi.BLACK) + " \uD83C\uDFDD \uD83C\uDFDD \uD83C\uDFDD \n";
        String _CLOUDS;
        String _BOARDS;

        _ISLANDS += islandsToString(reducedModel.getIslands(), reducedModel);
        _BOARDS = boardsToString(reducedModel.getBoards(), reducedModel);
        if (reducedModel instanceof ReducedModelExpertMode){
            _CLOUDS = expertCloudAndCharacterToString(reducedModel.getClouds(), ((ReducedModelExpertMode) reducedModel).getCharacters(), ((ReducedModelExpertMode) reducedModel).getCurrentEffect());
        } else {
            _CLOUDS = cloudsToString(reducedModel.getClouds());
        }

        System.out.println("\n");
        String model = String.join("", Collections.nCopies(120, "═"))+"\n";

        model += "C U R R E N T   P L A Y E R: ";
        model += Ansi.colorize(" "+reducedModel.getCurrentPlayer()+" ",Ansi.BACKGROUND_YELLOW,Ansi.BLACK,Ansi.HIGH_INTENSITY);
        model += "\n";

        model += _ISLANDS;

        model += _CLOUDS;

        model += "\n\uD83C\uDFB2 \uD83C\uDFB2 \uD83C\uDFB2 " + Ansi.colorize(" B O A R D S ",Ansi.BACKGROUND_RED,Ansi.BLACK,Ansi.HIGH_INTENSITY)+" \uD83C\uDFB2 \uD83C\uDFB2 \uD83C\uDFB2\n";

        model += _BOARDS;

        System.out.println(model);
    }

    /**
     * creates an array of string to print an island
     * @param island island to print
     * @param BORDER_COLOR border color for the islands
     * @return string that represents an island
     */
    private static String[] islandToStringArray(ReducedIsland island, String BORDER_COLOR){
        String[]  islandStrArray = new String[5];
        islandStrArray[0] =  Ansi.colorize("┌─────┬───┬──────┐",BORDER_COLOR);
        islandStrArray[1] =  Ansi.colorize("│",BORDER_COLOR)+" #"
                +(island.getId() < 9 ? (island.getId()+1)+"  " : (island.getId()+1)+" ")+Ansi.colorize("│",BORDER_COLOR)
                + (island.getTower()!=null ? Ansi.colorize(island.getNumOfTowers()+" T",island.getTower().getAnsiEscapeCode(), Ansi.BACKGROUND_GREY) : "   ")
                +Ansi.colorize("│",BORDER_COLOR)+ (island.isMotherNature() ? "  MN  ":"      ")
                +Ansi.colorize("│",BORDER_COLOR);
        islandStrArray[2] = Ansi.colorize("├─────┴───┴──────┤",BORDER_COLOR);
        islandStrArray[3] = Ansi.colorize("│",BORDER_COLOR)+" ";
        for(ColorS color : ColorS.values()){
            islandStrArray[3] += Ansi.colorize(String.valueOf(island.getStudents(color).size()), color.getAnsiEscapeCode())+"  ";
        }
        islandStrArray[3] += ""+Ansi.colorize("│",BORDER_COLOR);
        islandStrArray[4] = Ansi.colorize("└────────────────┘",BORDER_COLOR);
        return islandStrArray;
    }

    /**
     * prints all islands
     * @param islands islands
     * @return string that represents all islands
     */
    private static String islandsToString(List<ReducedIsland> islands, ReducedModel reducedModel){
        final int islandsPerRow = 6;
        final int islandsLength = islands.size();
        final int rowsToDo = islandsLength/islandsPerRow+(islandsLength%islandsPerRow>0?1:0);
        int rowsDone = 0;
        StringBuilder islandStr = new StringBuilder();
        while(rowsToDo-rowsDone>0){
            for(int i = 0; i<5; i++) {
                for(int j = rowsDone*islandsPerRow; j<islandsLength && j<(islandsPerRow*(rowsDone+1)); j++) {
                    if(reducedModel instanceof  ReducedModelExpertMode){
                        islandStr.append(islandToStringArray(islands.get(j),
                                ((ReducedModelExpertMode) reducedModel).getNoEntryTiles()
                                        .get(islands.get(j).getId()) ? Ansi.RED : Ansi.GREEN)[i]);
                    } else {
                        islandStr.append(islandToStringArray(islands.get(j), Ansi.GREEN)[i]);
                    }
                    islandStr.append("  ");
                }
                islandStr.append("\n");
            }
            rowsDone++;
        }

        return islandStr.toString();
    }

    /**
     * creates an array of string to print a cloud
     * @param cloud cloud to print
     * @return string that represents an island
     */
    private static String[] cloudToStringArray(ReducedCloud cloud){
        String[]  cloudStrArray = new String[3];
        cloudStrArray[0] = Ansi.colorize("┌────┬───────────┐",Ansi.CYAN);
        cloudStrArray[1] =  Ansi.colorize("│ ",Ansi.CYAN)+"#"+(cloud.getId()+1)+ Ansi.colorize(" │ ",Ansi.CYAN);
        for(ColorS color : ColorS.values()){
            cloudStrArray[1] += Ansi.colorize(String.valueOf(cloud.getStudents(color).size()), color.getAnsiEscapeCode())+" ";
        }
        cloudStrArray[1] +=  Ansi.colorize("│",Ansi.CYAN);
        cloudStrArray[2] =  Ansi.colorize("└────┴───────────┘",Ansi.CYAN);
        return cloudStrArray;
    }

    /**
     * prints all the clouds
     * @param clouds clouds to print
     * @return a string that represents the clouds
     */
    private static String cloudsToString(List<ReducedCloud> clouds){
        StringBuilder cloudStr = new StringBuilder("☁ ☁ ☁ " + Ansi.colorize(" C L O U D S ", Ansi.HIGH_INTENSITY, Ansi.BACKGROUND_CYAN, Ansi.BLACK)+ " ☁ ☁ ☁ \n");
        for(int i = 0; i<3; i++) {
            for (ReducedCloud cloud : clouds) {
                cloudStr.append(cloudToStringArray(cloud)[i]);
                cloudStr.append("  ");
            }
            cloudStr.append("\n");
        }
        return cloudStr.toString();
    }

    /**
     * prints clouds and expert mode information (only when game is in expert mode)
     * @param clouds clouds to print
     * @param characters characters to print
     * @param currentEffect current effect to print
     * @return string that represents clouds and expert mode information
     */
    private static String expertCloudAndCharacterToString(List<ReducedCloud> clouds,List<ReducedCharacter> characters, String currentEffect){
        StringBuilder cloudAndCharactersStr = new StringBuilder("☁ ☁ ☁ " + Ansi.colorize(" C L O U D S ", Ansi.HIGH_INTENSITY, Ansi.BACKGROUND_CYAN, Ansi.BLACK) + " ☁ ☁ ☁              " + Ansi.CARD_ICON + " " + Ansi.CARD_ICON + " " + Ansi.CARD_ICON + " " + Ansi.colorize(" C H A R A C T E R   C A R D S ", Ansi.HIGH_INTENSITY, Ansi.BACKGROUND_MAGENTA, Ansi.BLACK) + " " + Ansi.CARD_ICON + " " + Ansi.CARD_ICON + " " + Ansi.CARD_ICON + " " + "\n");
        final int _ROWS = clouds.size() < 3 ? 5 : 6;
        final int cloudsPerRow = 2;
        final int cloudSize = clouds.size();
        for(int i = 0; i < _ROWS; i++) {

            int index = i / 3 * 2;
            int cloudRowIndex = i % 3;

            // CLOUDS
            while(index < cloudSize && index < cloudsPerRow*((i/3)+1)){
                cloudAndCharactersStr.append(cloudToStringArray(clouds.get(index))[cloudRowIndex]);
                cloudAndCharactersStr.append("  ");
                index++;
            }
            if(cloudSize%cloudsPerRow!=0 && cloudSize == (index)){
                cloudAndCharactersStr.append(String.join("", Collections.nCopies(20, " ")));
            }
            cloudAndCharactersStr.append("    ");
            // CHARACTERS
            if(i<characters.size()){
                cloudAndCharactersStr.append(characterToString(characters.get(i)));
            }
            if(i == characters.size()+1){
                if(cloudSize < 3){
                    cloudAndCharactersStr.append(String.join("", Collections.nCopies(40, " ")));
                }
                cloudAndCharactersStr.append(Ansi.EFFECT_ICON + " CURRENT EFFECT: ").append(currentEffect != null ? currentEffect : " _ ");
            }
            cloudAndCharactersStr.append("\n");
        }
        return cloudAndCharactersStr.toString();
    }

    /**
     * prints a character card
     * @param card character card
     * @return string that represents a character card
     */
    private static String characterToString(ReducedCharacter card){
        StringBuilder cardString = new StringBuilder();
        cardString.append("#").append(card.getId() < 10 ? card.getId() + " " : card.getId()).append(" │ ").append(Ansi.MONEY_BAG_ICON).append(" ").append(card.getCost()).append(" │ ");
        for(ColorS color : ColorS.values()){
            cardString.append(Ansi.colorize(String.valueOf(card.getStudents(color).size()), color.getAnsiEscapeCode())).append(" ");
        }
        return cardString.toString();
    }

    /**
     * creates a string to print a board
     * @param b school board to print
     * @return string that represents a school board
     */
    private static String[] boardToStringArray(ReducedBoard b, int coins){
        String[] boardStrArray = new String[12];

        ColorS[] colors = ColorS.values();

        boardStrArray[0] ="		 "+Ansi.colorize(" "+b.getPlayer()+" ",Ansi.BACKGROUND_YELLOW,Ansi.BLACK)+"’s BOARD                                    ";
        boardStrArray[1] ="╔══════╦═══════════════════════════╦═════╦════════╗";
        boardStrArray[2] ="║COLOR ║ DINING ROOM               ║ PRO ║ TOWERS ║";
        boardStrArray[3] ="╠══════╬═══════════════════════════╬═════╬════════╣";

        int index = 4;
        final int towers = b.getNumOfTowers();
        int printedTowers = 0;
        for(ColorS color : colors){
            StringBuilder colorRow = new StringBuilder();
            colorRow.append("║").append(Ansi.colorize(color.toString().toUpperCase(Locale.ROOT) + (String.join("", Collections.nCopies(6 - color.toString().length(), " "))), color.getAnsiEscapeCode())).append("║ ");

            int students = b.getStudents().get(color);
            for(int i = 0; i<10; i++){
                String placeholder = i < students ? "S" : "_";

                if(i == 2 || i == 5 || i == 8){
                    colorRow.append("(").append(Ansi.colorize(placeholder, color.getAnsiEscapeCode())).append(") ");
                }else{
                    colorRow.append(Ansi.colorize(placeholder + " ", color.getAnsiEscapeCode()));
                }
            }
            colorRow.append("║  ");

            // PROFESSORS
            colorRow.append(b.getProfessors().contains(color) ? 'P' : '_');
            colorRow.append("  ║  ");

            // TOWERS
            for(int i = 0; i<2;i++){
                if(printedTowers<towers){
                    colorRow.append(Ansi.colorize("T", b.getTowerColor().getAnsiEscapeCode(), Ansi.BACKGROUND_GREY));
                    printedTowers++;
                }else{
                    colorRow.append("_");
                }
                colorRow.append("  ");
            }
            colorRow.append("║");
            boardStrArray[index++] = colorRow.toString();
        }

        boardStrArray[9]  ="╠══════╩═══╦═══════════════════════╩═════╩════════╣";
        boardStrArray[10] ="║ ENTRANCE ║";
        List<ColorS> students = b.getEntranceStudents();
        for (int i = 0; i < 9; i++) {
            boardStrArray[10] += i < b.getEntranceStudents().size() ? " "+Ansi.colorize("S", students.get(i).getAnsiEscapeCode())+" " : " _ ";
        }
        if (coins == -1){
            boardStrArray[10] +=String.join("", Collections.nCopies(11, " "))+"║";
        } else {
            boardStrArray[10] +=" ║ COINS "+coins+" ║";
        }
        boardStrArray[11] ="╚══════════╩══════════════════════════════════════╝";
        return boardStrArray;
    }

    /**
     * prints all the school boards
     * @param boards boards
     * @return string that represents all the school boards
     */
    private static String boardsToString(List<ReducedBoard> boards, ReducedModel reducedModel){
        final int boardsPerRow = 2;
        StringBuilder boardsStr = new StringBuilder();
        int boardsNum = boards.size();
        int rowsDone = 0;
        while((boardsNum/boardsPerRow)+(boardsNum%boardsPerRow)-(rowsDone)>0){
            // Boards
            for(int i = 0; i<12; i++) {
                for(int j = rowsDone*boardsPerRow; j<boardsNum && j<(boardsPerRow*(rowsDone+1));j++){
                    if (reducedModel instanceof ReducedModelExpertMode){
                        boardsStr.append(boardToStringArray(boards.get(j), ((ReducedModelExpertMode) reducedModel)
                                .getCoins().get(boards.get(j).getPlayer()))[i]).append(" ");
                    } else {
                        boardsStr.append(boardToStringArray(boards.get(j), -1)[i]).append(" ");
                    }
                }
                boardsStr.append("\n");
            }
            // Assistant Deck
            for(int i = 0; i<6; i++) {
                for(int j = rowsDone*boardsPerRow; j<boardsNum && j<(boardsPerRow*(rowsDone+1));j++){
                    String [] deckArray = assistantDeckToStringArray(boards.get(j).getAssistantDeck().getAssistantCards());
                    String rowDeck = i < deckArray.length ?  deckArray[i] : "";
                    boardsStr.append(rowDeck);
                    boardsStr.append(String.join("", Collections.nCopies(51 - rowDeck.length(), " ")));
                    boardsStr.append(" ");

                }
                boardsStr.append("\n");
            }
            for(int j = rowsDone*boardsPerRow; j<boardsNum && j<(boardsPerRow*(rowsDone+1));j++){
                ReducedAssistant assistant = boards.get(j).getAssistantDeck().getPlayedAssistant();
                String discardPile = Ansi.CARD_ICON+" LAST ASSISTANT PLAYED: "+ (assistant != null ? ("#"+assistant.getId()+" - "+assistant.getMotherNatureMovements()+" M.N.M."): " - ");
                boardsStr.append(discardPile);
                boardsStr.append(String.join("", Collections.nCopies(53 - discardPile.length(), " ")));
            }
            boardsStr.append("\n");
            rowsDone++;
        }

        return boardsStr.toString();
    }

    /**
     * prints the entire assistant deck
     * @param cards assistant deck
     * @return string that represents assistant deck
     */
    private static String[] assistantDeckToStringArray(List<ReducedAssistant> cards){
        final int cardsPerRow = 5;
        int deckLength = cards.size();
        int rowsToDo = deckLength/cardsPerRow+(deckLength%cardsPerRow>0?1:0);
        String[] cardsStrArray = new String[3*rowsToDo];
        int rowsDone = 0;
        while(rowsToDo-rowsDone>0){
            for(int i = 0; i<3; i++) {
                int index = i+(3*rowsDone);
                cardsStrArray[index] = "";
                for(int j = rowsDone*cardsPerRow; j<deckLength && j<(cardsPerRow*(rowsDone+1)); j++){
                    cardsStrArray[index] += assistantToStringArray(cards.get(j))[i]+" ";
                }
            }
            rowsDone++;
        }
        return cardsStrArray;
    }

    /**
     * prints an assistant card
     * @param card assistant card to print
     * @return string that represents an assistant card
     */
    private static String[] assistantToStringArray(ReducedAssistant card){
        String[]  cardStrArray = new String[3];
        cardStrArray[0] = "┌───┬───┐";
        cardStrArray[1] = "│#"+(card.getId()<10 ? card.getId()+" " :  card.getId()) +"│ "+card.getMotherNatureMovements()+" │";
        cardStrArray[2] = "└───┴───┘";
        return cardStrArray;
    }

    /**
     * prints a string in a request input format
     * @param string request input string
     */
    public static void CTA(String string){
        System.out.print(string +": "+ Ansi.TYPING_ICON +" ");
    }

    /**
     * prints a string in an error format
     * @param string error string
     */
    public static void error(String string){
        System.out.print("\t "+Ansi.ERROR_ICON+"️ "+Ansi.colorize(string,Ansi.RED)+"\n");
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

        ReducedBoard myBoard = reducedModel.getBoard(player);

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
