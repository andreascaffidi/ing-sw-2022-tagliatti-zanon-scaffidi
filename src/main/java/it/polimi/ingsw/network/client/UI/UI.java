package it.polimi.ingsw.network.client.UI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;

public interface UI {

    AbstractClientState getClientState(ClientState clientState, Client client);
}