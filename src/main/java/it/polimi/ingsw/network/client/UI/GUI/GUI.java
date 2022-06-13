package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;

/**
 * GUI class (graphical user interface UI)
 */
public class GUI implements UI {

    /**
     * starts the thread for JavaFX
     * @param disconnectClient method to call when JavaFX is stopped
     */
    public void startJavaFX(Runnable disconnectClient){
        JavaFXGUI.disconnectClient = disconnectClient;
        new Thread(JavaFXGUI::launchJavaFX).start();
    }

    /**
     * gets the client state based on the given ClientState enum
     * @param clientState client state enum
     * @param client client
     * @return abstract client state
     */
    @Override
    public AbstractClientState getClientState(ClientState clientState, Client client)
    {
        switch (clientState){
            case WELCOME: return new GUIWelcomeState(client);
            case MENU: return new GUIMenuState(client);
            case CREATE_LOBBY: return new GUICreateLobbyState(client);
            case JOIN_LOBBY: return new GUIShowLobbiesState(client);
            case PLAY_ASSISTANT: return new GUIPlayAssistantState(client);
            case WAITING: return new GUIWaitingState(client);
            case MOVE_STUDENTS: return new GUIMoveStudentsState(client);
            case MOVE_MN: return new GUIMoveMotherNatureState(client);
            case CHOOSE_CLOUD: return new GUIChooseCloudState(client);
            case END_GAME: return new GUIEndGameState(client);
            case PLAY_CHARACTER: return new GUIPlayCharacterState(client);
            default : return null;
        }
    }

    /**
     *
     * @param reducedModel reduced model
     */
    @Override
    public void showModel(ReducedModel reducedModel) {

    }

}
