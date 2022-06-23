package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.AbstractSceneController;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.client.reducedModel.*;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * GUI class (graphical user interface UI)
 */
public class GUI implements UI {

    private Client client;
    private AbstractSceneController controller;

    /**
     * starts the thread for JavaFX
     * @param disconnectClient method to call when JavaFX is stopped
     */
    public void startJavaFX(Runnable disconnectClient){
        JavaFXGUI.disconnectClient = disconnectClient;
        new Thread(JavaFXGUI::launchJavaFX).start();
    }

    /**
     * gets the client state based on the given ClientState enum
     * @param clientState client state enum
     * @param client client
     * @return abstract client state
     */
    @Override
    public AbstractClientState getClientState(ClientState clientState, Client client)
    {
        switch (clientState){
            case WELCOME: return new GUIWelcomeState(client);
            case MENU: return new GUIMenuState(client);
            case CREATE_LOBBY: return new GUICreateLobbyState(client);
            case JOIN_LOBBY: return new GUIShowLobbiesState(client);
            case PLAY_ASSISTANT: return new GUIPlayAssistantState(client);
            case WAITING: return new GUIWaitingState(client);
            case MOVE_STUDENTS: return new GUIMoveStudentsState(client);
            case MOVE_MN: return new GUIMoveMotherNatureState(client);
            case CHOOSE_CLOUD: return new GUIChooseCloudState(client);
            case END_GAME: return new GUIEndGameState(client);
            case PLAY_CHARACTER: return new GUIPlayCharacterState(client);
            default : return null;
        }
    }

