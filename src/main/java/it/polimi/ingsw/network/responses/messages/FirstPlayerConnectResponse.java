package it.polimi.ingsw.network.responses.messages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class FirstPlayerConnectResponse implements ResponseMessage {

    private final String message = "Welcome to Eryantis! You are the first player of the match; Please choose the number of players of this game [2-4]: ";

    @Override
    public void execute(Client client) {
        client.getUi().print(message);
        client.getUi().askPlayersNumber();
    }
}
