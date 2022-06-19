package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

/**
 * play assistant scene controller (GUI)
 */
public class PlayAssistantSceneController extends AbstractSceneController{

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
    private ScrollPane assistantsPane;

    @FXML
    private AnchorPane scrollPaneView;

    @FXML
    private GridPane assistantsGrid;

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
     * sets up the model in the main components of the scene
     */
    @Override
    public void setup(){
        ((GUI) client.getUI()).showModel(client, islands, cloudsGrid, assistantPlayed, entranceGrid, diningGrid,
                professorsGrid, towersGrid, playCharacter, coins, character1, character2, character3, currentEffectBox);

        ((GUI) client.getUI()).showOtherPlayers(modelView, otherBoards, overlayView, otherPlayerPane, otherEntranceGrid,
                otherDiningGrid, otherProfessorsGrid, otherTowersGrid, otherAssistantPlayed, otherCoins, otherPlayerName);
        alert("Play an assistant card");
        setupAssistants();
    }

    /**
     * sets up the assistant cards, putting them in the components and creating buttons
     */
    private void setupAssistants(){
        ReducedBoard myBoard = client.getReducedModel().getBoard(client.getUsername());

        List<Integer> possibleChoices = null;
        if(myBoard != null){
            possibleChoices = myBoard.getAssistantDeck().getPossibleChoices();
        }

        int row = 0;
        int column = 0;
        if (possibleChoices != null) {
            for (int assistant : possibleChoices) {
                if (column >= 4) {
                    column = 0;
                    row++;
                    scrollPaneView.setMaxHeight(scrollPaneView.getMaxHeight() + 300);
                }
                Button assistantButton = new Button();
                assistantButton.getStyleClass().add("assistant-" + assistant);
                assistantButton.setPrefWidth(250);
                assistantButton.setPrefHeight(360);
                assistantButton.setOnAction(e -> client.send(new PlayAssistantMessage(assistant)));
                assistantsGrid.add(assistantButton, column, row);
                GridPane.setMargin(assistantButton, new Insets(20, 10, 20, 10));
                column++;
            }
        }
    }

    /**
     * shows the AssistantsPane (dispatched by PlayAssistant button)
     */
    public void showAssistants(){
        modelView.setDisable(true);
        modelView.setOpacity(0.5);
        assistantsPane.setVisible(true);
        overlayView.setVisible(true);
        overlayView.setDisable(false);
    }

    /**
     * hides the AssistantsPane (dispatched by X button on AssistantsPane)
     */
    public void hideAssistants(){
        assistantsPane.setVisible(false);
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
     * shows an alert message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        alertBox.setVisible(true);
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
}
