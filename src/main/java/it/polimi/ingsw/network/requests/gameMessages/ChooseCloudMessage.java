package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

/**
 * game message choose cloud
 */
public class ChooseCloudMessage implements RequestMessage, ControllerExecute {
    private final int id;

    /**
     * builds choose cloud message
     * @param id cloud id
     */
    public ChooseCloudMessage(int id) {
        this.id = id;
    }

    /**
     * gets cloud id
     * @return cloud id
     */
    public int getId() {
        return this.id;
    }

    /**
     * executes controller method choose cloud
     * @param controller game controller
     * @param username of the player that chooses the cloud
     */
    @Override
    public void execute(Controller controller, String username) {
        controller.chooseCloud(this, username);
    }
}
