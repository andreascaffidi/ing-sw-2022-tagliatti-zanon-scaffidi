package it.polimi.ingsw.exceptions;

/**
 * exception thrown when a color is not found
 */
public class ColorNotFoundException extends Exception{
    public ColorNotFoundException(String message){
        super(message);
    }
}
