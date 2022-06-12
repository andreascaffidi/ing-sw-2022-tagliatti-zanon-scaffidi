package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * welcome scene controller
 */
public class WelcomeSceneController extends AbstractSceneController {

    /**
     * scene actors
     */
    @FXML
    public Button loginButton;
    @FXML
    public TextField NickNameTextField;
    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    public void login(){
        String username = NickNameTextField.getText();
        client.setUsername(username);
        client.send(new SetupRequestMessage(SetupRequestTypes.USERNAME, username));
    }

    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }
}
