package it.polimi.ingsw.network.responses;

import it.polimi.ingsw.network.client.Client;

import java.io.Serializable;

public interface ResponseMessage extends Serializable {
    void execute(Client client);
}

