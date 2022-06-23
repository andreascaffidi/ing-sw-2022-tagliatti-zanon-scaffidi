package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * welcome scene controller (GUI)
 */
public class WelcomeSceneController extends AbstractSceneController {

    @FXML
    private TextField NickNameTextField;
    @FXML
    private Text alertMessage;
    @FXML
    private Pane alert;

    /**
     * sends the username to the server, event dispatched when user presses login button
     */
    public void login(){
        String username = NickNameTextField.getText();
        if (username.contains(" ") || username.length() == 0){
            alert("Username not permitted (avoid white spaces)");
        } else {
            client.setUsername(username);
            client.send(new SetupRequestMessage(SetupRequestTypes.USERNAME, username));
        }
    }

    /**
     * shows an alert with a message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        ((GUI) client.getUI()).setAlertAnimation(alert);
    }
}
