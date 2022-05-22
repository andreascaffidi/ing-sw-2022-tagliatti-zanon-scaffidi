package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.MenuSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WelcomeSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class GUIWelcomeState extends AbstractClientState {


    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Stage primaryStage;
    private static Scene primaryScene;

    private static final Pane mainRoot = new StackPane();
    private static final Pane overlayRoot = new StackPane();

    private static final Object sceneLock = new Object();
    private static boolean initialized = false;
    static Runnable onExit;
    private Client client;
    private Scanner in;
    private String username;

    public GUIWelcomeState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render() throws IOException {
        this.askUsername();
    }

    private void askUsername() throws MalformedURLException {

        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\WelcomeScene.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);

        WelcomeSceneController welcomeSceneController = loader.getController();
        username = welcomeSceneController.getUsername();

        client.setUsername(username.toLowerCase());
        client.send(new SetupRequestMessage(SetupResponsesTypes.USERNAME, username));
    }
}
