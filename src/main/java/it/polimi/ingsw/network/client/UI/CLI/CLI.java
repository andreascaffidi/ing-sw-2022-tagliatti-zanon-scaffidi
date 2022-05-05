package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedIsland;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;


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
            default : return null;
        }
    }

    //FIXME: è una prova
    public static void showModel(ReducedModel reducedModel){
        System.out.println("QUESTO è il modello");
        String model = "";

        model += "Islands\n";
        for(ReducedIsland i : reducedModel.getIslands())
        {
            model += i.getId();
            model += "\t";
            for(ColorS s : i.getStudents())
            {
                model += s.toString();
                model += ", ";
            }
        }
        System.out.println(model);
    }
}
