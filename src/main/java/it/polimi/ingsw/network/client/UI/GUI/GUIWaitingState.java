package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import java.net.URL;

/**
 * GUI waiting state class
 */
public class GUIWaitingState extends AbstractClientState {
    private final Client client;

    /**
     * builds a GUI waiting state
     * @param client client
     */
    public GUIWaitingState(Client client){
        this.client = client;
    }

    /**
     * loads the waiting scene (fxml file)
     */
    @Override
    public void render(){
        URL url = getClass().getResource("/fxml/WaitingScene.fxml");
        ((GUI) client.getUI()).loadScene(url, client);
    }

    /**
     * manages a server error
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        //do nothing
    }

    /**
     * manages a disconnection error
     * @param message error message
     */
    @Override
    public void disconnectionError(String message){
        ((GUI)client.getUI()).getSceneController().disconnectClient(message);
    }
}
