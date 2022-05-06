package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.requests.gameMessages.MoveMotherNatureMessage;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;

public abstract class AbstractMoveMotherNatureState extends AbstractClientState{

    protected int id;

    //TODO: creare il messaggio
    @Override
    public void notifyFromUI(Client client) {
        client.send(new MoveMotherNatureMessage(id));
    }
}
