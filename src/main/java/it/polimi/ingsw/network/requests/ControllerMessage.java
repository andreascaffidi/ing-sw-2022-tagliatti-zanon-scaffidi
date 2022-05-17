package it.polimi.ingsw.network.requests;

public class ControllerMessage {

    private RequestMessage requestMessage;
    private String username;

    public ControllerMessage(RequestMessage requestMessage, String username) {
        this.requestMessage = requestMessage;
        this.username = username;
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    public String getUsername() {
        return username;
    }

}
