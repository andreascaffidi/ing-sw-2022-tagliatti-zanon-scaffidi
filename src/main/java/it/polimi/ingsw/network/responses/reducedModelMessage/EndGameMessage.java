package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class EndGameMessage implements ResponseMessage, ClientExecute {
    private String winner;

    public EndGameMessage(String winner){
        this.winner = winner;
    }

    @Override
    public void execute(Client client) {
        client.setWinner(winner);
        client.changeState(ClientState.END_GAME);
    }
}
