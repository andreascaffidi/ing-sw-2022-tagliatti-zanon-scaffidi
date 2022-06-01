package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.ChooseCloudSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.MoveMotherNatureSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GUIChooseCloudState extends AbstractClientState {

    private final Client client;
    private Parent root;

    public GUIChooseCloudState(Client client){
        this.client = client;
    }

    @Override
    public void render(){
        URL url = getClass().getResource("/fxml/ChooseCloudScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            ChooseCloudSceneController chooseCloudSceneController = loader.getController();
            chooseCloudSceneController.setClient(client);
            chooseCloudSceneController.setup();
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
