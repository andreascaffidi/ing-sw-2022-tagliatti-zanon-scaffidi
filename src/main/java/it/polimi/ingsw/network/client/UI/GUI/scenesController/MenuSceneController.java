package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuSceneController extends AbstractController{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label nicknameLabel;
    public void displayName()
    {
        nicknameLabel.setText("Hello");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
