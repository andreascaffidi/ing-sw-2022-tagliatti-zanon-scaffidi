package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.network.client.UI.CLI.CLI;
import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.reducedModel.*;
import it.polimi.ingsw.network.requests.gameMessages.*;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class PlayCharacterSceneController extends AbstractSceneController {

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
    private Pane inputPane;

    @FXML
    private Text requestInput;

    @FXML
    private GridPane colorsInput;

    @FXML
    private Pane redStudent;

    @FXML
    private Pane blueStudent;

    @FXML
    private Pane greenStudent;

    @FXML
    private Pane yellowStudent;

    @FXML
    private Pane pinkStudent;

    @FXML
    private RadioButton movements1;

    @FXML
    private RadioButton movements2;

    @FXML
    private Button selectMovements;

    @FXML
    private TilePane cardWithStudents;

    @FXML
    private Button exit;

    private final List<Integer> cardStudents = new ArrayList<>();
    private final List<Integer> entranceStudents = new ArrayList<>();
    private final List<ColorS> diningStudents = new ArrayList<>();
    private int choices;
    private int numOfDiningStudents;

    /**
     * shows an alert message
     * @param message message
     */
    public void alert(String message){
        alertMessage.setText(message);
        alertBox.setVisible(true);
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
     * hides the Input Pane (dispatched by X button on Input Pane)
     */
    public void hideInputPane(){
        inputPane.setVisible(false);
        overlayView.setVisible(false);
        overlayView.setDisable(true);
        modelView.setDisable(false);
        modelView.setOpacity(1);
    }

    /**
     * shows the Input Pane (dispatched by Select Input button)
     */
    public void showInput(){
        modelView.setDisable(true);
        modelView.setOpacity(0.5);
        inputPane.setVisible(true);
        overlayView.setVisible(true);
        overlayView.setDisable(false);
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
     * quits the play character scene
     */
    public void exit(){
        client.changeState(client.getBackState());
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

        playCharacter.setVisible(false);
        play(client.getCharacterSelected());
    }

    /**
     * dispatches character effect
     * @param id character id
     */
    private void play(int id)
    {
        switch (id){
            case 1 : character1();
            break;
            case 2 : character2();
            break;
            case 3 : character3();
            break;
            case 4 : character4();
            break;
            case 5 : character5();
            break;
            case 6 : character6();
            break;
            case 7 : character7();
            break;
            case 8 : character8();
            break;
            case 9 : character9();
            break;
            case 10 : character10();
            break;
            case 11 : character11();
            break;
            case 12 : character12();
            break;
        }
    }

    /**
     * requests the input for character 1
     */
    public void character1()
    {
        alert("Move a student from the card to an island, drag and drop");
        cardWithStudents.getStyleClass().add("character-" + client.getCharacterSelected());
        for (ColorS student : ((ReducedModelExpertMode) client.getReducedModel()).getCharacterById(client.getCharacterSelected()).getStudents()){
            Pane studentPane = new Pane();
            studentPane.getStyleClass().add(student.toString().toLowerCase());
            studentPane.setPrefHeight(50);
            studentPane.setPrefWidth(50);
            studentPane.setOnDragDetected(e -> {
                Dragboard db = studentPane.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(valueOf(cardWithStudents.getChildren().indexOf(studentPane)));
                db.setContent(content);
                e.consume();
            });
            cardWithStudents.getChildren().add(studentPane);
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
                client.send(new PayCharacter1Message(islandPane.getChildren().indexOf(island) + 1,
                        Integer.parseInt(studentIndex) + 1));
                e.consume();
            });
        }
    }

    /**
     * requests the input for character 2 (this character doesn't need any input)
     */
    private void character2()
    {
        client.send(new PayCharacter2Message());
    }

    /**
     * requests the input for character 3
     */
    private void character3()
    {
        alert("Click on an island on which calculate supremacy");
        Pane islandPane = (Pane) islands.getChildren().get(0);
        for (Node island : islandPane.getChildren()) {
            island.setOnMouseClicked(e ->
                    client.send(new PayCharacter3Message(islandPane.getChildren().indexOf(island) + 1)));
        }
    }

    /**
     * requests the input for character 4
     */
    private void character4()
    {
        alert("Insert a number of additional movement (maximum 2)");
        requestInput.setText("Choose an additional movement");
        movements1.setVisible(true);
        movements2.setVisible(true);
        selectMovements.setVisible(true);
        selectMovements.setOnAction(e -> {
            if (movements1.isSelected()){
                client.send(new PayCharacter4Message(1));
            } else if (movements2.isSelected()){
                client.send(new PayCharacter4Message(2));
            }
        });
        playCharacter.setVisible(true);
        showInput();
    }

    /**
     * requests the input for character 5
     */
    private void character5()
    {
        alert("Click on an island to place a No-Entry Tile");
        Pane islandPane = (Pane) islands.getChildren().get(0);
        for (Node island : islandPane.getChildren()) {
            island.setOnMouseClicked(e ->
                    client.send(new PayCharacter5Message(islandPane.getChildren().indexOf(island) + 1)));
        }
    }

    /**
     * requests the input for character 6
     */
    private void character6()
    {
        alert("Click on an island on which towers don't count for supremacy calculation");
        Pane islandPane = (Pane) islands.getChildren().get(0);
        for (Node island : islandPane.getChildren()) {
            island.setOnMouseClicked(e ->
                    client.send(new PayCharacter6Message(islandPane.getChildren().indexOf(island) + 1)));
        }
    }

    /**
     * requests the input for character 7
     */
    private void character7()
    {
        playCharacter.setText("Confirm");
        playCharacter.setOnAction(e -> client.send(new PayCharacter7Message(cardStudents, entranceStudents)));
        playCharacter.setVisible(true);
        playCharacter.setDisable(true);
        alert("Switch students from card and entrance (max 3), click on them");
        choices = 3;
        cardWithStudents.getStyleClass().add("character-" + client.getCharacterSelected());
        //set students on card
        for (ColorS student : ((ReducedModelExpertMode) client.getReducedModel()).getCharacterById(client.getCharacterSelected()).getStudents()){
            Pane studentPane = new Pane();
            studentPane.getStyleClass().add(student.toString().toLowerCase());
            studentPane.setPrefHeight(50);
            studentPane.setPrefWidth(50);
            //for each student on card set onClickEvent
            studentPane.setOnMouseClicked(e -> {

                //add the student selected to the list and disable the click event
                cardStudents.add(cardWithStudents.getChildren().indexOf(studentPane) + 1);
                studentPane.setDisable(true);
                playCharacter.setDisable(true);

                //disable all students different form the clicked one
                for (Node otherStudent : cardWithStudents.getChildren()){
                    if (otherStudent != studentPane){
                        otherStudent.setOpacity(0.5);
                        otherStudent.setDisable(true);
                    }
                }

                //if the student to switch has been selected
                if (cardStudents.size() == entranceStudents.size()){
                    //hide the two selected students
                    studentPane.setVisible(false);
                    Node switchedStudent = entranceGrid.getChildren().get(entranceStudents.get(entranceStudents.size()-1) -1);
                    switchedStudent.setVisible(false);

                    //enable all the students disabled
                    for (Node otherStudent : cardWithStudents.getChildren()){
                        if (otherStudent != studentPane){
                            otherStudent.setOpacity(1);
                            otherStudent.setDisable(false);
                        }
                    }
                    for (Node otherStudent : entranceGrid.getChildren()){
                        if (otherStudent != switchedStudent){
                            otherStudent.setOpacity(1);
                            otherStudent.setDisable(false);
                        }
                    }
                    playCharacter.setDisable(false);

                    //manage the other choices
                    choices--;
                    if (choices == 0){
                        client.send(new PayCharacter7Message(cardStudents, entranceStudents));
                    }
                }
            });

            cardWithStudents.getChildren().add(studentPane);
        }


        for (Node student : entranceGrid.getChildren()){
            //for each student on card set onClickEvent
            student.setOnMouseClicked(e -> {

                //add the student selected to the list and disable the click event
                entranceStudents.add(entranceGrid.getChildren().indexOf(student) + 1);
                student.setDisable(true);
                playCharacter.setDisable(true);

                //disable all students different form the clicked one
                for (Node otherStudent : entranceGrid.getChildren()){
                    if (otherStudent != student){
                        otherStudent.setOpacity(0.5);
                        otherStudent.setDisable(true);
                    }
                }

                //if the student to switch has been selected
                if (cardStudents.size() == entranceStudents.size()){
                    //hide the two selected students
                    student.setVisible(false);
                    Node switchedStudent = cardWithStudents.getChildren().get(cardStudents.get(cardStudents.size()-1) -1);
                    switchedStudent.setVisible(false);

                    //enable all the students disabled
                    for (Node otherStudent : entranceGrid.getChildren()){
                        if (otherStudent != student){
                            otherStudent.setOpacity(1);
                            otherStudent.setDisable(false);
                        }
                    }
                    for (Node otherStudent : cardWithStudents.getChildren()){
                        if (otherStudent != switchedStudent){
                            otherStudent.setOpacity(1);
                            otherStudent.setDisable(false);
                        }
                    }
                    playCharacter.setDisable(false);

                    //manage the other choices
                    choices--;
                    if (choices == 0){
                        client.send(new PayCharacter7Message(cardStudents, entranceStudents));
                    }
                }
            });

        }
    }

    /**
     * requests the input for character 8
     */
    private void character8()
    {
        client.send(new PayCharacter8Message());
    }

    /**
     * requests the input for character 9
     */
    private void character9()
    {
        alert("Choose a color that has no influence in this round");
        requestInput.setText("Choose a color, clicking on it");
        colorsInput.setVisible(true);
        redStudent.setOnMouseClicked(e -> client.send(new PayCharacter9Message(ColorS.RED)));
        greenStudent.setOnMouseClicked(e -> client.send(new PayCharacter9Message(ColorS.GREEN)));
        blueStudent.setOnMouseClicked(e -> client.send(new PayCharacter9Message(ColorS.BLUE)));
        yellowStudent.setOnMouseClicked(e -> client.send(new PayCharacter9Message(ColorS.YELLOW)));
        pinkStudent.setOnMouseClicked(e -> client.send(new PayCharacter9Message(ColorS.PINK)));
        playCharacter.setVisible(true);
        showInput();
    }

    /**
     * requests the input for character 10
     */
    private void character10() {
        playCharacter.setText("Confirm");
        playCharacter.setOnAction(e -> client.send(new PayCharacter10Message(diningStudents, entranceStudents)));
        playCharacter.setVisible(true);
        playCharacter.setDisable(true);
        alert("Switch students from dining and entrance (max 2), click on them");
        choices = 2;
        List<ColorS> rows = new ArrayList<>(Arrays.asList(ColorS.GREEN, ColorS.RED, ColorS.YELLOW, ColorS.PINK, ColorS.BLUE));
        numOfDiningStudents = diningGrid.getChildren().size();
        if (numOfDiningStudents == 0) {
            client.send(new PayCharacter10Message(null, null));
            modelView.setDisable(true);
            modelView.setOpacity(0.5);
            exit.setVisible(true);
            overlayView.setVisible(true);
            overlayView.setDisable(false);
        } else {

            for (Node student : diningGrid.getChildren()) {
                //for each student on card set onClickEvent
                student.setOnMouseClicked(e -> {

                    //add the student selected to the list and disable the click event
                    diningStudents.add(rows.get(GridPane.getRowIndex(student)));
                    student.setDisable(true);
                    playCharacter.setDisable(true);

                    //disable all students different form the clicked one
                    for (Node otherStudent : diningGrid.getChildren()) {
                        if (otherStudent != student) {
                            otherStudent.setOpacity(0.5);
                            otherStudent.setDisable(true);
                        }
                    }

                    //if the student to switch has been selected
                    if (diningStudents.size() == entranceStudents.size()) {
                        //hide the two selected students
                        student.setVisible(false);
                        Node switchedStudent = entranceGrid.getChildren().get(entranceStudents.get(entranceStudents.size() - 1) - 1);
                        switchedStudent.setVisible(false);

                        //enable all the students disabled
                        for (Node otherStudent : diningGrid.getChildren()) {
                            if (otherStudent != student) {
                                otherStudent.setOpacity(1);
                                otherStudent.setDisable(false);
                            }
                        }
                        for (Node otherStudent : entranceGrid.getChildren()) {
                            if (otherStudent != switchedStudent) {
                                otherStudent.setOpacity(1);
                                otherStudent.setDisable(false);
                            }
                        }
                        playCharacter.setDisable(false);

                        //manage the other choices
                        choices--;
                        if (choices == 0) {
                            client.send(new PayCharacter10Message(diningStudents, entranceStudents));
                        }
                    }
                });
            }


            for (Node student : entranceGrid.getChildren()) {
                //for each student on card set onClickEvent
                student.setOnMouseClicked(e -> {

                    //add the student selected to the list and disable the click event
                    entranceStudents.add(entranceGrid.getChildren().indexOf(student) + 1);
                    student.setDisable(true);
                    playCharacter.setDisable(true);

                    //disable all students different form the clicked one
                    for (Node otherStudent : entranceGrid.getChildren()) {
                        if (otherStudent != student) {
                            otherStudent.setOpacity(0.5);
                            otherStudent.setDisable(true);
                        }
                    }

                    //if the student to switch has been selected
                    if (diningStudents.size() == entranceStudents.size()) {
                        //hide the two selected students
                        student.setVisible(false);
                        Node switchedStudent = diningGrid.getChildren().stream().filter(n -> !n.isDisable()).findFirst().orElse(null);
                        switchedStudent.setVisible(false);

                        //enable all the students disabled
                        for (Node otherStudent : entranceGrid.getChildren()) {
                            if (otherStudent != student) {
                                otherStudent.setOpacity(1);
                                otherStudent.setDisable(false);
                            }
                        }
                        for (Node otherStudent : diningGrid.getChildren()) {
                            if (otherStudent != switchedStudent) {
                                otherStudent.setOpacity(1);
                                otherStudent.setDisable(false);
                            }
                        }
                        playCharacter.setDisable(false);

                        //manage the other choices
                        choices--;
                        numOfDiningStudents--;
                        if (choices == 0 || numOfDiningStudents == 0) {
                            client.send(new PayCharacter10Message(diningStudents, entranceStudents));
                        }
                    }
                });

            }
        }
    }

    /**
     * request input for character 11
     */
    private void character11()
    {
        alert("Move a student from the card to dining room, drag and drop");
        cardWithStudents.getStyleClass().add("character-" + client.getCharacterSelected());
        for (ColorS student : ((ReducedModelExpertMode) client.getReducedModel()).getCharacterById(client.getCharacterSelected()).getStudents()){
            Pane studentPane = new Pane();
            studentPane.getStyleClass().add(student.toString().toLowerCase());
            studentPane.setPrefHeight(50);
            studentPane.setPrefWidth(50);
            studentPane.setOnDragDetected(e -> {
                Dragboard db = studentPane.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(valueOf(cardWithStudents.getChildren().indexOf(studentPane)));
                db.setContent(content);
                e.consume();
            });
            cardWithStudents.getChildren().add(studentPane);
        }

        diningGrid.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.ANY);
            }
            e.consume();
        });

        diningGrid.setOnDragDropped(e -> {
            String studentIndex = e.getDragboard().getString();
            client.send(new PayCharacter11Message(Integer.parseInt(studentIndex) + 1));
            e.consume();
        });
    }

    /**
     * request input for character 12
     */
    private void character12()
    {
        alert("Choose a color");
        requestInput.setText("Choose a color, clicking on it");
        colorsInput.setVisible(true);
        redStudent.setOnMouseClicked(e -> client.send(new PayCharacter12Message(ColorS.RED)));
        greenStudent.setOnMouseClicked(e -> client.send(new PayCharacter12Message(ColorS.GREEN)));
        blueStudent.setOnMouseClicked(e -> client.send(new PayCharacter12Message(ColorS.BLUE)));
        yellowStudent.setOnMouseClicked(e -> client.send(new PayCharacter12Message(ColorS.YELLOW)));
        pinkStudent.setOnMouseClicked(e -> client.send(new PayCharacter12Message(ColorS.PINK)));
        playCharacter.setVisible(true);
        showInput();
    }

}
