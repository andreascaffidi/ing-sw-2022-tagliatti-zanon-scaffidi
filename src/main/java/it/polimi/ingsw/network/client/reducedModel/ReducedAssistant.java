package it.polimi.ingsw.network.client.reducedModel;

import java.io.Serializable;

public class ReducedAssistant implements Serializable {

    private int id;
    private int motherNatureMovements;
    private String wizard;

    public ReducedAssistant(int id, int motherNatureMovements, String wizard) {
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

    public String getWizard() {
        return wizard;
    }
}
