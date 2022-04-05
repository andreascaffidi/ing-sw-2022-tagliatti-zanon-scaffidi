package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;

public class PayCharacter3Message implements CharactersMessage {

    private int islandId;

    public PayCharacter3Message(int islandId) {
        islandId = islandId;
    }

    public int getIslandId() {
        return islandId;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username)
    {
        controller.payCharacter3(this, username);
    }
}
