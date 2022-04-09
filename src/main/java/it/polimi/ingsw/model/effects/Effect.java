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
