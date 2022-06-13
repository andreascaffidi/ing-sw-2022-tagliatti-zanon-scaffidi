package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WelcomeSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

/**
 * GUI welcome state class
 */
public class GUIWelcomeState extends AbstractClientState {

    private WelcomeSceneController welcomeSceneController;

    /**
     * builds a GUI welcome state
     * @param client client
     */
    public GUIWelcomeState(Client client){
        this.client = client;
    }

    /**
     * loads the welcome scene (fxml file)
     */
    @Override
    public void render() {
        JavaFXGUI.waitForStartingGUI();
        URL url = getClass().getResource("/fxml/WelcomeScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            Parent root = loader.load();
            welcomeSceneController = loader.getController();
            welcomeSceneController.setClient(client);
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * manages a server error
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        welcomeSceneController.alert(message);
    }
}
