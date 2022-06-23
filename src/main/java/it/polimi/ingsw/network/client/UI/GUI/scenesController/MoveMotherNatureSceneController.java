package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.reducedModel.ReducedIsland;
import it.polimi.ingsw.network.requests.gameMessages.MoveMotherNatureMessage;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import static java.lang.String.valueOf;

/**
 * move mother nature scene controller (GUI)
 */
public class MoveMotherNatureSceneController extends AbstractSceneController {

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

    @FXML
    private TextFlow messageBox;

    @FXML
    private Text gameMessage;

    private int maxMovement;

    /**
     * shows an alert message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        ((GUI) client.getUI()).setAlertAnimation(alert);
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
        gameMessage.setText(message);
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
        ReducedBoard myBoard = client.getReducedModel().getBoard(client.getUsername());
        if (myBoard != null) {
            maxMovement = myBoard.getAssistantDeck().getPlayedAssistant().getMotherNatureMovements();
        }
        gameMessage.setText("Move mother nature, maximum: " + maxMovement + " clockwise movements");
        messageBox.setVisible(true);
        setDragAndDrop();
    }

    /**
     * sets the drag and drop events for mother nature and islands
     */
    private void setDragAndDrop(){
        Pane islandPane = (Pane) islands.getChildren().get(0);
        for (ReducedIsland island : client.getReducedModel().getIslands()){
            if (island.isMotherNature()){
                Pane motherNatureIsland = (Pane) islandPane.getChildren().get(island.getId());
                Pane motherNature = (Pane) motherNatureIsland.getChildren().stream().filter(p -> p.getId() != null &&
                        p.getId().equals("motherNature")).findFirst().orElse(null);
                if (motherNature != null){
                    motherNature.setOnDragDetected(e -> {
                        Dragboard db = motherNature.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(valueOf(islandPane.getChildren().indexOf(motherNatureIsland)));
                        db.setContent(content);
                        e.consume();
                    });
                }
            }
        }

        for (Node island : islandPane.getChildren()){

            island.setOnDragOver(e -> {
                if (e.getDragboard().hasString()) {
                    e.acceptTransferModes(TransferMode.ANY);
                }
                e.consume();
            });

            island.setOnDragDropped(e -> {
                int motherNatureIslandIndex = Integer.parseInt(e.getDragboard().getString());
                int movements = (islandPane.getChildren().indexOf(island) - motherNatureIslandIndex);
                if (movements < 0){
                    movements = movements + client.getReducedModel().getIslands().size();
                }
                if (movements <= maxMovement && movements > 0) {
                    Pane motherNatureIsland = (Pane) islandPane.getChildren().get(motherNatureIslandIndex);
                    Pane motherNature = (Pane) motherNatureIsland.getChildren().stream().filter(p -> p.getId() != null &&
                            p.getId().equals("motherNature")).findFirst().orElse(null);
                    ((TilePane) island).getChildren().add(motherNature);
                    client.send(new MoveMotherNatureMessage(movements));
                    e.consume();
                } else {
                    alert("Invalid movement, maximum movements: " + maxMovement + " (clockwise)");
                }
            });
        }
    }
}