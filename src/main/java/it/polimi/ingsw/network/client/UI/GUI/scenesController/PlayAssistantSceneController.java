package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.*;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.gameMessages.PayCharacter1Message;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

public class PlayAssistantSceneController extends AbstractSceneController {

    // CHARACTERS
    @FXML
    Pane character1;
    @FXML
    TilePane character1MoveStudents;

    @FXML
    Pane character2;
    @FXML
    TilePane character2MoveStudents;
    @FXML
    Pane character3;
    @FXML
    TilePane character3MoveStudents;

    List<TilePane> characterStudents = new ArrayList<>();

    // ASSISTANTS
    @FXML
    Pane player1Assistant;

    @FXML
    Pane player2Assistant;

    @FXML
    Pane player3Assistant;

    @FXML
    Pane player4Assistant;

    @FXML
    List<Pane> assistants = new ArrayList<>();

    // COINS
    @FXML
    Pane player1Coins;

    @FXML
    Pane player2Coins;

    @FXML
    Pane player3Coins;

    @FXML
    Pane player4Coins;

    @FXML
    List<Pane> coins = new ArrayList<>();

    // ---------- SCHOOLBOARDS:

    @FXML
    Pane player1;

    @FXML
    Pane player2;

    @FXML
    Pane player3;

    @FXML
    Pane player4;

    @FXML
    List<Pane> schoolBoards = new ArrayList<>();

    // ---------- PLAYER1 SCHOOLBOARD COMPONENTS:

    @FXML
    GridPane player1Entrance;
    @FXML
    GridPane player1DiningRoom;
    @FXML
    GridPane player1ProfessorBoard;
    @FXML
    GridPane player1TowerBoard;

    // ---------- PLAYER2 SCHOOLBOARD COMPONENTS:

    @FXML
    GridPane player2Entrance;
    @FXML
    GridPane player2DiningRoom;
    @FXML
    GridPane player2ProfessorBoard;
    @FXML
    GridPane player2TowerBoard;

    // ---------- PLAYER3 SCHOOLBOARD COMPONENTS:

    @FXML
    GridPane player3Entrance;
    @FXML
    GridPane player3DiningRoom;
    @FXML
    GridPane player3ProfessorBoard;
    @FXML
    GridPane player3TowerBoard;

    // ---------- PLAYER4 SCHOOLBOARD COMPONENTS:

    @FXML
    GridPane player4Entrance;
    @FXML
    GridPane player4DiningRoom;
    @FXML
    GridPane player4ProfessorBoard;
    @FXML
    GridPane player4TowerBoard;

    // ---------- CLOUDS:

    @FXML
    TilePane cloud1;

    @FXML
    TilePane cloud2;

    @FXML
    TilePane cloud3;

    @FXML
    TilePane cloud4;

    @FXML
    List<TilePane> clouds = new ArrayList<>();

    // ---------- CLOUDS GRIDPANE
    @FXML
    GridPane cloudPane;

    // ---------- CLOUDS STUDENTS

    @FXML
    TilePane cloud1Students;

    @FXML
    TilePane cloud2Students;

    @FXML
    TilePane cloud3Students;

    @FXML
    TilePane cloud4Students;

    @FXML
    List<TilePane> cloudStudents = new ArrayList<>();

    // ---------- CLOUDS STUDENTS GRIDPANE
    @FXML
    GridPane cloudStudentsPane;

    // ---------- ISLANDS
    @FXML
    TilePane island1;

    @FXML
    TilePane island2;
    @FXML
    TilePane island3;
    @FXML
    TilePane island4;
    @FXML
    TilePane island5;
    @FXML
    TilePane island6;
    @FXML
    TilePane island7;
    @FXML
    TilePane island8;
    @FXML
    TilePane island9;
    @FXML
    TilePane island10;
    @FXML
    TilePane island11;
    @FXML
    TilePane island12;
    @FXML
    List<TilePane> islands = new ArrayList<>();

    // ---------- ISLANDS GRIDPANE
    @FXML
    GridPane islandPane;

    // ---------- ISLANDS STUDENTS

    @FXML
    TilePane island1Students;

    @FXML
    TilePane island2Students;
    @FXML
    TilePane island3Students;
    @FXML
    TilePane island4Students;
    @FXML
    TilePane island5Students;
    @FXML
    TilePane island6Students;
    @FXML
    TilePane island7Students;
    @FXML
    TilePane island8Students;
    @FXML
    TilePane island9Students;
    @FXML
    TilePane island10Students;
    @FXML
    TilePane island11Students;
    @FXML
    TilePane island12Students;
    @FXML
    List<TilePane> islandStudents = new ArrayList<>();

    // ---------- ISLANDS STUDENTS GRIDPANE

    @FXML
    GridPane islandStudentsPane;

    // ---------- CHARACTERS

    @FXML
    Button playCharacter;

    // ---------- ASSISTANTS

    @FXML
    Button Assistant1;

    @FXML
    Button Assistant2;

    @FXML
    Button Assistant3;

    @FXML
    Button Assistant4;

