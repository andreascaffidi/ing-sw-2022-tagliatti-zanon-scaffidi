package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

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

    public void login(ActionEvent event){
        String username = NickNameTextField.getText();
        client.setUsername(username);
        client.send(new SetupRequestMessage(SetupResponsesTypes.USERNAME, username));
    }

}
