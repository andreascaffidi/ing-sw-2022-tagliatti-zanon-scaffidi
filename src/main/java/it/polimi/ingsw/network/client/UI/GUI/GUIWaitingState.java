package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WaitingSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class GUIWaitingState extends AbstractClientState {
    private final Client client;
    private Parent root;

    WaitingSceneController waitingSceneController;

    public GUIWaitingState(Client client){
        this.client = client;
    }

    @Override
    public void render(){
        URL url = getClass().getResource("/fxml/WaitingScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            waitingSceneController = loader.getController();
            waitingSceneController.setClient(client);
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void serverError(String message) {
        waitingSceneController.alert(message);
    }
}
