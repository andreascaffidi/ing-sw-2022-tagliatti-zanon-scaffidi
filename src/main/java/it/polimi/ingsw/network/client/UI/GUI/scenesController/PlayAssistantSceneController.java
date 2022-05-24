package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedAssistant;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayAssistantSceneController extends AbstractSceneController {

    @FXML
    Button PlayCharacter;

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

    /**
     * List of all available cards. Loaded once at game start.
     */
    List<Button> deck = new ArrayList<>();
    List<Integer> possibleChoices = null;

    /**
     * Cards to be dealt
     */
    @FXML
    Pane stockPane;

    /**
     * Cards which are already dealt
     */
    @FXML
    FlowPane tableauPane;

    @Override
    public void setup() {

        ReducedBoard myBoard = client.getReducedModel().getBoards().stream()
                .filter(b -> b.getPlayer().equals(client.getUsername()))
                .findFirst().orElse(null);

        if (myBoard != null){
            possibleChoices = myBoard.getAssistantDeck().getAssistantCards().stream()
                    .map(ReducedAssistant::getId).collect(Collectors.toList());
        }

        Image img1 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(1).png")));
        ImageView view1 = new ImageView(img1);
        view1.setFitWidth(50);
        view1.setPreserveRatio(true);
        Assistant1.setGraphic(view1);
        if(possibleChoices.contains(1))
        {
            deck.add(Assistant1);
            Assistant1.setOnAction(e -> {
                        tableauPane.getChildren().remove(Assistant1);
                        stockPane.getChildren().add(Assistant1);
                        client.send(new PlayAssistantMessage(1)); });
        }

        Image img2 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(2).png")));
        ImageView view2 = new ImageView(img2);
        view2.setFitWidth(50);
        view2.setPreserveRatio(true);
        Assistant2.setGraphic(view2);
        if(possibleChoices.contains(2))
        {
            deck.add(Assistant2);
            Assistant2.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant2);
                stockPane.getChildren().add(Assistant2);
                client.send(new PlayAssistantMessage(2));
            });
        }

        Image img3 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(3).png")));
        ImageView view3 = new ImageView(img3);
        view3.setFitWidth(50);
        view3.setPreserveRatio(true);
        Assistant3.setGraphic(view3);
        if(possibleChoices.contains(3)) {
            deck.add(Assistant3);
            Assistant3.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant3);
                stockPane.getChildren().add(Assistant3);
                client.send(new PlayAssistantMessage(3));
            });
        }

        Image img4 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(4).png")));
        ImageView view4 = new ImageView(img4);
        view4.setFitWidth(50);
        view4.setPreserveRatio(true);
        Assistant4.setGraphic(view4);
        if(possibleChoices.contains(4)) {
            deck.add(Assistant4);
            Assistant4.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant4);
                stockPane.getChildren().add(Assistant4);
                client.send(new PlayAssistantMessage(4));
            });
        }

        Image img5 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(5).png")));
        ImageView view5 = new ImageView(img5);
        view5.setFitWidth(50);
        view5.setPreserveRatio(true);
        Assistant5.setGraphic(view5);
        if(possibleChoices.contains(5)) {
            deck.add(Assistant5);
            Assistant5.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant5);
                stockPane.getChildren().add(Assistant5);
                client.send(new PlayAssistantMessage(5));
            });
        }

        Image img6 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(6).png")));
        ImageView view6 = new ImageView(img6);
        view6.setFitWidth(50);
        view6.setPreserveRatio(true);
        Assistant6.setGraphic(view6);
        if(possibleChoices.contains(6)) {
            deck.add(Assistant6);
            Assistant6.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant6);
                stockPane.getChildren().add(Assistant6);
                client.send(new PlayAssistantMessage(6));
            });
        }

        Image img7 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(7).png")));
        ImageView view7 = new ImageView(img7);
        view7.setFitWidth(50);
        view7.setPreserveRatio(true);
        Assistant7.setGraphic(view7);
        if(possibleChoices.contains(7)) {
            deck.add(Assistant7);
            Assistant7.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant7);
                stockPane.getChildren().add(Assistant7);
                client.send(new PlayAssistantMessage(7));
            });
        }

        Image img8 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(8).png")));
        ImageView view8 = new ImageView(img8);
        view8.setFitWidth(50);
        view8.setPreserveRatio(true);
        Assistant8.setGraphic(view8);
        if(possibleChoices.contains(8)) {
            deck.add(Assistant8);
            Assistant8.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant8);
                stockPane.getChildren().add(Assistant8);
                client.send(new PlayAssistantMessage(8));
            });
        }

        Image img9 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(9).png")));
        ImageView view9 = new ImageView(img9);
        view9.setFitWidth(50);
        view9.setPreserveRatio(true);
        Assistant9.setGraphic(view9);
        if(possibleChoices.contains(9)) {
            deck.add(Assistant9);
            Assistant9.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant9);
                stockPane.getChildren().add(Assistant9);
                client.send(new PlayAssistantMessage(9));
            });
        }

        Image img10 = new Image(String.valueOf(getClass().getResource("/img/Assistenti/Assistente(10).png")));
        ImageView view10 = new ImageView(img10);
        view10.setFitWidth(50);
        view10.setPreserveRatio(true);
        Assistant10.setGraphic(view10);
        if(possibleChoices.contains(10)) {
            deck.add(Assistant10);
            Assistant10.setOnAction(e -> {
                tableauPane.getChildren().remove(Assistant10);
                stockPane.getChildren().add(Assistant10);
                client.send(new PlayAssistantMessage(10));
            });
        }

        // put cards on stock pane
        for( int i=0; i < deck.size(); i++) {

            // get card from stock
            Button card = deck.get(i);

            // set card position
            card.setLayoutX(i * 20);

            // put card on tableau pane
            tableauPane.getChildren().add(card);
        }
    }

    public void showModel()
    {
        showIslands();
        showClouds();
        showSchoolBoards();
        showCoins();
    }

    public void showIslands()
    {

    }

    public void showClouds()
    {

    }

    public void showSchoolBoards()
    {

    }

    public void showCoins()
    {

    }
}
