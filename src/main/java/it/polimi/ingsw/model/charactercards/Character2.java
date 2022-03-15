package it.polimi.ingsw.model.charactercards;

public class Character2 {

    public Character2(String name, int cost) {
        super(2, 2);
    }

    @Override
    public void activate(Table table, Controller controller)
    {
        //TODO: durante questo turno, prendi il controllo dei Professori anche se nella tua sala hai lo stesso numero di studenti del giocatore che li controlla in quel momento
    }
}
