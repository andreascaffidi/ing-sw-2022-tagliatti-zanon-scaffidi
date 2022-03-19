package it.polimi.ingsw.model.charactercards;

public class Character5 {
    public Character5(String name, int cost) {
        super(5, 2);
    }

    @Override
    public void activate(Table table, Controller controller)
    {
        //TODO: All'inizio della partita, mettete le 4 tessere divieto su un'Isola a tua scelta.
        //La prima volta che Madre natura termina il suo movimento lì, rimettete la tessera divieto sulla carta senza calcolare l'influenza su quell'isola nè piazzare torri
    }

    public void setup(Table table)
    {
        for(int i = 0; i < NUM_OF_STUDENTS; i++)
        {
            students.add(table.bag.drawstudent());
        }
    }
}
