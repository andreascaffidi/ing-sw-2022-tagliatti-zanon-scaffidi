package it.polimi.ingsw.network.responses;

import it.polimi.ingsw.network.client.Client;

import java.net.MalformedURLException;

public interface ClientExecute {
    void execute(Client client) throws MalformedURLException;
}
