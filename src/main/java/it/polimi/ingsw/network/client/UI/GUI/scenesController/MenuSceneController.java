package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuSceneController extends AbstractController{

    @FXML
    Label nameLabel;
    public void displayName(String username)
    {
        nameLabel.setText("Hello: " + username);
    }
}
