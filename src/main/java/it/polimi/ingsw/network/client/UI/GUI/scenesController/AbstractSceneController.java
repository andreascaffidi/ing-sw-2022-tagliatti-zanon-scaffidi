package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.Client;

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

}