    @FXML
    Button Assistant5;

    @FXML
    Button Assistant6;

    @FXML
    Button Assistant7;

    @FXML
    Button Assistant8;

    @FXML
    Button Assistant9;

    @FXML
    Button Assistant10;

    // ---------- ASSISTANT DECK
    List<Button> deck = new ArrayList<>();
    List<Integer> possibleChoices = null;

    // ---------- ALERT
    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    // ---------- ALERT

    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }

    // ---------- SETUP
    @Override
    public void setup() {
        handleCharacters();
        showModel();
    }

    // ---------- CHARACTERS
    public void handleCharacters() {

        characterStudents.add(character1MoveStudents);
        characterStudents.add(character2MoveStudents);
        characterStudents.add(character3MoveStudents);

        if (client.getReducedModel() instanceof ReducedModelExpertMode) {

            playCharacter.setVisible(true);
            playCharacter.setOnAction(e -> {
                if (!((ReducedModelExpertMode) client.getReducedModel()).isCharacterAlreadyPlayed()) {
                    client.changeState(ClientState.PLAY_CHARACTER);
                }
            });
            playCharacter.toFront();

            ReducedModelExpertMode reducedModelExpertMode = (ReducedModelExpertMode) client.getReducedModel();
            for (int i = 0; i < reducedModelExpertMode.getCharacters().size(); i++) {
                if (reducedModelExpertMode.getCharacters().get(i).getId() == 1 ||
                        reducedModelExpertMode.getCharacters().get(i).getId() == 7 ||
                        reducedModelExpertMode.getCharacters().get(i).getId() == 11) {
                    String color;
                    for (ColorS s : reducedModelExpertMode.getCharacters().get(i).getStudents()) {
                        Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
                        Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
                        Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
                        Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
                        Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));


                        color = s.toString().toLowerCase();
                        switch (color) {
                            case "blue":
                                characterStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(blue)));
                                break;
                            case "red":
                                characterStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(red)));
                                break;
                            case "pink":
                                characterStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(pink)));
                                break;
                            case "yellow":
                                characterStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(yellow)));
                                break;
                            case "green":
                                characterStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(green)));
                                break;
                        }
                    }
                }
                characterStudents.get(i).toFront();
            }

        }

    }

    // ---------- MODEL

    public void showModel()
    {
        showIslands();
        showIslandStudents();
        showClouds();
        showCloudsStudents();
        showSchoolBoards();
        showAssistants();
        showCoins();
        showPlayedAssistant();
        showCharacters();
    }

    // ---------- ASSISTANTS
    public void setAssistantDimension(ImageView view)
    {
        view.setFitWidth(50);
        view.setPreserveRatio(true);
    }

    public void showAssistants()
    {
        Assistant1.setVisible(true);
        Assistant2.setVisible(true);
        Assistant3.setVisible(true);
        Assistant4.setVisible(true);
        Assistant5.setVisible(true);
        Assistant6.setVisible(true);
        Assistant7.setVisible(true);
        Assistant8.setVisible(true);
        Assistant9.setVisible(true);
        Assistant10.setVisible(true);

        Assistant1.toFront();
        Assistant2.toFront();
        Assistant3.toFront();
        Assistant4.toFront();
        Assistant5.toFront();
        Assistant6.toFront();
        Assistant7.toFront();
        Assistant8.toFront();
        Assistant9.toFront();
        Assistant10.toFront();

        List<Integer> possibleChoices = null;

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        if (myBoard != null){
            possibleChoices = myBoard.getAssistantDeck().getAssistantCards().stream()
                    .map(ReducedAssistant::getId).collect(Collectors.toList());
        }

        if(possibleChoices != null) {
            Image img1 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(1).png")));
            ImageView view1 = new ImageView(img1);
            setAssistantDimension(view1);
            Assistant1.setGraphic(view1);
            if (possibleChoices.contains(1)) {
                deck.add(Assistant1);
                Assistant1.setOnAction(e -> {
                    deck.remove(Assistant1);
                    client.send(new PlayAssistantMessage(1));
                });
            } else {
                Assistant1.setVisible(false);
            }

            Image img2 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(2).png")));
            ImageView view2 = new ImageView(img2);
            setAssistantDimension(view2);
            Assistant2.setGraphic(view2);
            if (possibleChoices.contains(2)) {
                deck.add(Assistant2);
                Assistant2.setOnAction(e -> {
                    deck.remove(Assistant2);
                    client.send(new PlayAssistantMessage(2));
                });
            } else {
                Assistant2.setVisible(false);
            }

            Image img3 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(3).png")));
            ImageView view3 = new ImageView(img3);
            setAssistantDimension(view3);
            Assistant3.setGraphic(view3);
            if (possibleChoices.contains(3)) {
                deck.add(Assistant3);
                Assistant3.setOnAction(e -> {
                    deck.remove(Assistant3);
                    client.send(new PlayAssistantMessage(3));
                });
            } else {
                Assistant3.setVisible(false);
            }

            Image img4 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(4).png")));
            ImageView view4 = new ImageView(img4);
            setAssistantDimension(view4);
            Assistant4.setGraphic(view4);
            if (possibleChoices.contains(4)) {
                deck.add(Assistant4);
                Assistant4.setOnAction(e -> {
                    deck.remove(Assistant4);
                    client.send(new PlayAssistantMessage(4));
                });
            } else {
                Assistant4.setVisible(false);
            }

            Image img5 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(5).png")));
            ImageView view5 = new ImageView(img5);
            setAssistantDimension(view5);
            Assistant5.setGraphic(view5);
            if (possibleChoices.contains(5)) {
                deck.add(Assistant5);
                Assistant5.setOnAction(e -> {
                    deck.remove(Assistant5);
                    client.send(new PlayAssistantMessage(5));
                });
            } else {
                Assistant5.setVisible(false);
            }

            Image img6 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(6).png")));
            ImageView view6 = new ImageView(img6);
            setAssistantDimension(view6);
            Assistant6.setGraphic(view6);
            if (possibleChoices.contains(6)) {
                deck.add(Assistant6);
                Assistant6.setOnAction(e -> {
                    deck.remove(Assistant6);
                    client.send(new PlayAssistantMessage(6));
                });
            } else {
                Assistant6.setVisible(false);
            }

            Image img7 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(7).png")));
            ImageView view7 = new ImageView(img7);
            setAssistantDimension(view7);
            Assistant7.setGraphic(view7);
            if (possibleChoices.contains(7)) {
                deck.add(Assistant7);
                Assistant7.setOnAction(e -> {
                    deck.remove(Assistant7);
                    client.send(new PlayAssistantMessage(7));
                });
            } else {
                Assistant7.setVisible(false);
            }

            Image img8 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(8).png")));
            ImageView view8 = new ImageView(img8);
            setAssistantDimension(view8);
            Assistant8.setGraphic(view8);
            if (possibleChoices.contains(8)) {
                deck.add(Assistant8);
                Assistant8.setOnAction(e -> {
                    deck.remove(Assistant8);
                    client.send(new PlayAssistantMessage(8));
                });
            } else {
                Assistant8.setVisible(false);
            }

            Image img9 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(9).png")));
            ImageView view9 = new ImageView(img9);
            setAssistantDimension(view9);
            Assistant9.setGraphic(view9);
            if (possibleChoices.contains(9)) {
                deck.add(Assistant9);
                Assistant9.setOnAction(e -> {
                    deck.remove(Assistant9);
                    client.send(new PlayAssistantMessage(9));
                });
            } else {
                Assistant9.setVisible(false);
            }

            Image img10 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(10).png")));
            ImageView view10 = new ImageView(img10);
            setAssistantDimension(view10);
            Assistant10.setGraphic(view10);
            if (possibleChoices.contains(10)) {
                deck.add(Assistant10);
                Assistant10.setOnAction(e -> {
                    deck.remove(Assistant10);
                    client.send(new PlayAssistantMessage(10));
                });
            } else {
                Assistant10.setVisible(false);
            }
        }
    }

    // ---------- ISLANDS
    public void setIslandDimension(ImageView view)
    {
        view.setFitHeight(80);
        view.setFitWidth(80);
    }

    public void showIslands()
    {
        //adding island to array of islands
        islands.add(island1);
        islands.add(island2);
        islands.add(island3);
        islands.add(island4);
        islands.add(island5);
        islands.add(island6);
        islands.add(island7);
        islands.add(island8);
        islands.add(island9);
        islands.add(island10);
        islands.add(island11);
        islands.add(island12);

        Image ex1 = new Image(String.valueOf(getClass().getResource("/img/Tavolo/Isole/island1.png")));
        Image ex2 = new Image(String.valueOf(getClass().getResource("/img/Tavolo/Isole/island2.png")));
        Image ex3 = new Image(String.valueOf(getClass().getResource("/img/Tavolo/Isole/island3.png")));

        ImageView iex1 = new ImageView(ex1);
        ImageView iex2 = new ImageView(ex2);
        ImageView iex3 = new ImageView(ex3);
        ImageView iex4 = new ImageView(ex1);
        ImageView iex5 = new ImageView(ex2);
        ImageView iex6 = new ImageView(ex3);
        ImageView iex7 = new ImageView(ex1);
        ImageView iex8 = new ImageView(ex2);
        ImageView iex9 = new ImageView(ex3);
        ImageView iex10 = new ImageView(ex1);
        ImageView iex11 = new ImageView(ex2);
        ImageView iex12 = new ImageView(ex3);

        setIslandDimension(iex1);
        setIslandDimension(iex2);
        setIslandDimension(iex3);
        setIslandDimension(iex4);
        setIslandDimension(iex5);
        setIslandDimension(iex6);
        setIslandDimension(iex7);
        setIslandDimension(iex8);
        setIslandDimension(iex9);
        setIslandDimension(iex10);
        setIslandDimension(iex11);
        setIslandDimension(iex12);

        islands.get(0).getChildren().add(iex1);
        islands.get(1).getChildren().add(iex2);
        islands.get(2).getChildren().add(iex3);
        islands.get(3).getChildren().add(iex4);
        islands.get(4).getChildren().add(iex5);
        islands.get(5).getChildren().add(iex6);
        islands.get(6).getChildren().add(iex7);
        islands.get(7).getChildren().add(iex8);
        islands.get(8).getChildren().add(iex9);
        islands.get(9).getChildren().add(iex10);
        islands.get(10).getChildren().add(iex11);
        islands.get(11).getChildren().add(iex12);

        for(int i = client.getReducedModel().getIslands().size(); i < islands.size(); i++)
        {
            islands.get(i).setVisible(false);
        }

    }

    // ---------- ISLAND STUDENTS
    public ImageView setStudentsDimension(ImageView view)
    {
        view.setFitWidth(20);
        view.setFitHeight(20);
        return view;
    }
    public void showIslandStudents()
    {
        islandStudents.add(island1Students);
        islandStudents.add(island2Students);
        islandStudents.add(island3Students);
        islandStudents.add(island4Students);
        islandStudents.add(island5Students);
        islandStudents.add(island6Students);
        islandStudents.add(island7Students);
        islandStudents.add(island8Students);
        islandStudents.add(island9Students);
        islandStudents.add(island10Students);
        islandStudents.add(island11Students);
        islandStudents.add(island12Students);

        Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));
        Image mN = new Image(String.valueOf(getClass().getResource("/img/Tavolo/mother_nature.png")));

        String color;

        for(int i = 0; i < client.getReducedModel().getIslands().size(); i++)
        {
            for(ColorS s : client.getReducedModel().getIslands().get(i).getStudents()) {
                color = s.toString().toLowerCase();
                switch(color){
                    case "blue": islandStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(blue)));
                    break;
                    case "red": islandStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(red)));
                    break;
                    case "pink": islandStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(pink)));
                    break;
                    case "yellow": islandStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(yellow)));
                    break;
                    case "green": islandStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(green)));
                    break;
                }
            }

            if(client.getReducedModel().getIslands().get(i).isMotherNature())
            {
                islandStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(mN)));
            }
        }

        for(int i = 0; i < client.getReducedModel().getIslands().size(); i++)
        {
            for(int j = 0; j < client.getReducedModel().getIslands().get(i).getNumOfTowers(); j++) {
                if (client.getReducedModel().getIslands().get(i).getTower() != null) {
                    islandStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(new Image(valueOf(getClass().getResource("/img/Plancia/Torri/" + client.getReducedModel().getIslands().get(i).getTower().toString() + "_tower.png"))))));
                }
            }
        }

        if(client.getReducedModel() instanceof ReducedModelExpertMode)
        {
            for(ReducedIsland r : client.getReducedModel().getIslands())
            {
                if(((ReducedModelExpertMode) client.getReducedModel()).getNoEntryTiles().containsKey(r))
                {
                    if(((ReducedModelExpertMode) client.getReducedModel()).getNoEntryTiles().get(r).equals(true))
                    {
                        islandStudents.get(client.getReducedModel().getIslands().indexOf(r)).getChildren().add(setStudentsDimension(new ImageView("/img/Tavolo/Isole/deny_island_icon.png")));
                    }
                }
            }
        }
    }

    // ---------- CLOUDS
    public void showClouds()
    {
        clouds.add(cloud1);
        clouds.add(cloud2);
        clouds.add(cloud3);
        clouds.add(cloud4);

        Image ex1 = new Image(String.valueOf(getClass().getResource("/img/Tavolo/Nuvole/cloud_card_1.png")));
        Image ex2 = new Image(String.valueOf(getClass().getResource("/img/Tavolo/Nuvole/cloud_card_2.png")));
        Image ex3 = new Image(String.valueOf(getClass().getResource("/img/Tavolo/Nuvole/cloud_card_3.png")));
        Image ex4 = new Image(String.valueOf(getClass().getResource("/img/Tavolo/Nuvole/cloud_card_4.png")));

        ImageView iex1 = new ImageView(ex1);
        ImageView iex2 = new ImageView(ex2);
        ImageView iex3 = new ImageView(ex3);
        ImageView iex4 = new ImageView(ex4);

        setIslandDimension(iex1);
        setIslandDimension(iex2);
        setIslandDimension(iex3);
        setIslandDimension(iex4);

        clouds.get(0).getChildren().add(iex1);
        clouds.get(1).getChildren().add(iex2);
        clouds.get(2).getChildren().add(iex3);
        clouds.get(3).getChildren().add(iex4);

        for(int i = client.getReducedModel().getClouds().size(); i < clouds.size(); i++)
        {
            clouds.get(i).setVisible(false);
        }
    }

    public void showCloudsStudents()
    {
        cloudStudents.add(cloud1Students);
        cloudStudents.add(cloud2Students);
        cloudStudents.add(cloud3Students);
        cloudStudents.add(cloud4Students);

        Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

        String color;

        for(int i = 0; i < client.getReducedModel().getClouds().size(); i++)
        {
            for(ColorS s : client.getReducedModel().getClouds().get(i).getStudents()) {
                color = s.toString().toLowerCase();
                switch(color){
                    case "blue": cloudStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(blue)));
                        break;
                    case "red": cloudStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(red)));
                        break;
                    case "pink": cloudStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(pink)));
                        break;
                    case "yellow": cloudStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(yellow)));
                        break;
                    case "green": cloudStudents.get(i).getChildren().add(setStudentsDimension(new ImageView(green)));
                        break;
                }
            }
        }
    }

    public ImageView setSchoolBoardSize(ImageView view)
    {
        view.setFitHeight(200);
        view.setFitWidth(400);
        return view;
    }

    public void showSchoolBoards()
    {
        schoolBoards.add(player1);
        schoolBoards.add(player2);
        schoolBoards.add(player3);
        schoolBoards.add(player4);

        Image ex1 = new Image(String.valueOf(getClass().getResource("/img/Plancia/Plancia_DEF2.png")));

        for(int i = 0; i < client.getReducedModel().getBoards().size(); i++)
        {
            schoolBoards.get(i).getChildren().add(setSchoolBoardSize(new ImageView(ex1)));
        }

        for(int i = client.getReducedModel().getBoards().size(); i < schoolBoards.size(); i++)
        {
            schoolBoards.get(i).setVisible(false);
        }

        showEntrance();
        showDiningRoom();
        showProfessorTable();
        showTowers();
    }

    public void showEntrance() {
        Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

        String color;

        int count = 0;

        for (int i = 0; i < 5 && count < client.getReducedModel().getBoards().get(0).getEntranceStudents().size(); i++) {
            for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(0).getEntranceStudents().size(); j++) {
                if (i == 0 && j == 0) {

                }
                else{
                    color = client.getReducedModel().getBoards().get(0).getEntranceStudents().get(count).toString().toLowerCase();
                    switch (color) {
                        case "blue":
                            player1Entrance.add(setStudentsDimension(new ImageView(blue)), j, i);
                            break;
                        case "red":
                            player1Entrance.add(setStudentsDimension(new ImageView(red)), j, i);
                            break;
                        case "pink":
                            player1Entrance.add(setStudentsDimension(new ImageView(pink)), j, i);
                            break;
                        case "yellow":
                            player1Entrance.add(setStudentsDimension(new ImageView(yellow)), j, i);
                            break;
                        case "green":
                            player1Entrance.add(setStudentsDimension(new ImageView(green)), j, i);
                            break;
                    }
                    count++;
                }
            }
        }

        count = 0;

        for (int i = 0; i < 5 && count < client.getReducedModel().getBoards().get(1).getEntranceStudents().size(); i++) {
            for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(1).getEntranceStudents().size(); j++) {
                if (i == 0 && j == 0) {

                }
                else{
                    color = client.getReducedModel().getBoards().get(1).getEntranceStudents().get(count).toString().toLowerCase();
                    switch (color) {
                        case "blue":
                            player2Entrance.add(setStudentsDimension(new ImageView(blue)), j, i);
                            break;
                        case "red":
                            player2Entrance.add(setStudentsDimension(new ImageView(red)), j, i);
                            break;
                        case "pink":
                            player2Entrance.add(setStudentsDimension(new ImageView(pink)), j, i);
                            break;
                        case "yellow":
                            player2Entrance.add(setStudentsDimension(new ImageView(yellow)), j, i);
                            break;
                        case "green":
                            player2Entrance.add(setStudentsDimension(new ImageView(green)), j, i);
                            break;
                    }
                    count++;
                }
            }
        }

        count = 0;

        if (client.getReducedModel().getBoards().size() >= 3) {
            for (int i = 0; i < 5 && count < client.getReducedModel().getBoards().get(2).getEntranceStudents().size(); i++) {
                for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(2).getEntranceStudents().size(); j++) {
                    if (i == 0 && j == 0) {}
                    else{
                        color = client.getReducedModel().getBoards().get(2).getEntranceStudents().get(count).toString().toLowerCase();
                        switch (color) {
                            case "blue":
                                player3Entrance.add(setStudentsDimension(new ImageView(blue)), j, i);
                                break;
                            case "red":
                                player3Entrance.add(setStudentsDimension(new ImageView(red)), j, i);
                                break;
                            case "pink":
                                player3Entrance.add(setStudentsDimension(new ImageView(pink)), j, i);
                                break;
                            case "yellow":
                                player3Entrance.add(setStudentsDimension(new ImageView(yellow)), j, i);
                                break;
                            case "green":
                                player3Entrance.add(setStudentsDimension(new ImageView(green)), j, i);
                                break;
                        }
                        count++;
                    }
                }
            }
            player3Entrance.toFront();
        }

        count = 0;

        if (client.getReducedModel().getBoards().size() == 4) {
            for (int i = 0; i < 5 && count < client.getReducedModel().getBoards().get(3).getEntranceStudents().size(); i++) {
                for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(3).getEntranceStudents().size(); j++) {
                    if (i == 0 && j == 0) {}
                    else{
                        color = client.getReducedModel().getBoards().get(3).getEntranceStudents().get(count).toString().toLowerCase();
                        switch (color) {
                            case "blue":
                                player4Entrance.add(setStudentsDimension(new ImageView(blue)), j, i);
                                break;
                            case "red":
                                player4Entrance.add(setStudentsDimension(new ImageView(red)), j, i);
                                break;
                            case "pink":
                                player4Entrance.add(setStudentsDimension(new ImageView(pink)), j, i);
                                break;
                            case "yellow":
                                player4Entrance.add(setStudentsDimension(new ImageView(yellow)), j, i);
                                break;
                            case "green":
                                player4Entrance.add(setStudentsDimension(new ImageView(green)), j, i);
                                break;
                        }
                        count++;
                    }
                }
            }
            player4Entrance.toFront();
        }
        player1Entrance.toFront();
        player2Entrance.toFront();
    }

    public void showDiningRoom()
    {
        Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

        //PLAYER1
        for(int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.GREEN) ; i++)
        {
            player1DiningRoom.add(setStudentsDimension(new ImageView(green)), i, 0);
        }
        for(int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.RED) ; i++)
        {
            player1DiningRoom.add(setStudentsDimension(new ImageView(red)), i, 1);
        }
        for(int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.YELLOW) ; i++)
        {
            player1DiningRoom.add(setStudentsDimension(new ImageView(yellow)), i, 2);
        }
        for(int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.PINK) ; i++)
        {
            player1DiningRoom.add(setStudentsDimension(new ImageView(pink)), i, 3);
        }
        for(int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.BLUE) ; i++)
        {
            player1DiningRoom.add(setStudentsDimension(new ImageView(blue)), i, 4);
        }
        player1DiningRoom.toFront();

        //PLAYER2
        for(int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.GREEN) ; i++)
        {
            player2DiningRoom.add(setStudentsDimension(new ImageView(green)), i, 0);
        }
        for(int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.RED) ; i++)
        {
            player2DiningRoom.add(setStudentsDimension(new ImageView(red)), i, 1);
        }
        for(int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.YELLOW) ; i++)
        {
            player2DiningRoom.add(setStudentsDimension(new ImageView(yellow)), i, 2);
        }
        for(int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.PINK) ; i++)
        {
            player2DiningRoom.add(setStudentsDimension(new ImageView(pink)), i, 3);
        }
        for(int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.BLUE) ; i++)
        {
            player2DiningRoom.add(setStudentsDimension(new ImageView(blue)), i, 4);
        }
        player2DiningRoom.toFront();

        //PLAYER3
        if (client.getReducedModel().getBoards().size() >= 3) {
            for (int i = 0; i < client.getReducedModel().getBoards().get(2).getStudents().get(ColorS.GREEN); i++) {
                player3DiningRoom.add(setStudentsDimension(new ImageView(green)), i, 0);
            }
            for (int i = 0; i < client.getReducedModel().getBoards().get(2).getStudents().get(ColorS.RED); i++) {
                player3DiningRoom.add(setStudentsDimension(new ImageView(red)), i, 1);
            }
            for (int i = 0; i < client.getReducedModel().getBoards().get(2).getStudents().get(ColorS.YELLOW); i++) {
                player3DiningRoom.add(setStudentsDimension(new ImageView(yellow)), i, 2);
            }
            for (int i = 0; i < client.getReducedModel().getBoards().get(2).getStudents().get(ColorS.PINK); i++) {
                player3DiningRoom.add(setStudentsDimension(new ImageView(pink)), i, 3);
            }
            for (int i = 0; i < client.getReducedModel().getBoards().get(2).getStudents().get(ColorS.BLUE); i++) {
                player3DiningRoom.add(setStudentsDimension(new ImageView(blue)), i, 4);
            }
            player3DiningRoom.toFront();
        }

        //PLAYER4
        if (client.getReducedModel().getBoards().size() == 4) {
            for (int i = 0; i < client.getReducedModel().getBoards().get(3).getStudents().get(ColorS.GREEN); i++) {
                player4DiningRoom.add(setStudentsDimension(new ImageView(green)), i, 0);
            }
            for (int i = 0; i < client.getReducedModel().getBoards().get(3).getStudents().get(ColorS.RED); i++) {
                player4DiningRoom.add(setStudentsDimension(new ImageView(red)), i, 1);
            }
            for (int i = 0; i < client.getReducedModel().getBoards().get(3).getStudents().get(ColorS.YELLOW); i++) {
                player4DiningRoom.add(setStudentsDimension(new ImageView(yellow)), i, 2);
            }
            for (int i = 0; i < client.getReducedModel().getBoards().get(3).getStudents().get(ColorS.PINK); i++) {
                player4DiningRoom.add(setStudentsDimension(new ImageView(pink)), i, 3);
            }
            for (int i = 0; i < client.getReducedModel().getBoards().get(3).getStudents().get(ColorS.BLUE); i++) {
                player4DiningRoom.add(setStudentsDimension(new ImageView(blue)), i, 4);
            }
            player4DiningRoom.toFront();
        }
    }

    public void showProfessorTable()
    {
        Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Professori/teacher_blue.png")));
        Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Professori/teacher_red.png")));
        Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Professori/teacher_yellow.png")));
        Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Professori/teacher_pink.png")));
        Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Professori/teacher_green.png")));
        String color;

        //PLAYER1
        for(ColorS s : client.getReducedModel().getBoards().get(0).getProfessors())
        {
            color = s.toString().toLowerCase();
            switch (color) {
                case "blue":
                    player1ProfessorBoard.add(setStudentsDimension(new ImageView(blue)), 0, 4);
                    break;
                case "red":
                    player1ProfessorBoard.add(setStudentsDimension(new ImageView(red)), 0, 1);
                    break;
                case "pink":
                    player1ProfessorBoard.add(setStudentsDimension(new ImageView(pink)), 0, 3);
                    break;
                case "yellow":
                    player1ProfessorBoard.add(setStudentsDimension(new ImageView(yellow)), 0, 2);
                    break;
                case "green":
                    player1ProfessorBoard.add(setStudentsDimension(new ImageView(green)), 0, 0);
                    break;
            }

        }
        player1ProfessorBoard.toFront();

        //PLAYER2
        for(ColorS s : client.getReducedModel().getBoards().get(1).getProfessors())
        {
            color = s.toString().toLowerCase();
            switch (color) {
                case "blue":
                    player2ProfessorBoard.add(setStudentsDimension(new ImageView(blue)), 0, 4);
                    break;
                case "red":
                    player2ProfessorBoard.add(setStudentsDimension(new ImageView(red)), 0, 1);
                    break;
                case "pink":
                    player2ProfessorBoard.add(setStudentsDimension(new ImageView(pink)), 0, 3);
                    break;
                case "yellow":
                    player2ProfessorBoard.add(setStudentsDimension(new ImageView(yellow)), 0, 2);
                    break;
                case "green":
                    player2ProfessorBoard.add(setStudentsDimension(new ImageView(green)), 0, 0);
                    break;
            }

        }
        player2ProfessorBoard.toFront();

        //PLAYER3
        if (client.getReducedModel().getBoards().size() >= 3) {
            for(ColorS s : client.getReducedModel().getBoards().get(2).getProfessors())
            {
                color = s.toString().toLowerCase();
                switch (color) {
                    case "blue":
                        player3ProfessorBoard.add(setStudentsDimension(new ImageView(blue)), 0, 4);
                        break;
                    case "red":
                        player3ProfessorBoard.add(setStudentsDimension(new ImageView(red)), 0, 1);
                        break;
                    case "pink":
                        player3ProfessorBoard.add(setStudentsDimension(new ImageView(pink)), 0, 3);
                        break;
                    case "yellow":
                        player3ProfessorBoard.add(setStudentsDimension(new ImageView(yellow)), 0, 2);
                        break;
                    case "green":
                        player3ProfessorBoard.add(setStudentsDimension(new ImageView(green)), 0, 0);
                        break;
                }

            }
            player3ProfessorBoard.toFront();
        }

        //PLAYER4
        if (client.getReducedModel().getBoards().size() == 4) {
            for(ColorS s : client.getReducedModel().getBoards().get(3).getProfessors())
            {
                color = s.toString().toLowerCase();
                switch (color) {
                    case "blue":
                        player4ProfessorBoard.add(setStudentsDimension(new ImageView(blue)), 0, 4);
                        break;
                    case "red":
                        player4ProfessorBoard.add(setStudentsDimension(new ImageView(red)), 0, 1);
                        break;
                    case "pink":
                        player4ProfessorBoard.add(setStudentsDimension(new ImageView(pink)), 0, 3);
                        break;
                    case "yellow":
                        player4ProfessorBoard.add(setStudentsDimension(new ImageView(yellow)), 0, 2);
                        break;
                    case "green":
                        player4ProfessorBoard.add(setStudentsDimension(new ImageView(green)), 0, 0);
                        break;
                }

            }
            player4ProfessorBoard.toFront();
        }
    }

    public ImageView setTowerDimension(ImageView view)
    {
        view.setFitWidth(40);
        view.setFitHeight(80);
        return view;
    }

    //40 80
    public void showTowers()
    {
        int count = 0;

        //PLAYER1
        for (int i = 0; i < 4 && count < client.getReducedModel().getBoards().get(0).getNumOfTowers(); i++) {
            for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(0).getNumOfTowers(); j++) {
                String towerColor = client.getReducedModel().getBoards().get(0).getTowerColor().toString();
                player1TowerBoard.add(setTowerDimension(new ImageView(new Image(String.valueOf(getClass().getResource("/img/Plancia/Torri/" + towerColor +"_tower.png"))))), j, i);
                count++;

            }
        }
        player1TowerBoard.toFront();

        count = 0;

        //PLAYER2
        for (int i = 0; i < 4 && count < client.getReducedModel().getBoards().get(1).getNumOfTowers(); i++) {
            for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(1).getNumOfTowers(); j++) {
                String towerColor = client.getReducedModel().getBoards().get(1).getTowerColor().toString();
                player2TowerBoard.add(setTowerDimension(new ImageView(new Image(String.valueOf(getClass().getResource("/img/Plancia/Torri/" + towerColor +"_tower.png"))))), j, i);
                count++;

            }
        }
        player2TowerBoard.toFront();

        count = 0;

        //PLAYER3
        if (client.getReducedModel().getBoards().size() >= 3) {
            for (int i = 0; i < 4 && count < client.getReducedModel().getBoards().get(2).getNumOfTowers(); i++) {
                for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(2).getNumOfTowers(); j++) {
                    String towerColor = client.getReducedModel().getBoards().get(2).getTowerColor().toString();
                    player3TowerBoard.add(setTowerDimension(new ImageView(new Image(String.valueOf(getClass().getResource("/img/Plancia/Torri/" + towerColor +"_tower.png"))))), j, i);
                    count++;

                }
            }
            player3TowerBoard.toFront();
        }

        count = 0;

        //PLAYER4
        if (client.getReducedModel().getBoards().size() == 4) {
            for (int i = 0; i < 4 && count < client.getReducedModel().getBoards().get(3).getNumOfTowers(); i++) {
                for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(3).getNumOfTowers(); j++) {
                    String towerColor = client.getReducedModel().getBoards().get(3).getTowerColor().toString();
                    player4TowerBoard.add(setTowerDimension(new ImageView(new Image(String.valueOf(getClass().getResource("/img/Plancia/Torri/" + towerColor +"_tower.png"))))), j, i);
                    count++;

                }
            }
            player4TowerBoard.toFront();
        }

    }

    public void showCoins()
    {
        coins.add(player1Coins);
        coins.add(player2Coins);
        coins.add(player3Coins);
        coins.add(player4Coins);

        player1Coins.setVisible(false);
        player2Coins.setVisible(false);
        player3Coins.setVisible(false);

        Image ex1 = new Image(valueOf(getClass().getResource("/img/Plancia/Moneta_base.png")));

        for(int i = 0; i < client.getReducedModel().getBoards().size(); i++)
        {
            coins.get(i).getChildren().add(setStudentsDimension(new ImageView(ex1)));
            if(client.getReducedModel() instanceof ReducedModelExpertMode) {
                ReducedModelExpertMode reducedModel = (ReducedModelExpertMode) client.getReducedModel();
                coins.get(i).getChildren().add(new Text(reducedModel.getCoins().get(reducedModel.getBoards().get(i).getPlayer()).toString()));
                coins.get(i).setVisible(true);
                coins.get(i).toFront();
            }
        }


        for(int i = client.getReducedModel().getBoards().size(); i < schoolBoards.size(); i++)
        {
            coins.get(i).setVisible(false);
        }
    }

    public ImageView setAssistantSize(ImageView img)
    {
        img.setFitHeight(180);
        img.setFitWidth(100);
        return img;
    }

    public void showPlayedAssistant()
    {
        assistants.add(player1Assistant);
        assistants.add(player2Assistant);
        assistants.add(player3Assistant);
        assistants.add(player4Assistant);


        for(int i = 0; i < client.getReducedModel().getBoards().size(); i++)
        {
            if(client.getReducedModel().getBoards().get(i).getAssistantDeck().getPlayedAssistant() != null) {
                assistants.get(i).getChildren().add(setAssistantSize(new ImageView("/img/Assistenti/Assistente("
                        + (client.getReducedModel().getBoards().get(i).getAssistantDeck().getPlayedAssistant().getId()) + ").png")));
                assistants.get(i).setVisible(true);
                assistants.get(i).toFront();
                System.out.println(client.getReducedModel().getBoards().get(i).getAssistantDeck().getPlayedAssistant().getId());
                System.out.println(client.getReducedModel().getBoards().get(i).getAssistantDeck().getPlayedAssistant().getId());
            }
        }
    }

    public void showCharacters()
    {
        if(client.getReducedModel() instanceof ReducedModelExpertMode) {
            int id1 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(0).getId();
            int id2 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(1).getId();
            int id3 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(2).getId();

            Image img1 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id1 + ".jpg")));
            Image img2 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id2 + ".jpg")));
            Image img3 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id3 + ".jpg")));

            ImageView view1 = new ImageView(img1);
            ImageView view2 = new ImageView(img2);
            ImageView view3 = new ImageView(img3);

            view1.setFitWidth(100);
            view1.setFitHeight(150);
            character1.getChildren().add(view1);
            character1MoveStudents.setVisible(true);

            view2.setFitWidth(100);
            view2.setFitHeight(150);
            character2.getChildren().add(view2);
            character2MoveStudents.setVisible(true);

            view3.setFitWidth(100);
            view3.setFitHeight(150);
            character3.getChildren().add(view3);
            character3MoveStudents.setVisible(true);
        }
    }
}