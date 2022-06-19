package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * choose cloud scene controller (GUI)
 */
public class ChooseCloudSceneController extends AbstractSceneController {

    @FXML
    private Group islands;

    @FXML
    private GridPane entranceGrid;

    @FXML
    private GridPane diningGrid;

    @FXML
    private GridPane professorsGrid;

    @FXML
    private GridPane towersGrid;

    @FXML
    private Pane assistantPlayed;

    @FXML
    private GridPane cloudsGrid;

    @FXML
    private GridPane otherBoards;

    @FXML
    private TextFlow alertBox;

    @FXML
    private TextFlow currentEffectBox;

    @FXML
    private Button playCharacter;

    @FXML
    private Text alertMessage;

    @FXML
    private TilePane coins;

    @FXML
    private Pane charactersPane;

    @FXML
    private Button character1;

    @FXML
    private Button character2;

    @FXML
    private Button character3;

    @FXML
    private AnchorPane modelView;

    @FXML
    private AnchorPane overlayView;

    @FXML
    private Group otherPlayerPane;

    @FXML
    private GridPane otherEntranceGrid;

    @FXML
    private GridPane otherDiningGrid;

    @FXML
    private GridPane otherProfessorsGrid;

    @FXML
    private GridPane otherTowersGrid;

    @FXML
    private Pane otherAssistantPlayed;

    @FXML
    private TilePane otherCoins;

    @FXML
    private Text otherPlayerName;


    /**
     * shows an alert message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        alertBox.setVisible(true);
    }

    /**
     * shows the CharactersPane (dispatched by PlayCharacter button)
     */
    public void showCharacters(){
        modelView.setDisable(true);
        modelView.setOpacity(0.5);
        charactersPane.setVisible(true);
        overlayView.setVisible(true);
        overlayView.setDisable(false);
    }

    /**
     * hides the CharactersPane (dispatched by X button on CharactersPane)
     */
    public void hideCharacters(){
        charactersPane.setVisible(false);
        overlayView.setVisible(false);
        overlayView.setDisable(true);
        modelView.setDisable(false);
        modelView.setOpacity(1);
    }

    /**
     * hides the Other Board Pane (dispatched by CLOSE button on Other Board Pane)
     */
    public void hideOtherBoard(){
        otherPlayerPane.setVisible(false);
        overlayView.setVisible(false);
        overlayView.setDisable(true);
        modelView.setDisable(false);
        modelView.setOpacity(1);
    }

    /**
     * shows the disconnection message and disable all the scene
     * @param message disconnection message
     */
    public void disconnectClient(String message){
        alert(message);
        modelView.setDisable(true);
        modelView.setOpacity(0.5);
        overlayView.setDisable(true);
        overlayView.setOpacity(0.5);
    }

    /**
     * sets up the model in the main components of the scene
     */
    @Override
    public void setup()
    {
        ((GUI) client.getUI()).showModel(client, islands, cloudsGrid, assistantPlayed, entranceGrid, diningGrid,
                professorsGrid, towersGrid, playCharacter, coins, character1, character2, character3, currentEffectBox);

        ((GUI) client.getUI()).showOtherPlayers(modelView, otherBoards, overlayView, otherPlayerPane, otherEntranceGrid,
                otherDiningGrid, otherProfessorsGrid, otherTowersGrid, otherAssistantPlayed, otherCoins, otherPlayerName);
        alert("Choose a cloud, clicking on it");
        setClick();
    }

    /**
     * sets the click event on cloud
     */
    private void setClick(){
        for (Node cloud : cloudsGrid.getChildren()){
            cloud.setOnMouseClicked(e -> client.send(new ChooseCloudMessage(cloudsGrid.getChildren().indexOf(cloud) + 1)));
        }
    }

}
