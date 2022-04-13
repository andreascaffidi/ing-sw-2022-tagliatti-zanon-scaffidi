package it.polimi.ingsw.model.effects;

public class Effect {

    private TypeOfEffect typeOfEffect;

    public Effect(TypeOfEffect typeOfEffect){
        this.typeOfEffect = typeOfEffect;
    }


    public TypeOfEffect getTypeOfEffect() {
        return typeOfEffect;
    }
}


/*
ci sono 3 tipi di effetti:
1) effetti istantanei: gestibili direttamente dal controller (1, 3, 4, 7, 10, 11, 12)
2) effetti durevoli nel tempo: 5
3) effetti che terminano alla fine della fase azione del giocatore che li ha attivati: 2, 6, 8, 9

in ogni turno io posso pagare un solo effetto, quindi ci può essere un solo effetto attivo prima del reset -> currentEffect
 */