package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.UI.GUI.scenesController.SceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WelcomeSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
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

    @Override
    public void start(Stage stage) {
        try{
            //stage = new Stage();
            URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\WelcomeScene.fxml").toUri().toURL();
            root = FXMLLoader.load(url);
            scene = new Scene(root);

            stage.setScene(scene);

            stage.setTitle("Eryantis");
            stage.setWidth(420);
            stage.setHeight(420);

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
