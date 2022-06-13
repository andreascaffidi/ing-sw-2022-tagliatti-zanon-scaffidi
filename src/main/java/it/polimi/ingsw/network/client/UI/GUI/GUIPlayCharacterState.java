package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.PlayAssistantSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.PlayCharacterSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GUIPlayCharacterState extends AbstractClientState {
    private final Client client;
    private Parent root;

    private PlayCharacterSceneController playCharacterSceneController;

    public GUIPlayCharacterState(Client client){
        this.client = client;
    }

    @Override
    public void render() {
        URL url = getClass().getResource("/fxml/PlayCharacterScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            playCharacterSceneController = loader.getController();
            playCharacterSceneController.setClient(client);
            playCharacterSceneController.setup();
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void serverError(String message) {
        playCharacterSceneController.alert(message);
    }
}