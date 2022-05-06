package it.polimi.ingsw.network.client.states;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;

public abstract class AbstractMoveStudentsState extends AbstractClientState{

    protected String studentColor;
    protected int islandId;
    protected String destination;

    @Override
    public void notifyFromUI(Client client) {
        //client.send(new MoveStudentMessage(destination, studentColor, islandId));
    }
}
