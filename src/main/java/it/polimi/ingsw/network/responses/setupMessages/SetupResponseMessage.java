package it.polimi.ingsw.network.responses.setupMessages;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;


/**
 * setup response message
 */
public class SetupResponseMessage implements ResponseMessage, ClientExecute {
    private final ClientState clientState;

    /**
     * builds a setup response message
     * @param clientState client state to set
     */
    public SetupResponseMessage(ClientState clientState){
        this.clientState = clientState;
    }

    /**
     * implements ClientExecute interface
     * executes changeState() method on Client class
     * @param client client
     */
    @Override
    public void execute(Client client) {
        client.changeState(clientState);
    }
}
