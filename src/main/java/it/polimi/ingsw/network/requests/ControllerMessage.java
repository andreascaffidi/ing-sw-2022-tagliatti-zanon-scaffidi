package it.polimi.ingsw.network.requests;

import it.polimi.ingsw.view.View;

public class ControllerMessage {

    private RequestMessage requestMessage;
    private View view;
    private String username;
    private boolean expertMode;

    public ControllerMessage(RequestMessage requestMessage, String username, View view) {
        this.requestMessage = requestMessage;
        this.username = username;
        this.view = view;
        this.expertMode = false;
    }

    public ControllerMessage(RequestMessage requestMessage, String username, View view, boolean expertMode) {
        this.requestMessage = requestMessage;
        this.username = username;
        this.view = view;
        this.expertMode = expertMode;
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    public String getUsername() {
        return username;
    }

    public View getView(){
        return view;
    }

    public boolean isExpertMode(){
        return expertMode;
    }
}
