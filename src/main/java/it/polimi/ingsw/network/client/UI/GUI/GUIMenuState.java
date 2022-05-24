package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.MenuSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class GUIMenuState extends AbstractClientState {
    private final Client client;
    private Parent root;

    public GUIMenuState(Client client){
        this.client = client;
    }

    @Override
    public void render() {
        URL url = getClass().getResource("/fxml/MenuScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            MenuSceneController menuSceneController = loader.getController();
            menuSceneController.setClient(client);
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
