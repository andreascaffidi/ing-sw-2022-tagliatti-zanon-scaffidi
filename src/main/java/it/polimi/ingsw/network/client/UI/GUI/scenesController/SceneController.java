package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public SceneController(Stage stage, Scene scene, Parent root)
    {
        this.stage = stage;
        this.scene = scene;
        this.root = root;
    }

    public void switchToWelcomeScene(ActionEvent event) throws IOException {
        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\WelcomeScene.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenuScene(ActionEvent event) throws IOException {
        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\MenuScene.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}