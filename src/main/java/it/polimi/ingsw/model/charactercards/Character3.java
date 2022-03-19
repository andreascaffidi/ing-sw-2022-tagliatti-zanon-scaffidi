package it.polimi.ingsw.model.charactercards;

public class Character3 {
    public Character3(String name, int cost) {
        super(3, 3);
    }

    //TODO: scegli un'isola e calcola la maggioranza come se Madre Natura avesse terminato il suo movimento lì.
    //In questo turno Madre Natura si muoverà come di consueto e nell'Isola dove terminerà il suo movimento la maggioranza verrà normalmente calcolata
    @Override
    public Player activate(Table table, Island island)
    {
        for(Island i : table.getIslands())
        {
            if(i.equals(island))
            {
                return table.getSupremacy(i);
                //supponiamo ci sia un metodo nel controller che ad ogni round sostituisca le torri in base alla supremazia.
            }
        }
    }
}
