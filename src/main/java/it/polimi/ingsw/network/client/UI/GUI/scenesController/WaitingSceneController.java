package it.polimi.ingsw.network.client.UI.GUI.scenesController;


import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * waiting scene controller (GUI)
 */
public class WaitingSceneController extends AbstractSceneController {

    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    /**
     * shows an alert with a message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }

}
