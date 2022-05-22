package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.UI.GUI.scenesController.SceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WelcomeSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.net.ContentHandler;
import java.net.URL;
import java.nio.file.Paths;

public class JavaFXGUI extends Application {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static void main(String[] args)
    {
        launch(args);
    }

    private static Stage primaryStage;
    private static Scene primaryScene;

    private static final Pane mainRoot = new StackPane();
    private static final Pane overlayRoot = new StackPane();

    private static final Object sceneLock = new Object();
    private static boolean initialized = false;
    static Runnable onExit;


    @Override
    public void start(Stage stage) throws Exception {

        System.out.println("avviato");
        synchronized (sceneLock) {
            primaryStage = stage;

            stage.setTitle("Eryantis");

            //stage = new Stage();
            URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\WelcomeScene.fxml").toUri().toURL();
            root = FXMLLoader.load(url);

            //overlayRoot.setMouseTransparent(true);
            //((StackPane)overlayRoot).setAlignment(Pos.CENTER);
            //mainRoot.setId("root");

            //mainRoot.setCache(true);
            //mainRoot.setCacheHint(CacheHint.SPEED);
            //overlayRoot.setCache(true);
            //overlayRoot.setCacheHint(CacheHint.SPEED);

            FileInputStream inputstream = new FileInputStream("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\resources\\img\\Eriantys.png");
            Image image = new Image(inputstream);
            stage.getIcons().add(image);

            primaryScene = new Scene(root, 1280, 720);

            stage.setScene(primaryScene);
            stage.setFullScreen(false);
            initialized = true;
            sceneLock.notifyAll();
        }
        stage.show();
    }

    /**
     * This method sets the main root of the scene
     * @param newRoot the new root of the scene
     */
    public static void setMainRoot(Pane newRoot){
        mainRoot.getChildren().clear();
        mainRoot.getChildren().add(newRoot);
    }

    /**
     * This method retrieves the main root of the scene
     * @return the main root of the scene
     */
    public static Pane getMainRoot() {
        return mainRoot;
    }

    /**
     * This method sets the root for the overlay
     * @param newRoot the new root for the overlay
     */
    public static void setOverlayRoot(Pane newRoot){
        overlayRoot.getChildren().clear();
        overlayRoot.getChildren().add(newRoot);
    }

    /**
     * This method retrieves the overlay root of the scene
     * @return the overlay root
     */
    public static Pane getOverlayRoot() {
        return overlayRoot;
    }

    /**
     * This method retrieves the Scene instance
     * @return the Scene instance
     */
    public static Scene getPrimaryScene(){
        synchronized(sceneLock){
            while (!initialized) {
                try {
                    sceneLock.wait();
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            return primaryScene;
        }
    }

    /**
     * This method retrieves the Stage instance
     * @return the Stage instance
     */
    public static Stage getPrimaryStage(){
        synchronized(sceneLock){
            while (!initialized) {
                try {
                    sceneLock.wait();
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            return primaryStage;
        }
    }

}
