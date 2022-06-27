package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * create lobby scene controller (GUI)
 */
public class CreateLobbySceneController extends AbstractSceneController {

    @FXML
    private Text alertMessage;
    @FXML
    private TextFlow alert;

    @FXML
    private ToggleGroup players;

    @FXML
    private ToggleGroup gameMode;

    /**
     * shows an alert message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }

    /**
     * sends the lobby setting (dispatched by clicking enter button)
     */
    public void enter(){
        int num = Integer.parseInt(players.getSelectedToggle().getUserData().toString());
        String mode = gameMode.getSelectedToggle().getUserData().toString();
        client.send(new CreateLobbyMessage(client.getUsername(), mode.toUpperCase(), num));
    }
}
