package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.server.Lobby;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class ShowLobbiesSceneController extends AbstractController  implements Initializable {

    @FXML
    private Label myLabel;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private Button enter;

    private List<String> lobbies;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Lobby> availableLobbies = client.getAvailableLobbies();
            for(Lobby l: availableLobbies)
            {
                lobbies.add(l.getHost() + ": " + l.getGameMode() + " MODE " + l.getNumOfConnection() + "/" + l.getNumOfPlayers());
            }
        myChoiceBox.getItems().addAll(lobbies);
    }

    public void enter(ActionEvent event) throws IOException
    {
        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\WaitingScene.fxml").toUri().toURL();
        root = FXMLLoader.load(url);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
