package it.polimi.ingsw.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * observable class
 * @param <T> generic
 */
public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();

    /**
     * adds an observer to the observable object
     * @param observer observer added
     */
    public void addObserver(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     * notifies an event and send a generic message
     * @param message generic message
     */
    public void notify(T message){
        synchronized (observers) {
            for (Observer<T> observer : observers) {
                observer.update(message);
            }
        }
    }

}
