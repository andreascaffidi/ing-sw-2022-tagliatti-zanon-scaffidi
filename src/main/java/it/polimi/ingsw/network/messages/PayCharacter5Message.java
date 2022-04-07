package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter5Message implements CharactersMessage {

    private int islandId;

    public PayCharacter5Message(int islandId) {
        this.islandId = islandId;
    }

    public int getIslandId() {
        return islandId;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username) {
        controller.payCharacter5(this, username);
    }
}
