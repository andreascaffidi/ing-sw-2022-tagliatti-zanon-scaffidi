package it.polimi.ingsw.network.client.UI.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;


public class JavaFXGUI extends Application {

    private static Stage primaryStage;
    private static Scene primaryScene;

    private static final Pane mainPane = new StackPane();
    private static final Pane overlayPane = new StackPane();

    private static final Object lock = new Object();
    private static boolean initialized = false;

    public static void launchJavaFX() {
        launch();
    }

    @Override
    public void start(Stage stage){
        synchronized (lock) {
            //primary stage is the main window
            primaryStage = stage;
            primaryStage.setTitle("Eriantys");
            URL imagePath = this.getClass().getResource("/img/Eriantys.png");
            if (imagePath != null) {
                Image image = new Image(imagePath.toString());
                stage.getIcons().add(image);
            }

            //overlay root is the external layer of the scene
            overlayPane.setMouseTransparent(true);
            ((StackPane) overlayPane).setAlignment(Pos.CENTER);

            //main root is the root layer of the scene
            mainPane.setId("root");
            mainPane.setCache(true);
            mainPane.setCacheHint(CacheHint.SPEED);
            overlayPane.setCache(true);
            overlayPane.setCacheHint(CacheHint.SPEED);

            //create a Pane composed by a main root and an overlay root (they are panes too)
            Pane pane = new StackPane(mainPane, overlayPane);

            //the first scene loaded is a black screen
            primaryScene = new Scene(pane, 1280, 720, Color.BLACK);
            primaryStage.setScene(primaryScene);
            primaryStage.setFullScreen(false);
            initialized = true;
            lock.notifyAll();
        }

        primaryStage.show();
    }

    public static void setMainPane(Pane pane){
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }

    public static void setOverlayPane(Pane pane){
        overlayPane.getChildren().clear();
        overlayPane.getChildren().add(pane);
    }

    public static void waitForStartingGUI(){
        synchronized(lock){
            while (!initialized) {
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //TODO: disconnect client
    }
}
