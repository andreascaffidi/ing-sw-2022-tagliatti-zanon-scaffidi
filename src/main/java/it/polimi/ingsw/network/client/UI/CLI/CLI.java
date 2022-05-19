package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.*;
import it.polimi.ingsw.network.client.reducedModel.ReducedIsland;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;

import java.io.IOException;


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
            System.out.print(   " ___  __               ___      __  \n" +
                                "|__  |__) |  / \\  |\\ |  |  \\ / /__` \n" +
                                "|___ |  \\ | /~-~\\ | \\|  |   |  .__/ \n" +
                                "                                   \n");
    }

    @Override
    public void init()
    {

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

    //FIXME: è una prova
    public static void showModel(ReducedModel reducedModel){
        System.out.println("\n");
        String model = "------------------------------------------------------------\n";

        model += "Current Player: ";
        model += Ansi.Yellow.colorize(reducedModel.getCurrentPlayer());
        model += "\n";
        model += "\n";

        model += showIslands(reducedModel);

        model += showClouds(reducedModel);

        model += "BOARDS \n";
        model += "\n";
        for(ReducedBoard b : reducedModel.getBoards())
        {
            model += showBoard(b);
        }
        System.out.println(model);
    }

    public static String showIslands(ReducedModel reducedModel){
        String model = "";
        model += Ansi.Yellow.colorize("ISLANDS")+"\n";


        for(ReducedIsland i : reducedModel.getIslands())
        {
            model +="\t";
            model += (i.getId() + 1);
            model += ": ";
            model += i.isMotherNature() ? "[MN] ": "";
            for(ColorS s : i.getStudents()) {
                model += s.toAnsiString()+" ";
            }
            model += "\n";
        }
        model += "\n";
        //System.out.println(model);
        return model;
    }

    public static String showClouds(ReducedModel reducedModel){
        String model = "";

        model += Ansi.Yellow.colorize("CLOUDS")+"\n";

        for(ReducedCloud c : reducedModel.getClouds())
        {
            model += c.getId() + 1;
            model += ": ";
            for(ColorS s : c.getStudents())
            {
                model += s.toAnsiString();
                model += " ";
            }
            model += "\n";
        }
        model += "\n";
        //System.out.println(model);
        return model;
    }

    public static String showBoard(ReducedBoard b){
        String model = "------------------------------------------------------------\n";
        model += "\t\t\t\t\t\t"+Ansi.Yellow.colorize(b.getPlayer())+"\n";
        model += "------------------------------------------------------------\n";
        model += Ansi.Yellow.colorize("ENTRANCE")+"\n";

        for(ColorS c : b.getEntranceStudents())
        {
            model += c.toAnsiString();
            model += " ";
        }
        model += "\n\n";


        model += Ansi.Yellow.colorize("DINING ROOM")+"\n";

        model += Ansi.YELLOW+"\tYELLOW: "+b.getYellowStudents()+" / 10\n"+Ansi.RESET;
        model += Ansi.BLUE+"\tBLUE: "+b.getBlueStudents()+" / 10\n"+Ansi.RESET;;
        model += Ansi.GREEN+"\tGREEN: "+b.getGreenStudents()+" / 10\n"+Ansi.RESET;;
        model += Ansi.MAGENTA+"\tPINK: "+b.getPinkStudents()+" / 10\n"+Ansi.RESET;;
        model += Ansi.RED+"\tRED: "+b.getRedStudents()+" / 10\n\n"+Ansi.RESET;;

        model += Ansi.Yellow.colorize("PROFESSORS TABLE")+"\n";;
        if(b.getProfessors().size() == 0){
            model += "[no professors in your board]";
        }else{
            for(ColorS p : b.getProfessors()){
                model += p.toAnsiString()+" ";
            }
        }
        model += "\n\n";


        model += Ansi.Yellow.colorize("TOWERS")+"\n";
        model += "\t"+b.getTowerColor()+ " -- "+b.getNumOfTowers()+" / []\n\n";

        model += Ansi.Yellow.colorize("ASSISTANT DECK")+"\n";
        for(ReducedAssistant a : b.getAssistantDeck().getAssistantCards())
        {
            model += "[";
            model += a.getId();
            model += ", ";
            model += a.getMotherNatureMovements();
            model += "] ";
        }
        model += "\n\n\n";
        return model;
    }



    public void print(String string){
        System.out.println(string);
    }
}
