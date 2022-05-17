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

    public CLI(){

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
    //FIXME: it doesn't work
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

    //FIXME: it's horrible
    public static void showModel(ReducedModel reducedModel){
        System.out.println("\n");
        String model = "";

        model += "Current Player: ";
        model += reducedModel.getCurrentPlayer();
        model += "\n";
        model += "\n";

        model += "Islands:\n";
        for(ReducedIsland i : reducedModel.getIslands())
        {
            model += i.getId() + 1;
            model += ": ";
            if (i.isMotherNature()){
                model += "--MOTHER NATURE-- ";
            }
            if (i.getTower() != null){
                model += "--TOWER = " + i.getNumOfTowers() + " " + i.getTower() + " --";
            }
            model += " --STUDENTS = ";
            for(ColorS s : i.getStudents()) {
                model += s + " ";
            }
            model += "--\n";
        }
        model += "\n";

        model += "Clouds\n";
        for(ReducedCloud c : reducedModel.getClouds())
        {
            model += c.getId() + 1;
            model += ": ";
            for(ColorS s : c.getStudents())
            {
                model += s.toString();
                model += " ";
            }
            model += "\n";
        }
        model += "\n";

        model += "-----------------------------------------------------------\n";

        model += "Boards: \n";
        model += "\n";
        for(ReducedBoard b : reducedModel.getBoards())
        {
            model += "Board owner: ";
            model += b.getPlayer();
            model += "\n";

            model += "Tower color: ";
            model += b.getTowerColor();
            model += "\n";

            model += "\n";
            model += "Dining Room: \n";
            model += "Yellow Students: ";
            model += b.getYellowStudents();
            model += "\n";

            model += "Blue Students: ";
            model += b.getBlueStudents();
            model += "\n";

            model += "Green Students: ";
            model += b.getGreenStudents();
            model += "\n";

            model += "Pink Students: ";
            model += b.getPinkStudents();
            model += "\n";

            model += "Red Students: ";
            model += b.getRedStudents();
            model += "\n";

            model += "Entrance Students: ";
            for(ColorS c : b.getEntranceStudents())
            {
                model += c;
                model += " ";
            }
            model += "\n";

            model += "Professors: ";
            for(ColorS p : b.getProfessors())
            {
                model += p;
                model += " ";
            }
            model += "\n";

            model += "Num of Towers: ";
            model += b.getNumOfTowers();
            model += "\n";

            model += "\n";
            model += "Assistant Deck: ";
            for(ReducedAssistant a : b.getAssistantDeck().getAssistantCards())
            {
                model += "[Value = ";
                model += a.getId();
                model += ", Movement = ";
                model += a.getMotherNatureMovements();
                model += "] ";
            }
            if (b.getAssistantDeck().getPlayedAssistant() != null) {
                model += "\n\nPlayed card: [Value = " + b.getAssistantDeck().getPlayedAssistant().getId();
                model += ", Movement = " + b.getAssistantDeck().getPlayedAssistant().getMotherNatureMovements() + "] ";
            }
            model += "\n";
            model += "\n";
            model += "-----------------------------------------------------------\n";
        }
        System.out.println(model);
    }
}
