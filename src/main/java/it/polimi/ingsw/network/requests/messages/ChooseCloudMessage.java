package it.polimi.ingsw.network.requests.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.view.View;

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
