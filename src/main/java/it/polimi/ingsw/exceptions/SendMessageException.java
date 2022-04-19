package it.polimi.ingsw.exceptions;

import it.polimi.ingsw.network.responses.Response;
import it.polimi.ingsw.network.responses.ResponseMessage;

public class SendMessageException extends RuntimeException{
    private ResponseMessage response;

    public SendMessageException(ResponseMessage response) {
        this.response = response;
    }

    public ResponseMessage getResponse() {
        return response;
    }
}
