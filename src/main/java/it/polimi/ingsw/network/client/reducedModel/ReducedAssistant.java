package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.Wizards;

import java.io.Serializable;

/**
 * reduced serializable version of assistant card
 */
public class ReducedAssistant implements Serializable {

    private final int id;
    private final int motherNatureMovements;
    private final Wizards wizard;

    /**
     * build a reduced assistant
     * @param id assistant's value
     * @param motherNatureMovements assistant's mother nature movements
     * @param wizard assistant's wizard
     */
    public ReducedAssistant(int id, int motherNatureMovements, Wizards wizard) {
        this.id = id;
        this.motherNatureMovements = motherNatureMovements;
        this.wizard = wizard;
    }

    /**
     * gets the value of the reduced assistant
     * @return value of the reduced assistant
     */
    public int getId() {
        return id;
    }

    /**
     * gets the mother nature movements of the reduced assistant
     * @return mother nature movements of the reduced assistant
     */
    public int getMotherNatureMovements() {
        return motherNatureMovements;
    }

    /**
     * gets the assistant card's wizard
     * @return assistant card's wizard
     */
    public Wizards getWizard() {
        return wizard;
    }
}
