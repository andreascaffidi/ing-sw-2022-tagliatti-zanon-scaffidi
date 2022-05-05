package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.network.client.reducedModel.ReducedAssistant;

/**
 * class assistant card
 */
public class Assistant {
    private final int value;
    private final int motherNatureMovements;
    private final Wizards wizard;

    /**
     * builds assistant card
     * @param value value of the card
     * @param motherNatureMovements number of maximum possible mother nature movements
     * @param wizard card's wizard
     */
    public Assistant(int value, int motherNatureMovements, Wizards wizard){
        this.value = value;
        this.motherNatureMovements = motherNatureMovements;
        this.wizard = wizard;
    }

    /**
     * gets the value of the card
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * gets the number of maximum possible mother nature movements
     * @return the number of the movements
     */
    public int getMotherNatureMovements() {
        return motherNatureMovements;
    }

    /**
     * gets the card's wizard
     * @return card's wizard
     */
    public Wizards getWizard() {
        return wizard;
    }

    /**
     * gets a reduced version of the assistant card
     * @return reduced assistant
     */
    public ReducedAssistant reduceAssistant()
    {
        return new ReducedAssistant(this.value, this.motherNatureMovements, this.wizard);
    }
}
