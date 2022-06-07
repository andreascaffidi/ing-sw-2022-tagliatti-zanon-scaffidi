package it.polimi.ingsw.exceptions;

/**
 * exception thrown when a character card (with students on it) is not found
 */
public class CardNotFoundException extends Exception{
    public CardNotFoundException(String message)
    {
        super(message);
    }
}
