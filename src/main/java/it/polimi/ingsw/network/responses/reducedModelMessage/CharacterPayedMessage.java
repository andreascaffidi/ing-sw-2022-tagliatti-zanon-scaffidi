package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.CLI.CLI;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class CharacterPayedMessage  implements ResponseMessage, ClientExecute {
    private final ReducedModel reducedModel;
    private final int character;

    public CharacterPayedMessage(ReducedModel reducedModel, int character){
        this.reducedModel = reducedModel;
        this.character = character;
    }

    @Override
    public void execute(Client client) {
        client.setReducedModel(reducedModel);
        if(!reducedModel.getCurrentPlayer().equals(client.getUsername()))
        {
            System.out.println(reducedModel.getCurrentPlayer() + " has payed " + character + " card ");
            CLI.showModel(reducedModel);
            client.changeState(ClientState.WAITING);
            client.setWaitingMessage("It's " + reducedModel.getCurrentPlayer() + " turn, waiting for yours...");
        }else {
            System.out.println("You have correctly payed " + character + " card");
            client.changeState(client.getBackState());
        }
    }
}
