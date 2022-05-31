package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class MoveStudentsSceneController extends AbstractSceneController {

    // ---------- SCHOOLBOARDS
    @FXML
    Pane player1Coins;

    @FXML
    Pane player2Coins;

    @FXML
    Pane player3Coins;

    @FXML
    Pane player4Coins;

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

    @FXML
    GridPane player1Entrance;
    @FXML
    GridPane player1DiningRoom;
    @FXML
    GridPane player1ProfessorBoard;
    @FXML
    GridPane player1TowerBoard;

    @FXML
    GridPane player2Entrance;
    @FXML
    GridPane player2DiningRoom;
    @FXML
    GridPane player2ProfessorBoard;
    @FXML
    GridPane player2TowerBoard;

    @FXML
    GridPane player3Entrance;
    @FXML
    GridPane player3DiningRoom;
    @FXML
    GridPane player3ProfessorBoard;
    @FXML
    GridPane player3TowerBoard;

    @FXML
    GridPane player4Entrance;
    @FXML
    GridPane player4DiningRoom;
    @FXML
    GridPane player4ProfessorBoard;
    @FXML
    GridPane player4TowerBoard;


    // ---------- CLOUDS
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

    @FXML
    GridPane cloudPane;

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
    @FXML
    GridPane islandPane;
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
    @FXML
    GridPane islandStudentsPane;

    // ---------- CHARACTERS
    @FXML
    Button playCharacter1;

    @FXML
    Button playCharacter2;

    @FXML
    Button playCharacter3;
    private int bigCount;
    int[] countSchoolBoard = {0, 0, 0, 0, 0};

    private Map<Integer, String> movementsChosen;

    @Override
    public void setup()
    {
        playCharacter1.setVisible(false);
        playCharacter2.setVisible(false);
        playCharacter3.setVisible(false);

        bigCount = 0;

        movementsChosen = new HashMap<>();

        handleCharacters();
        showModel();
    }

    // ---------- CHARACTERS
    public void handleCharacters()
    {
        if(client.getReducedModel() instanceof ReducedModelExpertMode)
        {

            int id1 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(0).getId();
            int id2 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(1).getId();
            int id3 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(2).getId();

            Image img1 = new Image(valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id1 + ".jpg")));
            Image img2 = new Image(valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id2 + ".jpg")));
            Image img3 = new Image(valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id3 + ".jpg")));

            ImageView view1 = new ImageView(img1);
            ImageView view2 = new ImageView(img2);
            ImageView view3 = new ImageView(img3);

            view1.setFitWidth(50);
            view1.setPreserveRatio(true);
            playCharacter1.setGraphic(view1);
            playCharacter1.setVisible(true);
            playCharacter1.setOnAction(e -> {
            });

            view2.setFitWidth(50);
            view2.setPreserveRatio(true);
            playCharacter2.setGraphic(view2);
            playCharacter2.setVisible(true);
            playCharacter2.setOnAction(e -> {
            });

            view3.setFitWidth(50);
            view3.setPreserveRatio(true);
            playCharacter3.setGraphic(view3);
            playCharacter3.setVisible(true);
            playCharacter3.setOnAction(e -> {
            });
        }
    }

    public void showModel()
    {
        showIslands();
        showIslandStudents();
        showClouds();
        showCloudsStudents();
        showSchoolBoards();
        //showCoins();
        //showPlayedAssistant();
        //showNoEntryTile();
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

        Image ex1 = new Image(valueOf(getClass().getResource("/img/Tavolo/Isole/island1.png")));
        Image ex2 = new Image(valueOf(getClass().getResource("/img/Tavolo/Isole/island2.png")));
        Image ex3 = new Image(valueOf(getClass().getResource("/img/Tavolo/Isole/island3.png")));

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
        configureIsland(island1Students, 1);
        islandStudents.add(island2Students);
        configureIsland(island2Students, 2);
        islandStudents.add(island3Students);
        configureIsland(island3Students, 3);
        islandStudents.add(island4Students);
        configureIsland(island4Students, 4);
        islandStudents.add(island5Students);
        configureIsland(island5Students, 5);
        islandStudents.add(island6Students);
        configureIsland(island6Students, 6);
        islandStudents.add(island7Students);
        configureIsland(island7Students, 7);
        islandStudents.add(island8Students);
        configureIsland(island8Students, 8);
        islandStudents.add(island9Students);
        configureIsland(island9Students, 9);
        islandStudents.add(island10Students);
        configureIsland(island10Students, 10);
        islandStudents.add(island11Students);
        configureIsland(island11Students, 11);
        islandStudents.add(island12Students);
        configureIsland(island12Students, 12);

        Image blue = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));
        Image mN = new Image(valueOf(getClass().getResource("/img/Tavolo/mother_nature.png")));

        /*ImageView cb = new ImageView(blue);
        ImageView cr = new ImageView(red);
        ImageView cy = new ImageView(yellow);
        ImageView cp = new ImageView(pink);
        ImageView cg= new ImageView(green);

        setStudentsDimension(cb);
        setStudentsDimension(cr);
        setStudentsDimension(cy);
        setStudentsDimension(cp);
        setStudentsDimension(cg);*/

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

        for(TilePane island : islands)
        {
            island.toFront();
        }
    }

    // ---------- CLOUDS
    public void showClouds()
    {
        clouds.add(cloud1);
        clouds.add(cloud2);
        clouds.add(cloud3);
        clouds.add(cloud4);

        Image ex1 = new Image(valueOf(getClass().getResource("/img/Tavolo/Nuvole/cloud_card_1.png")));
        Image ex2 = new Image(valueOf(getClass().getResource("/img/Tavolo/Nuvole/cloud_card_2.png")));
        Image ex3 = new Image(valueOf(getClass().getResource("/img/Tavolo/Nuvole/cloud_card_3.png")));
        Image ex4 = new Image(valueOf(getClass().getResource("/img/Tavolo/Nuvole/cloud_card_4.png")));

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

        Image blue = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

        /*ImageView cb = new ImageView(blue);
        ImageView cr = new ImageView(red);
        ImageView cy = new ImageView(yellow);
        ImageView cp = new ImageView(pink);
        ImageView cg= new ImageView(green);

        setStudentsDimension(cb);
        setStudentsDimension(cr);
        setStudentsDimension(cy);
        setStudentsDimension(cp);
        setStudentsDimension(cg);*/

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

        Image ex1 = new Image(valueOf(getClass().getResource("/img/Plancia/Plancia_DEF2.png")));

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
        Image blue = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

        String color;

        int count = 0;

        ImageView supp = new ImageView();

        for (int i = 0; i < 5 && count < client.getReducedModel().getBoards().get(0).getEntranceStudents().size(); i++) {
            for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(0).getEntranceStudents().size(); j++) {
                if (i == 0 && j == 0) {

                }
                else{
                    color = client.getReducedModel().getBoards().get(0).getEntranceStudents().get(count).toString().toLowerCase();
                    switch (color) {
                        case "blue":
                            supp = new ImageView(blue);
                            player1Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                        case "red":
                            supp = new ImageView(red);
                            player1Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                        case "pink":
                            supp = new ImageView(pink);
                            player1Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                        case "yellow":
                            supp = new ImageView(yellow);
                            player1Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                        case "green":
                            supp = new ImageView(green);
                            player1Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                    }
                    if(client.getReducedModel().getBoards().get(0).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
                    {
                        setDragAndDrop(player1Entrance, supp, color, count);
                    }
                    count++;

                    //player1Entrance.getOnDragDropped();
                }
            }
        }

        count = 0;
        supp = new ImageView();

        for (int i = 0; i < 5 && count < client.getReducedModel().getBoards().get(1).getEntranceStudents().size(); i++) {
            for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(1).getEntranceStudents().size(); j++) {
                if (i == 0 && j == 0) {

                }
                else{
                    color = client.getReducedModel().getBoards().get(1).getEntranceStudents().get(count).toString().toLowerCase();
                    switch (color) {
                        case "blue":
                            supp = new ImageView(blue);
                            player2Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                        case "red":
                            supp = new ImageView(red);
                            player2Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                        case "pink":
                            supp = new ImageView(pink);
                            player2Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                        case "yellow":
                            supp = new ImageView(yellow);
                            player2Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                        case "green":
                            supp = new ImageView(green);
                            player2Entrance.add(setStudentsDimension(supp), j, i);
                            break;
                    }
                    if(client.getReducedModel().getBoards().get(1).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
                    {
                        setDragAndDrop(player2Entrance, supp, color, count);
                    }
                    count++;
                }
            }
        }

        count = 0;
        supp = new ImageView();

        if (client.getReducedModel().getBoards().size() >= 3) {
            for (int i = 0; i < 5 && count < client.getReducedModel().getBoards().get(2).getEntranceStudents().size(); i++) {
                for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(2).getEntranceStudents().size(); j++) {
                    if (i == 0 && j == 0) {}
                    else{
                        color = client.getReducedModel().getBoards().get(2).getEntranceStudents().get(count).toString().toLowerCase();
                        switch (color) {
                            case "blue":
                                supp = new ImageView(blue);
                                player3Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                            case "red":
                                supp = new ImageView(red);
                                player3Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                            case "pink":
                                supp = new ImageView(pink);
                                player3Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                            case "yellow":
                                supp = new ImageView(yellow);
                                player3Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                            case "green":
                                supp = new ImageView(green);
                                player3Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                        }
                        if(client.getReducedModel().getBoards().get(2).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
                        {
                            setDragAndDrop(player3Entrance, supp, color, count);
                        }
                        count++;
                    }
                }
            }
            player3Entrance.toFront();
        }

        count = 0;
        supp = new ImageView();

        if (client.getReducedModel().getBoards().size() == 4) {
            for (int i = 0; i < 5 && count < client.getReducedModel().getBoards().get(3).getEntranceStudents().size(); i++) {
                for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(3).getEntranceStudents().size(); j++) {
                    if (i == 0 && j == 0) {}
                    else{
                        color = client.getReducedModel().getBoards().get(3).getEntranceStudents().get(count).toString().toLowerCase();
                        switch (color) {
                            case "blue":
                                supp = new ImageView(blue);
                                player4Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                            case "red":
                                supp = new ImageView(red);
                                player4Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                            case "pink":
                                supp = new ImageView(pink);
                                player4Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                            case "yellow":
                                supp = new ImageView(yellow);
                                player4Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                            case "green":
                                supp = new ImageView(green);
                                player4Entrance.add(setStudentsDimension(supp), j, i);
                                break;
                        }
                        if(client.getReducedModel().getBoards().get(3).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
                        {
                            setDragAndDrop(player4Entrance, supp, color, count);
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
        Image blue = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

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
        if(client.getReducedModel().getBoards().get(0).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
        {
            configureDiningRoom(player1DiningRoom);
        }

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
        if(client.getReducedModel().getBoards().get(1).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
        {
            configureDiningRoom(player2DiningRoom);
        }

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
            if(client.getReducedModel().getBoards().get(2).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
            {
                configureDiningRoom(player3DiningRoom);
            }
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
            if(client.getReducedModel().getBoards().get(3).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
            {
                configureDiningRoom(player4DiningRoom);
            }
        }
    }

    public void showProfessorTable()
    {
        Image blue = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_blue.png")));
        Image red = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_red.png")));
        Image yellow = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_yellow.png")));
        Image pink = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_pink.png")));
        Image green = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_green.png")));
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
                player1TowerBoard.add(setTowerDimension(new ImageView(new Image(valueOf(getClass().getResource("/img/Plancia/Torri/" + towerColor +"_tower.png"))))), j, i);
                count++;

            }
        }
        player1TowerBoard.toFront();

        count = 0;

        //PLAYER2
        for (int i = 0; i < 4 && count < client.getReducedModel().getBoards().get(1).getNumOfTowers(); i++) {
            for (int j = 0; j < 2 && count < client.getReducedModel().getBoards().get(1).getNumOfTowers(); j++) {
                String towerColor = client.getReducedModel().getBoards().get(1).getTowerColor().toString();
                player2TowerBoard.add(setTowerDimension(new ImageView(new Image(valueOf(getClass().getResource("/img/Plancia/Torri/" + towerColor +"_tower.png"))))), j, i);
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
                    player3TowerBoard.add(setTowerDimension(new ImageView(new Image(valueOf(getClass().getResource("/img/Plancia/Torri/" + towerColor +"_tower.png"))))), j, i);
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
                    player4TowerBoard.add(setTowerDimension(new ImageView(new Image(valueOf(getClass().getResource("/img/Plancia/Torri/" + towerColor +"_tower.png"))))), j, i);
                    count++;

                }
            }
            player4TowerBoard.toFront();
        }

    }

    public void showCoins()
    {

    }

    public void showPlayedAssistant()
    {

    }

    public void showNoEntryTile()
    {

    }

    public void setDragAndDrop(GridPane entrance, ImageView supp, String color, int count)
    {
        //detected
        //drop

        entrance.getChildren().get(entrance.getChildren().size() - 1).setOnDragDetected((javafx.scene.input.MouseEvent event) ->
        {
            Dragboard db = supp.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();

            content.putImage(supp.getImage());
            //content.putString(color);
            content.putString(valueOf(count));
            db.setContent(content);
            entrance.getChildren().remove(supp);

            event.consume();
        });

    }

    public void configureIsland(TilePane island, int id)
    {
       island.setOnDragOver((DragEvent event)
                -> {
                   if (event.getDragboard().hasImage()) {
                       event.acceptTransferModes(TransferMode.ANY);
                   }
                   event.consume();
               });
       island.setOnDragDropped((DragEvent event)
               -> {
                    Image img = event.getDragboard().getImage();
                    island.getChildren().add(setStudentsDimension(new ImageView(img)));
                    checkIsland(parseInt(event.getDragboard().getString()), id);
                    event.consume();
                });
    }

    public void configureDiningRoom(GridPane diningRoom)
    {
        diningRoom.setOnDragOver((DragEvent event)
                -> {
            if (event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });


        diningRoom.setOnDragDropped((DragEvent event)
                -> {
                    Image img = event.getDragboard().getImage();
                    /*String color = event.getDragboard().getString();

                    ColorS colorS = null;
                    int i = 0;

                    switch (color) {
                        case "blue":
                            i =  4;
                            colorS = ColorS.BLUE;
                            countSchoolBoard[4] = countSchoolBoard[4] + 1;
                            break;
                        case "red":
                            i = 1;
                            colorS = ColorS.RED;
                            countSchoolBoard[1] = countSchoolBoard[1] + 1;
                            break;
                        case "pink":
                            i = 3;
                            colorS = ColorS.PINK;
                            countSchoolBoard[3] = countSchoolBoard[3] + 1;
                            break;
                        case "yellow":
                            i = 2;
                            colorS = ColorS.YELLOW;
                            countSchoolBoard[2] = countSchoolBoard[2] + 1;
                            break;
                        case "green":
                            i = 0;
                            colorS = ColorS.GREEN;
                            countSchoolBoard[0] = countSchoolBoard[0] + 1;
                            break;
                    }

                    for(int k = 0; k < client.getReducedModel().getBoards().size(); k++)
                    {
                        if(client.getReducedModel().getBoards().get(k).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
                        {
                            diningRoom.add(setStudentsDimension(new ImageView(img)),
                                    client.getReducedModel().getBoards().get(k).getStudents().get(colorS) + countSchoolBoard[i] - 1,
                                    i);
                        }
                    }*/

            int index = Integer.parseInt(event.getDragboard().getString());
            ColorS colorS = null;
            String color = null;

            for(int k = 0; k < client.getReducedModel().getBoards().size(); k++)
            {
                if(client.getReducedModel().getBoards().get(k).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
                {
                    color = client.getReducedModel().getBoards().get(k).getEntranceStudents().get(index).toString().toLowerCase();
                }
            }

            int i = 0;

            switch (color) {
                case "blue":
                    i =  4;
                    colorS = ColorS.BLUE;
                    countSchoolBoard[4] = countSchoolBoard[4] + 1;
                    break;
                case "red":
                    i = 1;
                    colorS = ColorS.RED;
                    countSchoolBoard[1] = countSchoolBoard[1] + 1;
                    break;
                case "pink":
                    i = 3;
                    colorS = ColorS.PINK;
                    countSchoolBoard[3] = countSchoolBoard[3] + 1;
                    break;
                case "yellow":
                    i = 2;
                    colorS = ColorS.YELLOW;
                    countSchoolBoard[2] = countSchoolBoard[2] + 1;
                    break;
                case "green":
                    i = 0;
                    colorS = ColorS.GREEN;
                    countSchoolBoard[0] = countSchoolBoard[0] + 1;
                    break;
            }

            for(int k = 0; k < client.getReducedModel().getBoards().size(); k++)
            {
                if(client.getReducedModel().getBoards().get(k).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
                {
                    diningRoom.add(setStudentsDimension(new ImageView(img)),
                            client.getReducedModel().getBoards().get(k).getStudents().get(colorS) + countSchoolBoard[i] - 1,
                            i);
                }
            }

            checkDiningRoom(index);
            diningRoom.toFront();
            event.consume();
        });
    }

    public void checkDiningRoom(Integer index)
    {
        bigCount = bigCount + 1;
        String destination = "DINING ROOM";
        System.out.println(index);
        movementsChosen.put(index + 1, destination);
        System.out.println(movementsChosen.get(index));

        check();
    }

    public void checkIsland(Integer stud, Integer id)
    {
        bigCount = bigCount + 1;

        System.out.println(stud);

        movementsChosen.put(stud + 1, valueOf(id));
        System.out.println(movementsChosen.get(stud));

        check();
    }

    public void check()
    {
        if(bigCount == 3)
        {
            System.out.println("sto dentro");
            System.out.println(movementsChosen);
            client.send(new MoveStudentMessage(movementsChosen));
        }
    }

}
