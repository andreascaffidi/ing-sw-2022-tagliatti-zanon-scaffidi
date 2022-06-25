package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;
import javafx.fxml.FXML;
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
        client.send(new CreateLobbyMessage(client.getUsername(), this.mode.toUpperCase(), this.num));
    }

    /**
     * sets expert mode
     */
    public void expert()
    {
        this.mode = "expert";
    }

    /**
     * sets normal mode
     */
    public void normal()
    {
        this.mode = "normal";
    }

    /**
     * sets two players match
     */
    public void two()
    {
        this.num = 2;
    }

    /**
     * sets three players match
     */
    public void tree()
    {
        this.num = 3;
    }

    /**
     * sets four players match
     */
    public void four()
    {
        this.num = 4;
    }

}
