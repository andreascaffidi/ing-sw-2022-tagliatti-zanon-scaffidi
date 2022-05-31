package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.MoveStudentsSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.PlayAssistantSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GUIMoveStudentsState extends AbstractClientState {
    private final Client client;
    private Parent root;

    public GUIMoveStudentsState(Client client){
        this.client = client;
    }

    //TODO: adattare al reduced model
    @Override
    public void render(){
        URL url = getClass().getResource("/fxml/MoveStudentsScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            MoveStudentsSceneController moveStudentsSceneController = loader.getController();
            moveStudentsSceneController.setClient(client);
            moveStudentsSceneController.setup();
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
