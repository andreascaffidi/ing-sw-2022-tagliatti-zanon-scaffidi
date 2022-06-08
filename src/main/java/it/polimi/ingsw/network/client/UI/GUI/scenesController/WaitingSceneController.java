package it.polimi.ingsw.network.client.UI.GUI.scenesController;


import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class WaitingSceneController extends AbstractSceneController {
    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }

}
