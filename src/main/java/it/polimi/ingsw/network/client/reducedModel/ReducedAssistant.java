package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.Wizards;

import java.io.Serializable;

public class ReducedAssistant implements Serializable {

    private int id;
    private int motherNatureMovements;
    private Wizards wizard;

    public ReducedAssistant(int id, int motherNatureMovements, Wizards wizard) {
        this.id = id;
        this.motherNatureMovements = motherNatureMovements;
        this.wizard = wizard;
    }

    public int getId() {
        return id;
    }

    public int getMotherNatureMovements() {
        return motherNatureMovements;
    }

    public Wizards getWizard() {
        return wizard;
    }
}