    /**
     * loads a FXML scene
     * @param url FXML file's URL
     * @param client client to set for controller
     */
    public void loadScene(URL url, Client client){
        FXMLLoader loader = new FXMLLoader(url);
        try {
            Parent root = loader.load();
            controller = loader.getController();
            controller.setClient(client);
            controller.setup();
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * gets the scene controller
     * @return scene controller
     */
    public AbstractSceneController getSceneController() {
        return controller;
    }

    /**
     * fills the main GUI components with the reduced model information
     * @param client client
     * @param islands group for islands to fill
     * @param cloudsGrid grid for clouds to fill
     * @param assistantPlayed pane for assistant played to fill
     * @param entrance grid for entrance students to fill
     * @param dining grid for dining students to fill
     * @param professors grid for professors to fill
     * @param towers grid for towers to fill
     * @param playCharacter button to show characters
     * @param coins tile pane for coins to fill
     * @param character1 button for character 1 to fill
     * @param character2 button for character 2 to fill
     * @param character3 button for character 3 to fill
     * @param currentEffect text for current effect to fill
     */
    public void showModel(Client client, Group islands, GridPane cloudsGrid, Pane assistantPlayed, GridPane entrance,
                          GridPane dining, GridPane professors, GridPane towers, Button playCharacter, TilePane coins,
                          Button character1, Button character2, Button character3, TextFlow currentEffect) {
        this.client = client;
        ReducedModel reducedModel = client.getReducedModel();
        showIslands(reducedModel.getIslands(), islands);
        showClouds(reducedModel.getClouds(), cloudsGrid);
        ReducedBoard myBoard = reducedModel.getBoard(client.getUsername());
        if (myBoard != null){
            if (myBoard.getAssistantDeck().getPlayedAssistant() != null){
                showAssistantPlayed(myBoard.getAssistantDeck().getPlayedAssistant(), assistantPlayed);
            }
            showEntrance(myBoard.getEntranceStudents(), entrance);
            showDining(myBoard.getStudents(), dining);
            showProfessors(myBoard.getProfessors(), professors);
            showTowers(myBoard.getTowerColor(), myBoard.getNumOfTowers(), towers);
        }
        if (reducedModel instanceof ReducedModelExpertMode){
            List<Button> characters = new ArrayList<>();
            characters.add(character1);
            characters.add(character2);
            characters.add(character3);
            showExpertMode((ReducedModelExpertMode) reducedModel, playCharacter, coins, characters, currentEffect);
        }
    }

    /**
     * fills the group with the correct islands in an IslandsPane (circular pane)
     * @param islands reduced islands to put
     * @param islandsGroup islands group to fill
     */
    private void showIslands(List<ReducedIsland> islands, Group islandsGroup){
        IslandsPane islandsPane = new IslandsPane();
        islandsPane.setPrefHeight(276);
        islandsPane.setPrefWidth(665);
        for (int i = 0; i < islands.size(); i++){
            TilePane island = new TilePane();
            island.getStyleClass().add("island" + ((i % 3) + 1));
            island.setMaxWidth(80);
            island.setMaxHeight(80);
            island.setPrefTileWidth(20);
            island.setPrefHeight(20);
            showIslandComponents(island, islands.get(i));
            islandsPane.getChildren().add(island);
        }
        islandsGroup.getChildren().add(islandsPane);
    }

    /**
     * fills the tile pane for an island with the components on it (students, mother nature, towers and no-entry-tile)
     * @param islandPane tile pane to fill
     * @param reducedIsland reduced island
     */
    private void showIslandComponents(TilePane islandPane, ReducedIsland reducedIsland){
        //adding students
        for (ColorS student : reducedIsland.getStudents()){
            Pane studentPane = new Pane();
            studentPane.getStyleClass().add(student.toString().toLowerCase());
            studentPane.setPrefHeight(20);
            studentPane.setPrefWidth(20);
            islandPane.getChildren().add(studentPane);
        }
        //adding mother nature
        if (reducedIsland.isMotherNature()){
            Pane motherNature = new Pane();
            motherNature.getStyleClass().add("motherNature");
            motherNature.setPrefHeight(20);
            motherNature.setPrefWidth(20);
            motherNature.setId("motherNature");
            islandPane.getChildren().add(motherNature);
        }
        //adding towers
        for (int i = 0; i < reducedIsland.getNumOfTowers(); i++){
            if (reducedIsland.getTower() != null){
                Pane tower = new Pane();
                tower.getStyleClass().add(reducedIsland.getTower().toString().toLowerCase());
                tower.setPrefHeight(20);
                tower.setPrefWidth(20);
                islandPane.getChildren().add(tower);
            }
        }
        //adding no-entry-tile
        if (client.getReducedModel() instanceof ReducedModelExpertMode){
            ReducedModelExpertMode reducedModelExpertMode = (ReducedModelExpertMode) client.getReducedModel();
            if (reducedModelExpertMode.getNoEntryTiles().get(reducedIsland.getId())){
                Pane noEntryTile = new Pane();
                noEntryTile.getStyleClass().add("noEntryTile");
                noEntryTile.setPrefHeight(20);
                noEntryTile.setPrefWidth(20);
                islandPane.getChildren().add(noEntryTile);
            }
        }
    }

    /**
     * fills the grid with the correct clouds
     * @param clouds reduced clouds to put
     * @param cloudsGrid clouds grid to fill
     */
    private void showClouds(List<ReducedCloud> clouds, GridPane cloudsGrid){
        int row = 0;
        for (int i = 0; i < clouds.size(); i++){
            if (i != 0 && i % 2 == 0){
                row++;
            }
            TilePane cloud = new TilePane();
            cloud.getStyleClass().add("cloud" + ((i % 4) + 1));
            cloud.setMaxWidth(100);
            cloud.setMaxHeight(100);
            showCloudStudents(cloud, clouds.get(i).getStudents());
            cloudsGrid.add(cloud, i % 2, row);
            GridPane.setHalignment(cloud, HPos.CENTER);
        }
    }

    /**
     * fills the tile pane for a cloud with the students on it
     * @param cloud tile pane to fill
     * @param students students to put
     */
    private void showCloudStudents(TilePane cloud, List<ColorS> students){
        for (ColorS student : students){
            Pane studentPane = new Pane();
            studentPane.getStyleClass().add(student.toString().toLowerCase());
            studentPane.setPrefHeight(20);
            studentPane.setPrefWidth(20);
            cloud.getChildren().add(studentPane);
        }
    }

    /**
     * fills the main GUI components for other players
     * @param modelView model view (the main view of the scene)
     * @param otherBoardsButtons grid for other board buttons
     * @param overlayPane overlay view
     * @param otherPlayerView pane for other player's board and info
     * @param otherEntrance grid for other player's entrance
     * @param otherDining grid for other player's dining room
     * @param otherProfessors grid for other player's professor table
     * @param otherTowers grid for other player's towers board
     * @param otherAssistantPlayed pane for other player's assistant
     * @param otherCoins tile pane for other player's coins
     * @param otherPlayerName text for other player's name
     */
    public void showOtherPlayers(AnchorPane modelView, GridPane otherBoardsButtons,
                                 AnchorPane overlayPane, Group otherPlayerView, GridPane otherEntrance, GridPane otherDining,
                                 GridPane otherProfessors, GridPane otherTowers, Pane otherAssistantPlayed,
                                 TilePane otherCoins, Text otherPlayerName){
        List<ReducedBoard> boards = client.getReducedModel().getBoards();
        int row = 0;
        for (ReducedBoard reducedBoard : boards) {
            if (!reducedBoard.getPlayer().equals(client.getUsername())) {
                //create button for each player's board
                Button board = new Button();
                board.getStyleClass().add("schoolBoard");
                board.setMaxWidth(200);
                board.setMaxHeight(100);
                board.setText(reducedBoard.getPlayer());

                //set the action for each button created
                board.setOnAction(e -> {
                    //clear the pane's info
                    otherEntrance.getChildren().clear();
                    otherDining.getChildren().clear();
                    otherProfessors.getChildren().clear();
                    otherTowers.getChildren().clear();
                    otherAssistantPlayed.getStyleClass().clear();
                    otherCoins.getChildren().clear();

                    //add the correct player's info
                    otherPlayerName.setText(reducedBoard.getPlayer() + "'s board and info");
                    showEntrance(reducedBoard.getEntranceStudents(), otherEntrance);
                    showDining(reducedBoard.getStudents(), otherDining);
                    showProfessors(reducedBoard.getProfessors(), otherProfessors);
                    showTowers(reducedBoard.getTowerColor(), reducedBoard.getNumOfTowers(), otherTowers);
                    if (reducedBoard.getAssistantDeck().getPlayedAssistant() != null){
                        showAssistantPlayed(reducedBoard.getAssistantDeck().getPlayedAssistant(), otherAssistantPlayed);
                    }
                    if (client.getReducedModel() instanceof ReducedModelExpertMode){
                        ReducedModelExpertMode reducedModelExpertMode = (ReducedModelExpertMode) client.getReducedModel();
                        Text numOfCoins = new Text();
                        numOfCoins.setText(String.valueOf(reducedModelExpertMode.getCoins().get(reducedBoard.getPlayer())));
                        otherCoins.getChildren().add(numOfCoins);
                        otherCoins.setVisible(true);
                    }

                    //set the overlay pane
                    modelView.setDisable(true);
                    modelView.setOpacity(0.5);
                    overlayPane.setVisible(true);
                    overlayPane.setDisable(false);
                    otherPlayerView.setVisible(true);
                });

                //add each button to the grid
                otherBoardsButtons.add(board, 0, row);
                GridPane.setHalignment(board, HPos.CENTER);
                row++;
            }
        }
    }

    /**
     * fills the pane for assistant played
     * @param assistant reduced assistant played
     * @param assistantPlayed pane to fill
     */
    private void showAssistantPlayed(ReducedAssistant assistant, Pane assistantPlayed){
        assistantPlayed.getStyleClass().add("assistant-" + assistant.getId());
    }

    /**
     * fills the grid with the correct entrance students
     * @param entranceStudents entrance students to put
     * @param entranceGrid grid to fill
     */
    private void showEntrance(List<ColorS> entranceStudents, GridPane entranceGrid){
        int row = 0;
        int column = 1;
        for (ColorS entranceStudent : entranceStudents) {
            if (column >= 2) {
                row++;
                column = 0;
            }
            Pane student = new Pane();
            student.getStyleClass().add(entranceStudent.toString().toLowerCase());
            student.setMaxWidth(38);
            student.setMaxHeight(38);
            entranceGrid.add(student, column, row);
            GridPane.setHalignment(student, HPos.CENTER);
            column++;
        }
    }

    /**
     * fills the grid with the correct dining students
     * @param diningStudents dining students to put
     * @param diningGrid grid to fill
     */
    private void showDining(Map<ColorS, Integer> diningStudents, GridPane diningGrid){
        List<ColorS> rows = new ArrayList<>(Arrays.asList(ColorS.GREEN, ColorS.RED, ColorS.YELLOW, ColorS.PINK, ColorS.BLUE));
        int rowIndex = 0;
        for (ColorS row : rows){
            int columnIndex = 0;
            for (int i = 0; i < diningStudents.get(row); i++){
                Pane student = new Pane();
                student.getStyleClass().add(row.toString().toLowerCase());
                student.setMaxWidth(38);
                student.setMaxHeight(38);
                diningGrid.add(student, columnIndex, rowIndex);
                GridPane.setHalignment(student, HPos.CENTER);
                columnIndex++;
            }
            rowIndex++;
        }
    }

    /**
     * fills the grid with the correct professors
     * @param professors professors to put
     * @param professorsGrid grid to fill
     */
    private void showProfessors(List<ColorS> professors, GridPane professorsGrid){
        List<ColorS> rows = new ArrayList<>(Arrays.asList(ColorS.GREEN, ColorS.RED, ColorS.YELLOW, ColorS.PINK, ColorS.BLUE));
        int rowIndex = 0;
        for (ColorS row : rows){
            if (professors.contains(row)){
                Pane professor = new Pane();
                professor.getStyleClass().add("prof_" + row.toString().toLowerCase());
                professor.setMaxWidth(40);
                professor.setMaxHeight(40);
                professor.setRotate(90);
                professorsGrid.add(professor, 0, rowIndex);
                GridPane.setHalignment(professor, HPos.CENTER);
            }
            rowIndex++;
        }
    }

    /**
     * fills the grid with the correct towers
     * @param color tower color
     * @param numOfTowers number of towers
     * @param towersGrid grid to fill
     */
    private void showTowers(ColorT color, int numOfTowers, GridPane towersGrid){
        int row = 0;
        for (int i = 0; i < numOfTowers; i++){
            if (i != 0 && i % 2 == 0){
                row++;
            }
            Pane tower = new Pane();
            tower.getStyleClass().add(color.toString().toLowerCase());
            towersGrid.add(tower, i % 2, row);
            GridPane.setHalignment(tower, HPos.CENTER);
        }
    }

    /**
     * shows and fills expert mode components
     * @param reducedModelExpertMode reduced model (expert mode)
     * @param playCharacter button to show characters
     * @param coins tile pane for coins to fill
     * @param characters buttons for characters to fill
     * @param currentEffect text for current effect to fill
     */
    private void showExpertMode(ReducedModelExpertMode reducedModelExpertMode, Button playCharacter, TilePane coins,
                                List<Button> characters, TextFlow currentEffect){
        //show button
        playCharacter.setVisible(true);
        //adding coins
        Text numOfCoins = new Text();
        numOfCoins.setText(String.valueOf(reducedModelExpertMode.getCoins().get(client.getUsername())));
        coins.getChildren().add(numOfCoins);
        coins.setVisible(true);
        //adding current effect
        Text currentEffectText = new Text();
        currentEffectText.setText(reducedModelExpertMode.getCurrentEffect());
        currentEffectText.setFont(Font.font("Gigi", 30));
        currentEffectText.setFill(Color.RED);
        currentEffect.getChildren().add(currentEffectText);
        currentEffect.setTextAlignment(TextAlignment.CENTER);
        //adding characters
        for (int i = 0; i < reducedModelExpertMode.getCharacters().size(); i++){
            int characterId = reducedModelExpertMode.getCharacters().get(i).getId();
            characters.get(i).getStyleClass().add("character-" + characterId);
            characters.get(i).setOnAction(e ->{
                client.setCharacterSelected(characterId);
                client.changeState(ClientState.PLAY_CHARACTER);
            });
            characters.get(i).setGraphic(new TilePane());
            incrementCost(characters.get(i), reducedModelExpertMode.getCharacters().get(i));
            if (reducedModelExpertMode.getCharacters().get(i).getStudents() != null){
                for (ColorS student : reducedModelExpertMode.getCharacters().get(i).getStudents()){
                    Pane studentPane = new Pane();
                    studentPane.getStyleClass().add(student.toString().toLowerCase());
                    studentPane.setPrefHeight(40);
                    studentPane.setPrefWidth(40);
                    ((TilePane)characters.get(i).getGraphic()).getChildren().add(studentPane);
                }
            }
            if (reducedModelExpertMode.getCharacters().get(i).getCost() > reducedModelExpertMode.getCoins()
                    .get(client.getUsername()) || reducedModelExpertMode.isCharacterAlreadyPlayed()){
                characters.get(i).setDisable(true);
            }
        }
    }

    /**
     * adds a coin on the card if necessary
     * @param characterButton character button
     * @param character reduced character
     */
    private void incrementCost(Button characterButton, ReducedCharacter character){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("assets/characters.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;
            for (Object o : cards){
                JSONObject card =  (JSONObject) o;
                if (((Long)card.get("id")).intValue()==character.getId()){
                    int initialCost = ((Long)card.get("cost")).intValue();
                    if (character.getCost() != initialCost){
                        Pane coin = new Pane();
                        coin.getStyleClass().add("coin");
                        coin.setPrefHeight(40);
                        coin.setPrefWidth(40);
                        ((TilePane)characterButton.getGraphic()).getChildren().add(coin);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the fade transition for an alert pane
     * @param alert alert pane
     */
    public void setAlertAnimation(Pane alert){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(3000));
        fadeTransition.setNode(alert);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        alert.setVisible(true);
        fadeTransition.play();
    }

}

/**
 * class IslandsPane that extends Pane to represents islands on a circle
 */
class IslandsPane extends Pane {

    /**
     * sets the layout for children (islands) that are on a circle
     */
    @Override
    protected void layoutChildren() {
        double increment = (double) 360 / getChildren().size();
        double degrees = 0;
        for (Node node : getChildren()) {
            double x = getWidth()/2 * Math.cos(Math.toRadians(degrees)) + getWidth() / 2;
            double y = getHeight()/2 * Math.sin(Math.toRadians(degrees)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degrees += increment;
        }
    }
}
