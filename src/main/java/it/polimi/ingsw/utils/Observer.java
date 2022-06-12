package it.polimi.ingsw.utils;

/**
 * interface observer
 * @param <T> generic
 */
public interface Observer<T> {

    /**
     * called when an event is notified
     * @param message generic message
     */
    void update(T message);

}
