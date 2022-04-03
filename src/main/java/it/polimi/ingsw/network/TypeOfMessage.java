package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;

import java.io.Serializable;

public interface TypeOfMessage extends Serializable {
    void execute(Controller controller, String username);
}
