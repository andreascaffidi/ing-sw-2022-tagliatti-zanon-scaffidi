package it.polimi.ingsw.exceptions;

/**
 * exception thrown when an assistant card is not found
 */
public class AssistantNotFoundException extends Exception {
    public AssistantNotFoundException(String message) {
        super(message);
    }
}
