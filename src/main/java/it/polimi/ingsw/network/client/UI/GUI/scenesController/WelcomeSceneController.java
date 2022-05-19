package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import javafx.scene.control.TextField;

public class WelcomeSceneController extends AbstractController{

    @FXML
    TextField NickNameTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException {

        String username = NickNameTextField.getText();

        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\MenuScene.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();

        MenuSceneController menuSceneController = loader.getController();
        menuSceneController.displayName(username);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
