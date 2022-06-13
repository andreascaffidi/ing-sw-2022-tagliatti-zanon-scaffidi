package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * end game scene controller (GUI)
 */
public class EndGameSceneController extends AbstractSceneController {

    @FXML
    public Text endGameText;
    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }

    @Override
    public void setup()
    {
        if(client.getUsername().equals(client.getWinner()))
        {
            endGameText.setText("Congrats! =) You are the winner. Wow you are so intelligent! :)");
        }else{
            endGameText.setText("The winner is... not you! It's " + client.getWinner());
        }
        //TODO: mettere bottone per tornare al menu
    }
}
