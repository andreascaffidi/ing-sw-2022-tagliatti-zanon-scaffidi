package it.polimi.ingsw.model.charactercards;

public class Character12 {
    public Character12(String name, int cost) {
        super(12, 3);
    }

    @Override
    public void activate(Table table, Controller controller)
    {
        //TODO: Scegli un colore di Studente; ogni giocatore (incluso te) deve rimettere nel sacchetto 3 studenti di quel colore presenti nella sua sala.
        //Chi avesse meno di 3 studenti di quel colore, rimetterà tutti quelli che ha
    }
}
