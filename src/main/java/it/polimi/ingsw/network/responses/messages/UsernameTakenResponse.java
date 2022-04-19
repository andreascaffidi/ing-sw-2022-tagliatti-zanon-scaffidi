package it.polimi.ingsw.network.responses.messages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class UsernameTakenResponse implements ResponseMessage {

    private final String message = "This username is already taken.";

    @Override
    public void execute(Client client) {
        client.getUi().print(message);
        client.getUi().askUsername();
    }
}
