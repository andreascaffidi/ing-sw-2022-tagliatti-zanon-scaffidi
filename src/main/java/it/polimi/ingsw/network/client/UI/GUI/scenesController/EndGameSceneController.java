package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * end game scene controller (GUI)
 */
public class EndGameSceneController extends AbstractSceneController {

    @FXML
    private Text endGameText;

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
