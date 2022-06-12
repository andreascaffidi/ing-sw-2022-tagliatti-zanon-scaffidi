package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.network.client.reducedModel.ReducedCharacter;
import it.polimi.ingsw.network.client.reducedModel.ReducedIsland;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.requests.gameMessages.*;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class PlayCharacterSceneController extends AbstractSceneController {

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

    // SCHOOLBOARDS
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

    // PLAYER1 SCHOOLBOARD COMPONENTS

    @FXML
    GridPane player1Entrance;
    @FXML
    GridPane player1DiningRoom;
    @FXML
    GridPane player1ProfessorBoard;
    @FXML
    GridPane player1TowerBoard;

    // PLAYER2 SCHOOLBOARD COMPONENTS

    @FXML
    GridPane player2Entrance;
    @FXML
    GridPane player2DiningRoom;
    @FXML
    GridPane player2ProfessorBoard;
    @FXML
    GridPane player2TowerBoard;

    // PLAYER3 SCHOOLBOARD COMPONENTS

    @FXML
    GridPane player3Entrance;
    @FXML
    GridPane player3DiningRoom;
    @FXML
    GridPane player3ProfessorBoard;
    @FXML
    GridPane player3TowerBoard;

    // PLAYER4 SCHOOLBOARD COMPONENTS

    @FXML
    GridPane player4Entrance;
    @FXML
    GridPane player4DiningRoom;
    @FXML
    GridPane player4ProfessorBoard;
    @FXML
    GridPane player4TowerBoard;

    // CLOUDS

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

    // CLOUDS GRID

    @FXML
    GridPane cloudPane;

    // CLOUDS STUDENTS

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

    // CLOUDS STUDENTS GRID

    @FXML
    GridPane cloudStudentsPane;

    // ISLANDS
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

    // ISLANDS GRID
    @FXML
    GridPane islandPane;

    // ISLANDS STUDENTS
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

    // ISLANDS STUDENTS GRID

    @FXML
    GridPane islandStudentsPane;

    // CHARACTERS
    @FXML
    Button playCharacter1;
    @FXML
    TilePane character1Students;
    @FXML
    Pane character1;
    @FXML
    TilePane character1MoveStudents;

    @FXML
    Button playCharacter2;
    @FXML
    TilePane character2Students;
    @FXML
    Pane character2;
    @FXML
    TilePane character2MoveStudents;

    @FXML
    Button playCharacter3;
    @FXML
    TilePane character3Students;
    @FXML
    Pane character3;
    @FXML
    TilePane character3MoveStudents;

    //ALERT

    @FXML
    public Text alertMessage;
    @FXML
    public TextFlow alert;

    // CHARACTER IDS

    int id1;
    int id2;
    int id3;

    // CHARACTER UTILS

    @FXML
    public TextField AdditionalMovements;

    public ColorS[][] diningRoom;

    //----------------------------------------------- METHODS

    public void alert(String message){
        alertMessage.setText(message);
        alert.setVisible(true);
    }

    public void setup()
    {
        handleCharacters();
    }

    public void handleCharacters()
    {
        id1 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(0).getId();
        id2 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(1).getId();
        id3 = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(2).getId();

        /*character1.setAccessibleText("Cost: " + ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(0).getCost());
        character2.setAccessibleText("Cost: " + ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(1).getCost());
        character3.setAccessibleText("Cost: " + ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().get(2).getCost());*/

        Image img1 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id1 + ".jpg")));
        Image img2 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id2 + ".jpg")));
        Image img3 = new Image(String.valueOf(getClass().getResource("/img/Personaggi/CarteTOT_front" + id3 + ".jpg")));

        ImageView view1 = new ImageView(img1);
        ImageView view2 = new ImageView(img2);
        ImageView view3 = new ImageView(img3);


        view1.setFitWidth(200);
        view1.setFitHeight(400);
        playCharacter1.setGraphic(view1);
        playCharacter1.setVisible(true);
        character1Students.setVisible(true);
        playCharacter1.toFront();
        playCharacter1.setOnAction(e -> {
            play(id1);
        });
        isCardWithStudents(id1, character1Students, character1MoveStudents);

        view2.setFitWidth(200);
        view2.setFitHeight(400);
        playCharacter2.setGraphic(view2);
        playCharacter2.setVisible(true);
        character2Students.setVisible(true);
        playCharacter2.toFront();
        playCharacter2.setOnAction(e -> {
            play(id2);
        });
        isCardWithStudents(id2, character2Students, character2MoveStudents);

        view3.setFitWidth(200);
        view3.setFitHeight(400);
        playCharacter3.setGraphic(view3);
        playCharacter3.setVisible(true);
        character3Students.setVisible(true);
        playCharacter3.toFront();
        playCharacter3.setOnAction(e -> {
            play(id3);
        });
        isCardWithStudents(id3, character3Students, character3MoveStudents);

        character1Students.toFront();
        character2Students.toFront();
        character3Students.toFront();

        List<Integer> possibleChoices = ((ReducedModelExpertMode) client.getReducedModel()).getCharacters().stream().map(ReducedCharacter::getId).collect(Collectors.toList());

        if (!possibleChoices.contains(id1)) {
            playCharacter1.setVisible(false);
            character1.setVisible(false);
            character1MoveStudents.setVisible(false);
            character1Students.setVisible(false);
        } else if (((ReducedModelExpertMode) client.getReducedModel()).getCoins().get(client.getUsername()) <
        ((ReducedModelExpertMode) client.getReducedModel()).getCharacterById(id1).getCost()) {
            playCharacter1.setVisible(false);
            character1.setVisible(false);
            character1MoveStudents.setVisible(false);
            character1Students.setVisible(false);
        }

        if (!possibleChoices.contains(id2)) {
            playCharacter2.setVisible(false);
            character2.setVisible(false);
            character2MoveStudents.setVisible(false);
            character2Students.setVisible(false);
        } else if (((ReducedModelExpertMode) client.getReducedModel()).getCoins().get(client.getUsername()) <
                ((ReducedModelExpertMode) client.getReducedModel()).getCharacterById(id2).getCost()) {
            playCharacter2.setVisible(false);
            character2.setVisible(false);
            character2MoveStudents.setVisible(false);
            character2Students.setVisible(false);
        }

        if (!possibleChoices.contains(id3)) {
            playCharacter3.setVisible(false);
            character3.setVisible(false);
            character3MoveStudents.setVisible(false);
            character3Students.setVisible(false);
        } else if (((ReducedModelExpertMode) client.getReducedModel()).getCoins().get(client.getUsername()) <
                ((ReducedModelExpertMode) client.getReducedModel()).getCharacterById(id3).getCost()) {
            playCharacter3.setVisible(false);
            character3.setVisible(false);
            character3MoveStudents.setVisible(false);
            character3Students.setVisible(false);
        }
    }

    public void isCardWithStudents(int id, TilePane characterStudents, TilePane characterMoveStudents)
    {
        ReducedModelExpertMode reducedModel = (ReducedModelExpertMode) client.getReducedModel();

        if(id == 1 || id == 7 || id == 11)
        {
            String color;
            for(ColorS s : reducedModel.getCharacterById(id).getStudents())
            {
                Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
                Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
                Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
                Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
                Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));


                color = s.toString().toLowerCase();
                switch (color) {
                    case "blue":
                        characterStudents.getChildren().add(setStudentsDimension(new ImageView(blue)));
                        characterMoveStudents.getChildren().add(setStudentsDimension(new ImageView(blue)));
                        break;
                    case "red":
                        characterStudents.getChildren().add(setStudentsDimension(new ImageView(red)));
                        characterMoveStudents.getChildren().add(setStudentsDimension(new ImageView(red)));
                        break;
                    case "pink":
                        characterStudents.getChildren().add(setStudentsDimension(new ImageView(pink)));
                        characterMoveStudents.getChildren().add(setStudentsDimension(new ImageView(pink)));
                        break;
                    case "yellow":
                        characterStudents.getChildren().add(setStudentsDimension(new ImageView(yellow)));
                        characterMoveStudents.getChildren().add(setStudentsDimension(new ImageView(yellow)));
                        break;
                    case "green":
                        characterStudents.getChildren().add(setStudentsDimension(new ImageView(green)));
                        characterMoveStudents.getChildren().add(setStudentsDimension(new ImageView(green)));
                        break;
                }
            }
        }
        characterMoveStudents.setVisible(false);
    }

    public void play(int id)
    {
        AdditionalMovements.setVisible(false);

        playCharacter1.setVisible(false);
        playCharacter2.setVisible(false);
        playCharacter3.setVisible(false);

        character1Students.setVisible(false);
        character2Students.setVisible(false);
        character3Students.setVisible(false);

        showModel();

        character1.setVisible(true);
        character2.setVisible(true);
        character3.setVisible(true);

        character1MoveStudents.setVisible(true);
        character2MoveStudents.setVisible(true);
        character3MoveStudents.setVisible(true);

        switch (id){
            case 1 : character1();
            case 2 : character2();
            case 3 : character3();
            case 4 : character4();
            case 5 : character5();
            case 6 : character6();
            case 7 : character7();
            case 8 : character8();
            case 9 : character9();
            case 10 : character10();
            case 11 : character11();
            case 12 : character12();
        }
    }

    public void character1()
    {
        if(id1 == 1)
        {
            pickFromCard(character1MoveStudents);
        }
        if(id2 == 1)
        {
            pickFromCard(character2MoveStudents);
        }
        if(id3 == 1)
        {
            pickFromCard(character3MoveStudents);
        }

        for (int i = 0; i < client.getReducedModel().getIslands().size(); i++) {
            dropOnIsland1(i);
        }

    }

    public void pickFromCard(TilePane studentsCard)
    {
        for(Node img : studentsCard.getChildren())
        {
            img.setOnDragDetected((javafx.scene.input.MouseEvent event) ->
            {
                Dragboard db = img.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                ImageView image = (ImageView) img;
                content.putImage(image.getImage());
                content.putString(valueOf(studentsCard.getChildren().indexOf(img) + 1));
                db.setContent(content);
                studentsCard.getChildren().remove(img);
                event.consume();
            });
        }
    }

    public void dropOnIsland1(int i) {

        TilePane currentIsland = islandStudents.get(i);

        currentIsland.setOnDragOver((DragEvent event)
                -> {
            if (event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });

        currentIsland.setOnDragDropped((DragEvent event)
                -> {
            Image img = event.getDragboard().getImage();
            currentIsland.getChildren().add(setStudentsDimension(new ImageView(img)));
            client.send(new PayCharacter1Message(i + 1, parseInt(event.getDragboard().getString())));
            event.consume();
        });
    }

    public void character2()
    {
        client.send(new PayCharacter2Message());
    }

    public void character3()
    {
        for (int i = 0; i < client.getReducedModel().getIslands().size(); i++)
        {
            clickOnIsland(i);
        }
    }

    public void clickOnIsland(int i)
    {
        islandStudents.get(i).setOnMouseClicked((MouseEvent event) ->
        {
            client.send(new PayCharacter3Message(i + 1));
        });
    }

    public void character4()
    {
        AdditionalMovements.setVisible(true);
        AdditionalMovements.toFront();
        AdditionalMovements.setOnMouseClicked((MouseEvent event) ->
        {
            String movements = AdditionalMovements.getText();
            client.send(new PayCharacter4Message(parseInt(movements)));
        });
        AdditionalMovements.setVisible(false);
    }

    public void character5()
    {
        for (int i = 0; i < client.getReducedModel().getIslands().size(); i++) {
            clickOnIsland5(i);
        }
    }

    public void clickOnIsland5(int i)
    {
        islandStudents.get(i).setOnMouseClicked((MouseEvent event) ->
        {
            client.send(new PayCharacter5Message(i + 1));
        });
    }

    public void character6()
    {
        for (int i = 0; i < client.getReducedModel().getIslands().size(); i++) {
            clickOnIsland6(i);
        }
    }

    public void clickOnIsland6(int i)
    {
        islandStudents.get(i).setOnMouseClicked((MouseEvent event) ->
        {
            client.send(new PayCharacter6Message(i + 1));
        });
    }

    int numOfStudents;

    public void character7()
    {
        AdditionalMovements.setVisible(true);
        AdditionalMovements.toFront();

        AdditionalMovements.setOnKeyPressed((KeyEvent event) ->
        {
            numOfStudents = Integer.parseInt(AdditionalMovements.getText());
        });

        AdditionalMovements.setVisible(false);

        List<Integer> entranceChosen = new ArrayList<>();
        List<Integer> cardChosen = new ArrayList<>();


        for(int k = 0; k < client.getReducedModel().getBoards().size(); k++)
        {
            if(client.getReducedModel().getBoards().get(k).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
            {
               if(k == 0)
               {
                   for(Node img : player1Entrance.getChildren())
                   {
                       img.setOnMouseClicked((MouseEvent event) ->
                       {
                           entranceChosen.add(player1Entrance.getChildren().indexOf(img) + 1);
                           player1Entrance.getChildren().remove(img);
                           if(entranceChosen.size() >= numOfStudents && cardChosen.size() >= numOfStudents)
                           {
                               client.send(new PayCharacter7Message(cardChosen, entranceChosen));
                           }
                           event.consume();
                       });
                   }
               }
               if(k == 1)
               {
                   for(Node img : player2Entrance.getChildren())
                   {
                       img.setOnMouseClicked((MouseEvent event) ->
                       {
                           entranceChosen.add(player2Entrance.getChildren().indexOf(img) + 1);
                           player2Entrance.getChildren().remove(img);
                           if(entranceChosen.size() >= numOfStudents && cardChosen.size() >= numOfStudents)
                           {
                               client.send(new PayCharacter7Message(cardChosen, entranceChosen));
                           }
                           event.consume();
                       });
                   }
               }
               if(k == 2)
               {
                   for(Node img : player3Entrance.getChildren())
                   {
                       img.setOnMouseClicked((MouseEvent event) ->
                       {
                           entranceChosen.add(player3Entrance.getChildren().indexOf(img) + 1);
                           player3Entrance.getChildren().remove(img);
                           if(entranceChosen.size() >= numOfStudents && cardChosen.size() >= numOfStudents)
                           {
                               client.send(new PayCharacter7Message(cardChosen, entranceChosen));
                           }
                           event.consume();
                       });
                   }
               }
               if(k == 3)
               {
                   for(Node img : player4Entrance.getChildren())
                   {
                       img.setOnMouseClicked((MouseEvent event) ->
                       {
                           entranceChosen.add(player4Entrance.getChildren().indexOf(img) + 1);
                           player4Entrance.getChildren().remove(img);
                           if(entranceChosen.size() >= numOfStudents && cardChosen.size() >= numOfStudents)
                           {
                               client.send(new PayCharacter7Message(cardChosen, entranceChosen));
                           }
                           event.consume();
                       });
                   }
               }
            }
        }

        if(id1 == 7)
        {
            for(Node img : character1MoveStudents.getChildren())
            {
                img.setOnMouseClicked((MouseEvent event) ->
                {
                    cardChosen.add(character1MoveStudents.getChildren().indexOf(img) + 1);
                    character1MoveStudents.getChildren().remove(img);
                    if(entranceChosen.size() >= numOfStudents && cardChosen.size() >= numOfStudents)
                    {
                        client.send(new PayCharacter7Message(cardChosen, entranceChosen));
                    }
                    event.consume();
                });
            }
        }
        if(id2 == 7)
        {
            for(Node img : character2MoveStudents.getChildren())
            {
                img.setOnMouseClicked((MouseEvent event) ->
                {
                    cardChosen.add(character2MoveStudents.getChildren().indexOf(img) + 1);
                    character2MoveStudents.getChildren().remove(img);
                    if(entranceChosen.size() >= numOfStudents && cardChosen.size() >= numOfStudents)
                    {
                        client.send(new PayCharacter7Message(cardChosen, entranceChosen));
                    }
                    event.consume();
                });
            }
        }
        if(id3 == 7)
        {
            for(Node img : character3MoveStudents.getChildren())
            {
                img.setOnMouseClicked((MouseEvent event) ->
                {
                    cardChosen.add(character3MoveStudents.getChildren().indexOf(img) + 1);
                    character3MoveStudents.getChildren().remove(img);
                    if(entranceChosen.size() >= numOfStudents && cardChosen.size() >= numOfStudents)
                    {
                        client.send(new PayCharacter7Message(cardChosen, entranceChosen));
                    }
                    event.consume();
                });
            }
        }
    }


    public void character8()
    {
        client.send(new PayCharacter8Message());
    }

    public void character9()
    {
        AdditionalMovements.setVisible(true);
        AdditionalMovements.toFront();
        AdditionalMovements.setOnKeyPressed((KeyEvent event) ->
        {
            String color = AdditionalMovements.getText().toLowerCase();
            switch (color){
                case "blue":  client.send(new PayCharacter9Message(ColorS.BLUE));
                case "red":  client.send(new PayCharacter9Message(ColorS.RED));
                case "yellow":  client.send(new PayCharacter9Message(ColorS.YELLOW));
                case "green":  client.send(new PayCharacter9Message(ColorS.GREEN));
                case "pink":  client.send(new PayCharacter9Message(ColorS.PINK));
            }
        });

        AdditionalMovements.setVisible(false);
    }

    public void character10()
    {
        AdditionalMovements.setVisible(true);
        AdditionalMovements.toFront();
        AdditionalMovements.setOnAction((ActionEvent event) ->
        {
            numOfStudents = Integer.parseInt(AdditionalMovements.getText());
            AdditionalMovements.setVisible(false);
            event.consume();
        });

        List<Integer> entranceChosen = new ArrayList<>();
        List<ColorS> diningChosen = new ArrayList<>();


        for(int k = 0; k < client.getReducedModel().getBoards().size(); k++)
        {
            if(client.getReducedModel().getBoards().get(k).getPlayer().equals(client.getReducedModel().getCurrentPlayer()))
            {
                if(k == 0)
                {
                    for(Node img : player1Entrance.getChildren())
                    {
                        img.setOnMouseClicked((MouseEvent event) ->
                        {
                            entranceChosen.add(player1Entrance.getChildren().indexOf(img) + 1);
                            player1Entrance.getChildren().remove(img);
                            if(entranceChosen.size() >= numOfStudents && diningChosen.size() >= numOfStudents)
                            {
                                client.send(new PayCharacter10Message(diningChosen, entranceChosen));
                            }
                            event.consume();
                        });
                    }

                    for(Node img : player1DiningRoom.getChildren())
                    {
                        img.setOnMouseClicked((MouseEvent event) ->
                        {
                            ImageView image = (ImageView) img;

                            Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
                            Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
                            Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
                            Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
                            Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

                            if(image.getImage().equals(blue))
                            {
                                diningChosen.add(ColorS.BLUE);
                            }
                            if(image.getImage().equals(red))
                            {
                                diningChosen.add(ColorS.RED);
                            }
                            if(image.getImage().equals(yellow))
                            {
                                diningChosen.add(ColorS.YELLOW);
                            }
                            if(image.getImage().equals(pink))
                            {
                                diningChosen.add(ColorS.PINK);
                            }
                            if(image.getImage().equals(green))
                            {
                                diningChosen.add(ColorS.GREEN);
                            }

                            GridPane.getRowIndex(img);
                            player1Entrance.getChildren().remove(img);
                            if(entranceChosen.size() >= numOfStudents && diningChosen.size() >= numOfStudents)
                            {
                                client.send(new PayCharacter10Message(diningChosen, entranceChosen));
                            }
                            event.consume();
                        });
                    }
                }
                if(k == 1)
                {
                    for(Node img : player2Entrance.getChildren())
                    {
                        img.setOnMouseClicked((MouseEvent event) ->
                        {
                            entranceChosen.add(player2Entrance.getChildren().indexOf(img) + 1);
                            player2Entrance.getChildren().remove(img);
                            if(entranceChosen.size() >= numOfStudents && diningChosen.size() >= numOfStudents)
                            {
                                client.send(new PayCharacter10Message(diningChosen, entranceChosen));
                            }
                            event.consume();
                        });
                    }

                    for(Node img : player2DiningRoom.getChildren())
                    {
                        img.setOnMouseClicked((MouseEvent event) ->
                        {
                            ImageView image = (ImageView) img;

                            Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
                            Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
                            Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
                            Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
                            Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

                            if(image.getImage().equals(blue))
                            {
                                diningChosen.add(ColorS.BLUE);
                            }
                            if(image.getImage().equals(red))
                            {
                                diningChosen.add(ColorS.RED);
                            }
                            if(image.getImage().equals(yellow))
                            {
                                diningChosen.add(ColorS.YELLOW);
                            }
                            if(image.getImage().equals(pink))
                            {
                                diningChosen.add(ColorS.PINK);
                            }
                            if(image.getImage().equals(green))
                            {
                                diningChosen.add(ColorS.GREEN);
                            }

                            player2Entrance.getChildren().remove(img);
                            if(entranceChosen.size() >= numOfStudents && diningChosen.size() >= numOfStudents)
                            {
                                client.send(new PayCharacter10Message(diningChosen, entranceChosen));
                            }
                            event.consume();
                        });
                    }
                }
                if(k == 2)
                {
                    for(Node img : player3Entrance.getChildren())
                    {
                        img.setOnMouseClicked((MouseEvent event) ->
                        {
                            entranceChosen.add(player3Entrance.getChildren().indexOf(img) + 1);
                            player3Entrance.getChildren().remove(img);
                            if(entranceChosen.size() >= numOfStudents && diningChosen.size() >= numOfStudents)
                            {
                                client.send(new PayCharacter10Message(diningChosen, entranceChosen));
                            }
                            event.consume();
                        });
                    }

                    for(Node img : player3DiningRoom.getChildren())
                    {
                        img.setOnMouseClicked((MouseEvent event) ->
                        {
                            ImageView image = (ImageView) img;

                            Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
                            Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
                            Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
                            Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
                            Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

                            if(image.getImage().equals(blue))
                            {
                                diningChosen.add(ColorS.BLUE);
                            }
                            if(image.getImage().equals(red))
                            {
                                diningChosen.add(ColorS.RED);
                            }
                            if(image.getImage().equals(yellow))
                            {
                                diningChosen.add(ColorS.YELLOW);
                            }
                            if(image.getImage().equals(pink))
                            {
                                diningChosen.add(ColorS.PINK);
                            }
                            if(image.getImage().equals(green))
                            {
                                diningChosen.add(ColorS.GREEN);
                            }

                            player3Entrance.getChildren().remove(img);
                            if(entranceChosen.size() >= numOfStudents && diningChosen.size() >= numOfStudents)
                            {
                                client.send(new PayCharacter10Message(diningChosen, entranceChosen));
                            }
                            event.consume();
                        });
                    }
                }
                if(k == 3)
                {
                    for(Node img : player4Entrance.getChildren())
                    {
                        img.setOnMouseClicked((MouseEvent event) ->
                        {
                            entranceChosen.add(player4Entrance.getChildren().indexOf(img) + 1);
                            player4Entrance.getChildren().remove(img);
                            if(entranceChosen.size() >= numOfStudents && diningChosen.size() >= numOfStudents)
                            {
                                client.send(new PayCharacter10Message(diningChosen, entranceChosen));
                            }
                            event.consume();
                        });
                    }

                    for(Node img : player4DiningRoom.getChildren())
                    {
                        img.setOnMouseClicked((MouseEvent event) ->
                        {
                            ImageView image = (ImageView) img;

                            Image blue = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
                            Image red = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
                            Image yellow = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
                            Image pink = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
                            Image green = new Image(String.valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

                            if(image.getImage().equals(blue))
                            {
                                diningChosen.add(ColorS.BLUE);
                            }
                            if(image.getImage().equals(red))
                            {
                                diningChosen.add(ColorS.RED);
                            }
                            if(image.getImage().equals(yellow))
                            {
                                diningChosen.add(ColorS.YELLOW);
                            }
                            if(image.getImage().equals(pink))
                            {
                                diningChosen.add(ColorS.PINK);
                            }
                            if(image.getImage().equals(green))
                            {
                                diningChosen.add(ColorS.GREEN);
                            }

                            player4Entrance.getChildren().remove(img);
                            if(entranceChosen.size() >= numOfStudents && diningChosen.size() >= numOfStudents)
                            {
                                client.send(new PayCharacter10Message(diningChosen, entranceChosen));
                            }
                            event.consume();
                        });
                    }
                }
            }
        }
    }

    public void character11()
    {
        if(id1 == 11)
        {
            for(Node img : character1MoveStudents.getChildren())
            {
                img.setOnMouseClicked((MouseEvent event) ->
                {
                    client.send(new PayCharacter11Message(character1MoveStudents.getChildren().indexOf(img) + 1));
                    character1MoveStudents.getChildren().remove(img);
                    event.consume();
                });
            }
        }
        if(id2 == 11)
        {
            for(Node img : character2MoveStudents.getChildren())
            {
                img.setOnMouseClicked((MouseEvent event) ->
                {
                    client.send(new PayCharacter11Message(character2MoveStudents.getChildren().indexOf(img) + 1));
                    character2MoveStudents.getChildren().remove(img);
                    event.consume();
                });
            }
        }
        if(id3 == 11)
        {
            for(Node img : character3MoveStudents.getChildren())
            {
                img.setOnMouseClicked((MouseEvent event) ->
                {
                    client.send(new PayCharacter11Message(character3MoveStudents.getChildren().indexOf(img) + 1));
                    character3MoveStudents.getChildren().remove(img);
                    event.consume();
                });
            }
        }
    }

    public void character12()
    {
        AdditionalMovements.setVisible(true);
        AdditionalMovements.toFront();
        AdditionalMovements.setOnKeyPressed((KeyEvent event) ->
        {
            String color = AdditionalMovements.getText().toLowerCase();
            switch (color){
                case "blue":  client.send(new PayCharacter12Message(ColorS.BLUE));
                case "red":  client.send(new PayCharacter12Message(ColorS.RED));
                case "yellow":  client.send(new PayCharacter12Message(ColorS.YELLOW));
                case "green":  client.send(new PayCharacter12Message(ColorS.GREEN));
                case "pink":  client.send(new PayCharacter12Message(ColorS.PINK));
            }
        });

        AdditionalMovements.setVisible(false);
    }


    public void showModel()
    {
        showIslands();
        showIslandStudents();
        showClouds();
        showCloudsStudents();
        showSchoolBoards();
        showCoins();
        showPlayedAssistant();
        showCharacters();
    }

    //ISLANDS

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


    //CLOUDS

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

    // SCHOOLBOARDS

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

    // ENTRANCES
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
                    count++;
                }
            }
        }
        player1Entrance.toFront();

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
                    count++;
                }
            }
        }
        player2Entrance.toFront();

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
                        count++;
                    }
                }
            }
            player4Entrance.toFront();
        }
    }

    // DINING ROOM

    public void showDiningRoom() {
        Image blue = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_blue.png")));
        Image red = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_red.png")));
        Image yellow = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_yellow.png")));
        Image pink = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_pink.png")));
        Image green = new Image(valueOf(getClass().getResource("/img/Plancia/Studenti/student_green.png")));

        //PLAYER1
        for (int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.GREEN); i++) {
            player1DiningRoom.add(setStudentsDimension(new ImageView(green)), i, 0);
        }
        for (int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.RED); i++) {
            player1DiningRoom.add(setStudentsDimension(new ImageView(red)), i, 1);
        }
        for (int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.YELLOW); i++) {
            player1DiningRoom.add(setStudentsDimension(new ImageView(yellow)), i, 2);
        }
        for (int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.PINK); i++) {
            player1DiningRoom.add(setStudentsDimension(new ImageView(pink)), i, 3);
        }
        for (int i = 0; i < client.getReducedModel().getBoards().get(0).getStudents().get(ColorS.BLUE); i++) {
            player1DiningRoom.add(setStudentsDimension(new ImageView(blue)), i, 4);
        }
        player1DiningRoom.toFront();

        //PLAYER2
        for (int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.GREEN); i++) {
            player2DiningRoom.add(setStudentsDimension(new ImageView(green)), i, 0);
        }
        for (int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.RED); i++) {
            player2DiningRoom.add(setStudentsDimension(new ImageView(red)), i, 1);
        }
        for (int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.YELLOW); i++) {
            player2DiningRoom.add(setStudentsDimension(new ImageView(yellow)), i, 2);
        }
        for (int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.PINK); i++) {
            player2DiningRoom.add(setStudentsDimension(new ImageView(pink)), i, 3);
        }
        for (int i = 0; i < client.getReducedModel().getBoards().get(1).getStudents().get(ColorS.BLUE); i++) {
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

    //PROFESSOR TABLE
    public void showProfessorTable() {
        Image blue = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_blue.png")));
        Image red = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_red.png")));
        Image yellow = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_yellow.png")));
        Image pink = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_pink.png")));
        Image green = new Image(valueOf(getClass().getResource("/img/Plancia/Professori/teacher_green.png")));
        String color;

        //PLAYER1
        for (ColorS s : client.getReducedModel().getBoards().get(0).getProfessors()) {
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
        for (ColorS s : client.getReducedModel().getBoards().get(1).getProfessors()) {
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
            for (ColorS s : client.getReducedModel().getBoards().get(2).getProfessors()) {
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
            for (ColorS s : client.getReducedModel().getBoards().get(3).getProfessors()) {
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

   // TOWER BOARD

    public ImageView setTowerDimension(ImageView view)
    {
        view.setFitWidth(40);
        view.setFitHeight(70);
        return view;
    }

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
        coins.add(player1Coins);
        coins.add(player2Coins);
        coins.add(player3Coins);
        coins.add(player4Coins);

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
            }
        }
    }

    public void showCharacters()
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
