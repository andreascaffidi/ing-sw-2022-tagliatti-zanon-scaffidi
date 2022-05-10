package it.polimi.ingsw.network.requests.setupMessages;

import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.requests.SetupExecute;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
import it.polimi.ingsw.network.server.Connection;

public class SetupRequestMessage implements RequestMessage, SetupExecute {
    private SetupResponsesTypes typeOfMessage;
    private String message;

    public SetupRequestMessage(SetupResponsesTypes typeOfMessage, String message) {
        this.typeOfMessage = typeOfMessage;
        this.message = message;
    }

    @Override
    public void execute(Connection connection) {
        connection.setupConnection(this);
    }

    public String getMessage() {
        return message;
    }

    public SetupResponsesTypes getTypeOfMessage() {
        return typeOfMessage;
    }
}
