package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.server.Lobby;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShowLobbiesSceneController extends AbstractSceneController {


    @FXML
    public Button Enter;

    @FXML
    private Label myLabel;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private final List<String> lobbies = new ArrayList<>();

    @Override
    public void setup() {
        List<Lobby> availableLobbies = client.getAvailableLobbies();
        for(Lobby l: availableLobbies) {
            lobbies.add(l.getHost() + ": " + l.getGameMode() + " MODE " + l.getNumOfConnection() + "/" + l.getNumOfPlayers());
        }
        myChoiceBox.getItems().addAll(lobbies);

    }

    public void enter(ActionEvent event)
    {
        //TODO: da qua iniziano le cose complicate
    }

}
