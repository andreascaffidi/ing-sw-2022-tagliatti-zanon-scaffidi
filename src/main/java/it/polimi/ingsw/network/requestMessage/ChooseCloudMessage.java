package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.ControllerExecute;

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
        controller.chooseCloud(this,username);
    }
}
