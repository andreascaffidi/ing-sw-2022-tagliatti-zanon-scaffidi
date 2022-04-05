package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.ControllerExpertMode;
import it.polimi.ingsw.network.CharactersMessage;
import it.polimi.ingsw.network.TypeOfMessage;

public class PayCharacterMessage implements CharactersMessage {
    private int id;

    public PayCharacterMessage(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void execute(ControllerExpertMode controller, String username)
    {
        controller.payCharacter(this, username);
    }
}
