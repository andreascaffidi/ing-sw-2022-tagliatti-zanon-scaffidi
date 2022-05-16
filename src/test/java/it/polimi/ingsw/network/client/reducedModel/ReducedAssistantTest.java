package it.polimi.ingsw.network.client.reducedModel;

import it.polimi.ingsw.model.enums.Wizards;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReducedAssistantTest {

    private ReducedAssistant reducedAssistant;

    @BeforeEach
    void setUp() {
        reducedAssistant = new ReducedAssistant(1, 3, Wizards.WIZARD_1);
    }

    @AfterEach
    void tearDown() {
        reducedAssistant= null;
    }

    @Test
    void getId() {
        assertEquals(1, reducedAssistant.getId());
    }

    @Test
    void getMotherNatureMovements() {
        assertEquals(3, reducedAssistant.getMotherNatureMovements());
    }

    @Test
    void getWizard() {
        assertEquals(Wizards.WIZARD_1, reducedAssistant.getWizard());
    }
}