package it.polimi.ingsw.network.requests;

import it.polimi.ingsw.view.View;

public class ControllerMessage {

    private RequestMessage requestMessage;
    private String username;
    private boolean expertMode;

    public ControllerMessage(RequestMessage requestMessage, String username) {
        this.requestMessage = requestMessage;
        this.username = username;
        this.expertMode = false;
    }

    public ControllerMessage(RequestMessage requestMessage, String username, boolean expertMode) {
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
