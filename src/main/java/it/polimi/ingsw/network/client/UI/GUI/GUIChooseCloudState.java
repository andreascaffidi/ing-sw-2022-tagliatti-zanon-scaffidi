package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import java.net.URL;

/**
 * GUI choose cloud state class
 */
public class GUIChooseCloudState extends AbstractClientState {
    private final Client client;

    /**
     * builds a GUI choose cloud state
     * @param client client
     */
    public GUIChooseCloudState(Client client){
        this.client = client;
    }

    /**
     * loads the ChooseCloud scene (fxml file)
     */
    @Override
    public void render(){
        URL url = getClass().getResource("/fxml/ChooseCloudScene.fxml");
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

    /**
     * manages a disconnection error
     * @param message error message
     */
    @Override
    public void disconnectionError(String message){
        ((GUI)client.getUI()).getSceneController().disconnectClient(message);
    }
}
