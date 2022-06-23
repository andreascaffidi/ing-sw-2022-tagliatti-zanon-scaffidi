package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.Client;

/**
 * abstract scene controller (GUI)
 */
public class AbstractSceneController {

    protected Client client;

    /**
     * sets the client in the scene controller
     * @param client client
     */
    public void setClient(Client client){
        this.client = client;
    }

    /**
     * sets up the controller if necessary
     */
    public void setup(){ }

    /**
     * shows an alert message
     * @param message message
     */
    public void alert(String message){ }

    /**
     * shows a disabled scene when a player is disconnected
     */
    public void disconnectClient(String message){ }
}
