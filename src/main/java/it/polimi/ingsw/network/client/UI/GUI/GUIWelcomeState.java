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

public class GUIWelcomeState extends AbstractClientState {

    private Parent root;
    private final Client client;

    public GUIWelcomeState(Client client){
        this.client = client;
    }

    @Override
    public void render() {
        JavaFXGUI.getPrimaryScene();
        URL url = getClass().getResource("/fxml/WelcomeScene.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            root = loader.load();
            WelcomeSceneController welcomeSceneController = loader.getController();
            welcomeSceneController.setClient(client);
            Platform.runLater(() ->JavaFXGUI.setMainPane((Pane)root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void serverError(String message) {
        //TODO: capire come gestire alert di errore
    }
}
