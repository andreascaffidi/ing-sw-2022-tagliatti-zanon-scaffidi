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
        System.out.println("QUESTO è il modello");
        String model = "";

        model += "\nCurrent Player: ";
        model += reducedModel.getCurrentPlayer();
        model += "\n";

        model += "Islands:\n";
        for(ReducedIsland i : reducedModel.getIslands())
        {
            model += i.getId();
            model += ":\t";
            for(ColorS s : i.getStudents()) {
                model += s.toString();
                model += ", ";
            }
        }
        model += "\n";

        model += "Clouds\n";
        for(ReducedCloud c : reducedModel.getClouds())
        {
            model += c.getId();
            model += ":\t";
            for(ColorS s : c.getStudents())
            {
                model += s.toString();
                model += ", ";
            }
        }
        model += "\n";

        model += "Boards";
        for(ReducedBoard b : reducedModel.getBoards())
        {
            model += "\nBoard owner: ";
            model += b.getPlayer();
            model += "\n";

            model += "Tower color: ";
            model += b.getTowerColor();
            model += "\n";

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
            model += b.getYellowStudents();
            model += "\n";

            model += "Entrance Students: ";
            for(ColorS c : b.getEntranceStudents())
            {
                model += c;
                model += ", ";
            }

            model += "Professors: ";
            for(ColorS p : b.getProfessors())
            {
                model += p;
                model += ", ";
            }

            model += "\nNum of Towers: ";
            model += b.getNumOfTowers();

            model += "\nAssistant Deck: ";
            //TODO: gestire il deck
            for(ReducedAssistant a : b.getAssistantDeck().getAssistantCards())
            {
                model += "[";
                model += a.getId();
                model += ", ";
                model += a.getMotherNatureMovements();
                model += ", ";
                model += a.getWizard();
                model += "], ";
            }
        }
        System.out.println(model);

    }
}
