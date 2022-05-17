package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;

import java.util.ArrayList;
import java.util.List;

public class GUI implements UI {

    public GUI()
    {
    }

    private final List<String> scenes = new ArrayList<>();
    private String currentScene;
    public void init(){
        JavaFXGUI.main(null);
    }
    /**
     * This method adds a scene in the sceneMap in order to be retrieved when the scene is shown again later.
     * @param fxmlFile
     */
    public void addScene(String fxmlFile) {
        scenes.add(fxmlFile);
    }

    /**
     * This method retrieves a scene in the sceneMap
     *
     * @param fxmlFile a String representing the path of the fxml file
     * @return the SavedScene associated with the fxmlFile if present, otherwise null
     */
    public String getScene(String fxmlFile){
        return scenes.get(scenes.indexOf(fxmlFile));
    }

    /**
     * This method is used to set the current SavedScene
     * @param current the SavedScene instance to be set as the current one
     */
    public void setCurrentScene(String current){
        currentScene = current;
    }

    /**
     * This method is used to retrieve the current SavedScene
     * @return the current SavedScene instance
     */
    public String getCurrentScene(){
        return currentScene;
    }

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

}
