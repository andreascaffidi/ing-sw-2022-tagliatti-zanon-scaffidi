package it.polimi.ingsw.network.client.UI.GUI.scenesController;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;

public class AbstractController {

    Client client;
    AbstractClientState state;

    /**
     * This method sets the client in the controller
     * @param client the Client instance
     */
    public void setClient(Client client){
        this.client = client;
    }

    /**
     * This method sets the ClientState in the controller
     * @param state the AbstractClientState instance
     */
    public void setState(AbstractClientState state){
        this.state = state;
    }

    /**
     * Method to be overridden if there is the need to do some operations after the FXML is loaded but before
     * the scene is shown to the user. This gets called once, after FXML load
     */
    public void setupController(){ }

    /**
     * Method to be overridden if there is the need to do some operations only the first time the scene is loaded,
     * both from FXML or, if the scene has already been loaded once, from the client cache
     */
    public void onSceneShow() { }
}
