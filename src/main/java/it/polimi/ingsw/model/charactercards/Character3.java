package it.polimi.ingsw.model.charactercards;

public class Character3 {
    public Character3(String name, int cost) {
        super(3, 3);
    }

    @Override
    public void activate(Table table, Controller controller)
    {
        //TODO: scegli un'isola e calcola la maggioranza come se Madre Natura avesse terminato il suo movimento lì.
        //In questo turno Madre Natura si muoverà come di consueto e nell'Isola dove terminerà il suo movimento la maggioranza verrà normalmente calcolata
    }
}
