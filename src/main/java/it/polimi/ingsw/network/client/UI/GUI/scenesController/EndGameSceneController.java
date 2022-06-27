package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * end game scene controller (GUI)
 */
public class EndGameSceneController extends AbstractSceneController {

    @FXML
    private Text endGameText;

    @FXML
    private Pane endImage;

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
                endGameText.setText("Congratulations, your team won this game!");
                endImage.getStyleClass().add("winner");
            } else {
                endGameText.setText("And the winner is... not you! It's team " + winningTeam);
                endImage.getStyleClass().add("loser");
            }
        } else {
            if(client.getUsername().equals(client.getWinner())){
                endGameText.setText("Congratulations, you won this game!");
                endImage.getStyleClass().add("winner");
            }else{
                endGameText.setText("And the winner is... not you! It's " + client.getWinner());
                endImage.getStyleClass().add("loser");
            }
        }
    }
}
