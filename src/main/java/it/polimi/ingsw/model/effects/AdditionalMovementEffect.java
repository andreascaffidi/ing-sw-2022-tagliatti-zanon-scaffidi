package it.polimi.ingsw.model.effects;

/**
 * character card effect that adds additional movements for mother nature
 */
public class AdditionalMovementEffect implements Effect, MotherNatureEffect {

    private final int additionalMovements;

    /**
     * builds "additionalMovement" effect
     * @param additionalMovements additional mother nature movements
     */
    public AdditionalMovementEffect(int additionalMovements) {
        this.additionalMovements = additionalMovements;
    }

    /**
     * adds the additional movements to mother nature
     * @param movements movements
     */
    @Override
    public int motherNatureEffect(int movements) {
        return movements + additionalMovements;
    }

    /**
     * override of toString() method
     * @return specified effect
     */
    @Override
    public String toString() {
        return "Current player can move mother nature up to " + additionalMovements + " movements more";
    }
}
