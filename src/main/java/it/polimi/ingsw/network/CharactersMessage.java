package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.ControllerExpertMode;

import java.io.Serializable;

public interface CharactersMessage extends Serializable {

    void execute(ControllerExpertMode controller, String username);

}
