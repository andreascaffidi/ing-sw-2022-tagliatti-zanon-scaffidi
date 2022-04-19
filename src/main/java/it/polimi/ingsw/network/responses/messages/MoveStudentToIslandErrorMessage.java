package it.polimi.ingsw.network.responses.messages;

import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class MoveStudentToIslandErrorMessage implements ResponseMessage, ClientExecute {

    private String errorMessage;

    public MoveStudentToIslandErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /*
    @Override
    public void execute(Client client){
        client.setStatus(ClientStatus.INVALID_INPUT);
        client.moveStudentToIsland();
    }
    */
}
