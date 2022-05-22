package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class CreateLobbySceneController extends AbstractController{

    @FXML
    private Button expert;

    @FXML
    private Button normal;

    @FXML
    private Button two;

    @FXML
    private Button tree;

    @FXML
    private Button four;

    @FXML
    private Button enter;

    private String mode;
    private int num;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void enter(ActionEvent event) throws IOException
    {
        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\WaitingScene.fxml").toUri().toURL();
        root = FXMLLoader.load(url);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void expert(ActionEvent event)
    {
        this.mode = "expert";
    }

    public void normal(ActionEvent event)
    {
        this.mode = "normal";
    }

    public void two(ActionEvent event)
    {
        this.num = 2;
    }

    public void tree(ActionEvent event)
    {
        this.num = 3;
    }

    public void four(ActionEvent event)
    {
        this.num = 4;
    }

    public int getNum() {
        return num;
    }

    public String getMode() {
        return mode;
    }
}
