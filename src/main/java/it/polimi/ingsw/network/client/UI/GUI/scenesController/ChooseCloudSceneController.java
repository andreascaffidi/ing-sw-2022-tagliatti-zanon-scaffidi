package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class ChooseCloudSceneController extends AbstractSceneController {
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


    // ---------- SETUP

    @Override
    public void setup() {

        playCharacter1.setVisible(false);
        playCharacter2.setVisible(false);
        playCharacter3.setVisible(false);

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

            Image img1 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id1 + ".jpg")));
            Image img2 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id2 + ".jpg")));
            Image img3 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id3 + ".jpg")));

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

    // ---------- MODEL
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

        configureCloud(cloudStudents);

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

    public void configureCloud(List<TilePane> clouds)
    {

        for(int i = 0; i < cloudStudents.size(); i++)
        {
            cloudStudents.get(i).setOnMouseClicked((MouseEvent event) ->
            {
                check(i);
            });
            break;
        }
    }

    public void check(int index)
    {
        client.send(new ChooseCloudMessage(index + 1));
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
}
