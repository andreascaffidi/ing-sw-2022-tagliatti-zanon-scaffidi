package it.polimi.ingsw.model.charactercards;

public class Character1 extends Character{

    public Character1(String name, int cost) {
        super(1, 1);
    }

    @Override
    public void activate(Table table, Controller controller)
    {
        //TODO: all'inizio della partita, pescate 4 studenti e piazzateli sopra questa carta
        //TODO: prendi 1 studente dalla carta e piazzalo su un'isola a tua scelta, poi pesca 1 studente dal sacchetto e mettilo su questa carta
    }
}
