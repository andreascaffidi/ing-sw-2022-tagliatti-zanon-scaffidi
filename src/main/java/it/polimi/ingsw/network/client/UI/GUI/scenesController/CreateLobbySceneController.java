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

    private String mode = "normal";
    private int num = 2;

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
        this.num = Integer.parseInt(players.getSelectedToggle().getUserData().toString());
        this.mode = gameMode.getSelectedToggle().getUserData().toString();
        System.out.print(this.num+" "+ this.mode);
        client.send(new CreateLobbyMessage(client.getUsername(), this.mode.toUpperCase(), this.num));
    }
}
