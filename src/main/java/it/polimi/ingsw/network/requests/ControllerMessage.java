package it.polimi.ingsw.network.requests;

/**
 * message handled by controller
 */
public class ControllerMessage {

    private final RequestMessage requestMessage;
    private final String username;

    /**
     * builds a controller message
     * @param requestMessage request message
     * @param username username of the sender
     */
    public ControllerMessage(RequestMessage requestMessage, String username) {
        this.requestMessage = requestMessage;
        this.username = username;
    }

    /**
     * gets the request message
     * @return request message
     */
    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    /**
     * gets the username of the sender
     * @return username
     */
    public String getUsername() {
        return username;
    }

}
