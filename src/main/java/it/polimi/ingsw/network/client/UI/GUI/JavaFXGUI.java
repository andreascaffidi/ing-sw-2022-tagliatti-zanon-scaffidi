package it.polimi.ingsw.network.client.UI.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
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
            Scene scene = new Scene(root, 400, 400);
            //scene.getStylesheets().add(getClass().getResource("application.css"));
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
