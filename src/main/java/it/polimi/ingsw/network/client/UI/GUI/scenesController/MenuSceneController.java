package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.UI.GUI.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class MenuSceneController extends AbstractController{

    @FXML
    public Button joinButton;
    @FXML
    public Button createButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void create(ActionEvent event) throws IOException {

        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\CreateLobbyScene.fxml").toUri().toURL();
        root = FXMLLoader.load(url);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void join(ActionEvent event) throws IOException
    {
        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\ShowLobbiesScene.fxml").toUri().toURL();
        root = FXMLLoader.load(url);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
