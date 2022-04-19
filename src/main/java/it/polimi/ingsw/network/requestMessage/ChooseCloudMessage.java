package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.ControllerExecute;
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
    public void execute(Controller controller, String username, View view) {
        controller.chooseCloud(this, username, view);
    }
}
