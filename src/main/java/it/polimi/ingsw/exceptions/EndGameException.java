package it.polimi.ingsw.exceptions;

/**
 * exception thrown when game ends for any conditions
 */
public class EndGameException extends Exception{
    public EndGameException(String message) {
        super(message);
    }
}
