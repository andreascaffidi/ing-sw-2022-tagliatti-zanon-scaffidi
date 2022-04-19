package it.polimi.ingsw.network.responses;

public class Response {

    private ResponseMessage responseMessage;

    public Response(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

}
