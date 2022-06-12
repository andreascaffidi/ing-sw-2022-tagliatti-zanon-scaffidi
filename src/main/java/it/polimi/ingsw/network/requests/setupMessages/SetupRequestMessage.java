package it.polimi.ingsw.network.requests.setupMessages;

import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.requests.SetupExecute;
import it.polimi.ingsw.network.server.Connection;

/**
 * setup request message (three types defined in SetupRequestTypes enum)
 */
public class SetupRequestMessage implements RequestMessage, SetupExecute {
    private final SetupRequestTypes typeOfMessage;
    private final String message;

    /**
     * builds a setup request message
     * @param typeOfMessage type of setup message
     * @param message message
     */
    public SetupRequestMessage(SetupRequestTypes typeOfMessage, String message) {
        this.typeOfMessage = typeOfMessage;
        this.message = message;
    }

    /**
     * implements SetupExecute interface
     * executes setupConnection() method on the connection class
     * @param connection connection
     */
    @Override
    public void execute(Connection connection) {
        connection.setupConnection(this);
    }

    /**
     * gets the message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * gets the type of setup request message
     * @return type of message
     */
    public SetupRequestTypes getTypeOfMessage() {
        return typeOfMessage;
    }
}
