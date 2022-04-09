package it.polimi.ingsw.network;

import it.polimi.ingsw.network.requestMessage.RequestMessage;

public class Message {

    private RequestMessage requestMessage;
    private String username;
    private boolean expertMode;

    public Message(RequestMessage requestMessage, String username) {
        this.requestMessage = requestMessage;
        this.username = username;
        this.expertMode = false;
    }

    public Message(RequestMessage requestMessage, String username, boolean expertMode) {
        this.requestMessage = requestMessage;
        this.username = username;
        this.expertMode = expertMode;
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    public String getUsername() {
        return username;
    }

    public boolean isExpertMode(){
        return expertMode;
    }
}
