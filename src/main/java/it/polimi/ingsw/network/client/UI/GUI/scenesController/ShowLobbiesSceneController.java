package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.ChooseTeamMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import it.polimi.ingsw.network.server.Lobby;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * show lobbies scene controller (GUI)
 */
public class ShowLobbiesSceneController extends AbstractSceneController {


    @FXML
    public RadioButton Team1;
    @FXML
    public RadioButton Team2;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private List<Lobby> availableLobbies;

    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    /**
     * shows an alert with a message
     *
     * @param message message
     */
    public void alert(String message) {
        alertMessage.setText(message);
        alert.setVisible(true);
    }

    /**
     * prints lobby information in String format
     * @param lobby lobby
     * @return string with lobby information
     */
    private String lobbyToString(Lobby lobby) {
        return lobby.getHost() + ": " + lobby.getGameMode() + " MODE " + lobby.getNumOfConnection() +
                "/" + lobby.getNumOfPlayers();
    }

    /**
     * sets up the controller adding the available lobbies to the scene
     */
    @Override
    public void setup() {
        availableLobbies = client.getAvailableLobbies();
        List<String> lobbies = new ArrayList<>();
        for (Lobby l : availableLobbies) {
            lobbies.add(lobbyToString(l));
        }
        myChoiceBox.getItems().addAll(lobbies);
        Team1.setVisible(false);
        Team2.setVisible(false);
    }

    /**
     * shows teams to select, if necessary
     */
    public void selectTeam() {
        String chosenLobby = myChoiceBox.getValue();
        for (Lobby lobby : availableLobbies) {
            if (lobbyToString(lobby).equals(chosenLobby)) {
                if (lobby.getNumOfPlayers() == 4) {
                    Team1.setVisible(true);
                    Team2.setVisible(true);
                }
            }
        }
    }

    /**
     * selects a lobby from the available
     */
    public void selectLobby() {
        String chosenLobby = myChoiceBox.getValue();
        for (Lobby lobby : availableLobbies) {
            if (lobbyToString(lobby).equals(chosenLobby)) {
                if (lobby.getNumOfPlayers() == 4) {
                    if (Team1.isSelected()) {
                        client.send(new ChooseTeamMessage(1, lobby.getHost()));
                    } else if (Team2.isSelected()) {
                        client.send(new ChooseTeamMessage(2, lobby.getHost()));
                    }
                } else {
                    client.send(new SetupRequestMessage(SetupRequestTypes.JOIN_LOBBY, lobby.getHost()));
                }
            }
        }
    }

}

