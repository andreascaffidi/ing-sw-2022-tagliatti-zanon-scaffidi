package it.polimi.ingsw.network;

public class Message {

    private TypeOfMessage typeOfMessage;
    private String username;

    public Message(TypeOfMessage typeOfMessage, String username) {
        this.typeOfMessage = typeOfMessage;
        this.username = username;
    }

    public TypeOfMessage getTypeOfMessage() {
        return typeOfMessage;
    }

    public String getUsername() {
        return username;
    }
}
