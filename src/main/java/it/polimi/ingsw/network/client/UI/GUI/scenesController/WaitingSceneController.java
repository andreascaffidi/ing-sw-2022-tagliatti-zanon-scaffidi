package it.polimi.ingsw.network.client.UI.GUI.scenesController;


import it.polimi.ingsw.network.client.UI.GUI.GUI;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * waiting scene controller (GUI)
 */
public class WaitingSceneController extends AbstractSceneController {

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
    private Pane alert;

    @FXML
    private TextFlow currentEffectBox;

    @FXML
    private Button playCharacter;

    @FXML
    private Button playAssistant;

    @FXML
    private Text alertMessage;

    @FXML
    private TilePane coins;

    @FXML
    private AnchorPane waitingLobby;

    @FXML
    private AnchorPane waitingGame;

    @FXML
    private Button character1;

    @FXML
    private Button character2;

    @FXML
    private Button character3;

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

    @FXML
    private TextFlow messageBox;

    @FXML
    private Text gameMessage;


    /**
     * shows an alert message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        ((GUI) client.getUI()).setAlertAnimation(alert);
    }

    /**
     * hides the Other Board Pane (dispatched by CLOSE button on Other Board Pane)
     */
    public void hideOtherBoard(){
        otherPlayerPane.setVisible(false);
        overlayView.setVisible(false);
        overlayView.setDisable(true);
        waitingGame.setDisable(false);
        waitingGame.setOpacity(1);
    }

    /**
     * sets up the model in the main components of the scene
     */
    @Override
    public void setup()
    {
        if (client.getReducedModel() != null) {
            waitingLobby.setVisible(false);
            waitingGame.setVisible(true);
            ((GUI) client.getUI()).showModel(client, islands, cloudsGrid, assistantPlayed, entranceGrid, diningGrid,
                    professorsGrid, towersGrid, playCharacter, coins, character1, character2, character3, currentEffectBox);

            ((GUI) client.getUI()).showOtherPlayers(waitingGame, otherBoards, overlayView, otherPlayerPane, otherEntranceGrid,
                    otherDiningGrid, otherProfessorsGrid, otherTowersGrid, otherAssistantPlayed, otherCoins, otherPlayerName);
            gameMessage.setText(client.getWaitingMessage());
            messageBox.setVisible(true);
            playCharacter.setVisible(false);
            playAssistant.setVisible(false);
        }
    }

    /**
     * shows the disconnection message and disable all the scene
     * @param message disconnection message
     */
    public void disconnectClient(String message){
        gameMessage.setText(message);
        waitingGame.setDisable(true);
        waitingGame.setOpacity(0.5);
        waitingLobby.setDisable(true);
        waitingLobby.setOpacity(0.5);
        overlayView.setDisable(true);
        overlayView.setOpacity(0.5);
    }

}
