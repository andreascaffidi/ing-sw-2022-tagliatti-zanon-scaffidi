package it.polimi.ingsw.view;

public abstract class UI {

    public abstract void sendingFailed();

    public abstract void showTextMessage(String toString);

    public abstract int askPlayersNumber();

    public abstract int askUsername();

    public abstract void print(String message);

}
