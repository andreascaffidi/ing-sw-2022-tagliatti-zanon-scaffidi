package it.polimi.ingsw.exceptions;

/**
 * exception thrown when there's a generic problem in the current game
 */
public class GameException extends Exception{
    public GameException(String message) {
        super(message);
    }
}
