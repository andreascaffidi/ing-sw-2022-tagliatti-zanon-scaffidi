package it.polimi.ingsw.network.responses.messages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class NotYourTurnResponse implements ResponseMessage {

    private final String message = "It's not your turn!";

    @Override
    public void execute(Client controller) {

    }
}
