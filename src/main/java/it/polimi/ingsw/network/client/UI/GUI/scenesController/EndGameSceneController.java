package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * end game scene controller (GUI)
 */
public class EndGameSceneController extends AbstractSceneController {

    @FXML
    private Text endGameText;

    /**
     * sets up the main components of the scene
     */
    @Override
    public void setup()
    {
        ReducedModel reducedModel = client.getReducedModel();
        if (reducedModel.getBoards().size() == 4){
            int winningTeam = reducedModel.getBoard(client.getWinner()).getTagTeam();
            if (reducedModel.getBoard(client.getUsername()).getTagTeam() == winningTeam){
                endGameText.setText("Congrats! =) Team " + winningTeam + " is the winner. Wow you are so intelligent! :)");
            } else {
                endGameText.setText("The winner is... not you! It's team " + winningTeam);
            }
        } else {
            if(client.getUsername().equals(client.getWinner())){
                endGameText.setText("Congrats! =) You are the winner. Wow you are so intelligent! :)");
            }else{
                endGameText.setText("The winner is... not you! It's " + client.getWinner());
            }
        }
    }
}
