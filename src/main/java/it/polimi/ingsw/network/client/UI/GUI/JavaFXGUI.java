package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.UI.GUI.scenesController.WelcomeSceneController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.logging.Logger;

public class JavaFXGUI extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            Group root = new Group();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 600, 600);

            /*Text text = new Text();
            text.setText("Ciao");
            text.setX(50);
            text.setY(50);
            //text.setFont(Font.font("Verdana", 50));

            root.getChildren().add(text);*/

            stage.setScene(scene);

            stage.setTitle("Eryantis");
            stage.setWidth(420);
            stage.setHeight(420);

            //primaryStage.setX(50);
            //primaryStage.setY(50);
            //scene.getStylesheets().add(getClass().getResource("application.css"));


            FileInputStream inputstream = new FileInputStream("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\resources\\img\\Eriantys.png");
            Image image = new Image(inputstream);
            stage.getIcons().add(image);

            stage.setFullScreen(true);
            stage.setFullScreenExitHint("PRESS q TO EXIT FROM FULL SCREEN");
            stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
