package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.*;

import static java.lang.String.valueOf;

/**
 * move students scene controller (GUI)
 */
public class MoveStudentsSceneController extends AbstractSceneController {

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


    private int choices;

    private Map<Integer, String> movementsChosen;

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
        alert("Move students to an island or to your dining room (drag and drop)");
        choices = client.getReducedModel().getBoards().size() == 3 ? 4 : 3;
        movementsChosen = new HashMap<>();
        setDragAndDrop();
    }

    /**
     * sets the drag and drop events for students, islands and dining room
     */
    private void setDragAndDrop(){
        for (Node student : entranceGrid.getChildren()){
            student.setId(String.valueOf(entranceGrid.getChildren().indexOf(student)));

            student.setOnDragDetected(e ->{
                Dragboard db = student.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(valueOf(entranceGrid.getChildren().indexOf(student)));
                db.setContent(content);
                e.consume();
            });
        }

        Pane islandPane = (Pane) islands.getChildren().get(0);
        for (Node island : islandPane.getChildren()){

            island.setOnDragOver(e -> {
                if (e.getDragboard().hasString()) {
                    e.acceptTransferModes(TransferMode.ANY);
                }
                e.consume();
            });

            island.setOnDragDropped(e -> {
                String studentIndex = e.getDragboard().getString();
                Pane studentMoved = (Pane) entranceGrid.getChildren().get(Integer.parseInt(studentIndex));
                ((TilePane) island).getChildren().add(studentMoved);
                choices--;
                movementsChosen.put(Integer.parseInt(studentMoved.getId())+1, String.valueOf(islandPane.getChildren().indexOf(island) + 1));
                if (choices == 0){
                    client.send(new MoveStudentMessage(movementsChosen));
                }
                e.consume();
            });
        }

        diningGrid.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.ANY);
            }
            e.consume();
        });

        diningGrid.setOnDragDropped(e -> {
            String studentIndex = e.getDragboard().getString();
            ReducedBoard myBoard = client.getReducedModel().getBoard(client.getUsername());
            Pane studentMoved = (Pane) entranceGrid.getChildren().get(Integer.parseInt(studentIndex));
            ColorS studentColor = myBoard.getEntranceStudents().get(Integer.parseInt(studentMoved.getId()));

            List<ColorS> rows = new ArrayList<>(Arrays.asList(ColorS.GREEN, ColorS.RED, ColorS.YELLOW, ColorS.PINK, ColorS.BLUE));
            int rowIndex = rows.indexOf(studentColor);
            diningGrid.addRow(rowIndex, studentMoved);
            GridPane.setHalignment(studentMoved, HPos.CENTER);

            choices--;
            movementsChosen.put(Integer.parseInt(studentMoved.getId())+1, "DINING ROOM");
            if (choices == 0){
                client.send(new MoveStudentMessage(movementsChosen));
            }
            e.consume();
        });

    }

}