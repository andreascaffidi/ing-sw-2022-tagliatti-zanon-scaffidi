package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.CreateLobbySceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class GUICreateLobbyState extends AbstractClientState {

    private final Client client;
    private Parent root;

    CreateLobbySceneController createLobbySceneController;

    public GUICreateLobbyState(Client client){
        this.client = client;
    }

    @Override
    public void render() {
        URL url = getClass().getResource("/fxml/CreateLobbyScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            createLobbySceneController = loader.getController();
            createLobbySceneController.setClient(client);
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void serverError(String message) {
        createLobbySceneController.alert(message);
    }
}
