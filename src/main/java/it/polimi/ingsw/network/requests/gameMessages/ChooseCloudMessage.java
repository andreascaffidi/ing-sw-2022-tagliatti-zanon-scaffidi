package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

public class ChooseCloudMessage implements RequestMessage, ControllerExecute {
    private int id;

    public ChooseCloudMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    @Override
    public void execute(Controller controller, String username) {
        controller.chooseCloud(this, username);
    }
}
