package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateLobbySceneController extends AbstractSceneController {

    @FXML
    private Button expert;

    @FXML
    private Button normal;

    @FXML
    private Button two;

    @FXML
    private Button tree;

    @FXML
    private Button four;

    @FXML
    private Button enter;

    private String mode;
    private int num;

    public void enter(ActionEvent event){
        client.send(new CreateLobbyMessage(client.getUsername(), this.mode.toUpperCase(), this.num));
    }

    public void expert(ActionEvent event)
    {
        this.mode = "expert";
    }

    public void normal(ActionEvent event)
    {
        this.mode = "normal";
    }

    public void two(ActionEvent event)
    {
        this.num = 2;
    }

    public void tree(ActionEvent event)
    {
        this.num = 3;
    }

    public void four(ActionEvent event)
    {
        this.num = 4;
    }

}
