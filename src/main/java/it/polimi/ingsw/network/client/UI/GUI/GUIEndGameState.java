package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.EndGameSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WaitingSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GUIEndGameState extends AbstractClientState {

    private final Client client;
    private Parent root;

    public GUIEndGameState(Client client){
        this.client = client;
    }

    @Override
    public void render(){
        URL url = getClass().getResource("/fxml/EndGameScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            EndGameSceneController endGameSceneController = loader.getController();
            endGameSceneController.setClient(client);
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
