package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
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
        String chosenLobby = myChoiceBox.getValue();
        String definitiveLobby = null;

        List<Lobby> availableLobbies = client.getAvailableLobbies();

        String prova;
        for(Lobby l: availableLobbies) {
            prova = l.getHost() + ": " + l.getGameMode() + " MODE " + l.getNumOfConnection() + "/" + l.getNumOfPlayers();
            if(prova.equals(chosenLobby))
            {
                definitiveLobby = l.getHost();
            }
        }
        client.send(new SetupRequestMessage(SetupResponsesTypes.JOIN_LOBBY, definitiveLobby));
    }

}
