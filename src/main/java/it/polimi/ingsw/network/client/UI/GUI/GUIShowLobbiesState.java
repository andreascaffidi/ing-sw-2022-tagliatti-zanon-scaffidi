package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.ShowLobbiesSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class GUIShowLobbiesState extends AbstractClientState {
    private final Client client;
    private Parent root;

    public GUIShowLobbiesState(Client client){
        this.client = client;
    }

    @Override
    public void render() {
        URL url = getClass().getResource("/fxml/ShowLobbiesScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            ShowLobbiesSceneController showLobbiesSceneController = loader.getController();
            showLobbiesSceneController.setClient(client);
            showLobbiesSceneController.setup();
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void serverError(String message) {
        //TODO
    }
}
