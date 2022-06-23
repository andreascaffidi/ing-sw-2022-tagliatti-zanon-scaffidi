package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import java.net.URL;

/**
 * GUI welcome state class
 */
public class GUIWelcomeState extends AbstractClientState {

    /**
     * builds a GUI welcome state
     * @param client client
     */
    public GUIWelcomeState(Client client) {
        this.client = client;
    }

    /**
     * loads the welcome scene (fxml file)
     */
    @Override
    public void render() {
        JavaFXGUI.waitForStartingGUI();
        URL url = getClass().getResource("/fxml/WelcomeScene.fxml");
        ((GUI) client.getUI()).loadScene(url, client);
    }

    /**
     * manages a server error
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        ((GUI)client.getUI()).getSceneController().alert(message);
    }
}
