package it.polimi.ingsw.network.responses.reducedModelMessage;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

/**
 * character paid message
 */
public class CharacterPaidMessage implements ResponseMessage, ClientExecute {
    private final ReducedModel reducedModel;
    private final int character;

    /**
     * builds a character paid message
     * @param reducedModel reduced model
     * @param character character paid
     */
    public CharacterPaidMessage(ReducedModel reducedModel, int character){
        this.reducedModel = reducedModel;
        this.character = character;
    }

    /**
     * implements ClientExecute interface
     * sets the reduced model to clients, shows it and sets the waiting message to all clients that aren't the current
     * player. At the end executes changeState() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client) {
        client.setReducedModel(reducedModel);
        if(!reducedModel.getCurrentPlayer().equals(client.getUsername()))
        {
            client.setWaitingMessage(reducedModel.getCurrentPlayer() + " has payed " + character + " card.\n"
                    + "It's " + reducedModel.getCurrentPlayer() + " turn, waiting for yours...");
            client.changeState(ClientState.WAITING);
        }else {
            if (client.getUI() instanceof GUI){
                client.changeState(client.getBackState());
                client.getCurrentState().serverError("You have correctly payed " + character + " card");
            } else {
                System.out.println("You have correctly payed " + character + " card");
                client.changeState(client.getBackState());
            }
        }
    }
}
