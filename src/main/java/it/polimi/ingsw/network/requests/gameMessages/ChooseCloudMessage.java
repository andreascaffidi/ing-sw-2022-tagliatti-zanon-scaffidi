package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

/**
 * game message to choose a cloud
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
     */
    @Override
    public void execute(Controller controller) {
        controller.chooseCloud(this);
    }
}
