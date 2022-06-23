package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


/**
 * menu scene controller (GUI)
 */
public class MenuSceneController extends AbstractSceneController {

    @FXML
    private Text alertMessage;
    @FXML
    private TextFlow alert;

    /**
     * shows an alert with a message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }

    /**
     * sends a message to create a lobby
     */
    public void create(){
        client.send(new SetupRequestMessage(SetupRequestTypes.COMMAND, "CREATE"));
    }

    /**
     * sends a message to join a lobby
     */
    public void join(){
        client.send(new SetupRequestMessage(SetupRequestTypes.COMMAND, "JOIN"));
    }
}
