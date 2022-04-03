package it.polimi.ingsw.exceptions;

public class WrongPlayerException extends RuntimeException{
    public WrongPlayerException(String message) {
        super(message);
    }
}
