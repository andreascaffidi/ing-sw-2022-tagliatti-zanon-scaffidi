package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.MoveMotherNatureSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.MoveStudentsSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GUIMoveMotherNatureState extends AbstractClientState {

    private final Client client;
    private Parent root;

    private int id;
    MoveMotherNatureSceneController moveMotherNatureSceneController;

    public GUIMoveMotherNatureState(Client client){
        this.client = client;
    }

    @Override
    public void render(){
        URL url = getClass().getResource("/fxml/MoveMotherNatureScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            moveMotherNatureSceneController = loader.getController();
            moveMotherNatureSceneController.setClient(client);
            moveMotherNatureSceneController.setup();
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void serverError(String message) {
        moveMotherNatureSceneController.alert(message);
    }
}
