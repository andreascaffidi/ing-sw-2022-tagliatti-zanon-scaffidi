package it.polimi.ingsw.model.charactercards;

public class Character6 {
    public Character1(String name, int cost) {
        super(6, 3);
    }

    @Override
    public void activate(Table table, Controller controller)
    {
        //TODO: durante il conteggio dell'influenza su un'Isola (o gruppo di isole), le torri presenti non vengono calcolate
    }
}
