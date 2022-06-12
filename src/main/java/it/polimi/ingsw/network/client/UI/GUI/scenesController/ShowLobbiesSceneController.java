package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;
import it.polimi.ingsw.network.requests.setupMessages.ChooseTeamMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
import it.polimi.ingsw.network.server.Lobby;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ShowLobbiesSceneController extends AbstractSceneController {


    @FXML
    public Button Enter;
    @FXML
    public RadioButton Team1;
    @FXML
    public RadioButton Team2;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private final List<String> lobbies = new ArrayList<>();

    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }


    @Override
    public void setup() {

        List<Lobby> availableLobbies = client.getAvailableLobbies();
        for(Lobby l: availableLobbies) {
            lobbies.add(l.getHost() + ": " + l.getGameMode() + " MODE " + l.getNumOfConnection() + "/" + l.getNumOfPlayers());
        }
        myChoiceBox.getItems().addAll(lobbies);
        Team1.setVisible(false);
        Team2.setVisible(false);

    }

    public void show()
    {
        String chosenLobby = myChoiceBox.getValue();
        List<Lobby> availableLobbies = client.getAvailableLobbies();
        String prova;

        for(Lobby l: availableLobbies) {
            prova = l.getHost() + ": " + l.getGameMode() + " MODE " + l.getNumOfConnection() + "/" + l.getNumOfPlayers();
            if(prova.equals(chosenLobby))
            {
                String definitiveLobby = l.getHost();
                if(l.getNumOfPlayers() == 4)
                {
                    Team1.setVisible(true);
                    Team2.setVisible(true);
                }
            }
            else{
                Team1.setVisible(false);
                Team2.setVisible(false);
            }
        }
    }

    public void enter()
    {
        String chosenLobby = myChoiceBox.getValue();

        List<Lobby> availableLobbies = client.getAvailableLobbies();

        String prova;
        for(Lobby l: availableLobbies) {
            prova = l.getHost() + ": " + l.getGameMode() + " MODE " + l.getNumOfConnection() + "/" + l.getNumOfPlayers();
            if(prova.equals(chosenLobby))
            {
                String definitiveLobby = l.getHost();
                if(l.getNumOfPlayers() == 4)
                {
                    if(Team1.isSelected())
                    {
                        client.send(new ChooseTeamMessage(1, l.getHost()));
                    }
                    if(Team2.isSelected())
                    {
                        client.send(new ChooseTeamMessage(2, l.getHost()));
                    }
                }
                else {
                    client.send(new SetupRequestMessage(SetupResponsesTypes.JOIN_LOBBY, definitiveLobby));
                }
            }
        }
    }
}
