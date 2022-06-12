package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class MenuSceneController extends AbstractSceneController {

    @FXML
    public Button joinButton;
    @FXML
    public Button createButton;

    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }

    public void create(ActionEvent event) throws IOException {
        client.send(new SetupRequestMessage(SetupRequestTypes.COMMAND, "CREATE"));
    }

    public void join(ActionEvent event) throws IOException
    {
        client.send(new SetupRequestMessage(SetupRequestTypes.COMMAND, "JOIN"));
    }
}
