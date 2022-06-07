package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.network.client.reducedModel.ReducedAssistant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  This class purpose is to test all methods of the Assistant class
 */
class AssistantTest {

    private Assistant assistant;

    /**
     *  Initialises a new Assistant card.
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init(){
        assistant = new Assistant(10,3, Wizards.WIZARD_1);
    }

    /**
     * Tests if it returns the value set during initialization
     */
    @Test
    void getValue() {
        assertEquals(10, assistant.getValue());
    }

    /**
     * Tests if it returns the mother nature movements set during initialization
     */
    @Test
    void getMotherNatureMovements() {
        assertEquals(3, assistant.getMotherNatureMovements());
    }

    /**
     * Tests if it returns the wizard set during initialization
     */
    @Test
    void getWizard() {
        assertEquals(Wizards.WIZARD_1, assistant.getWizard());
    }

    /**
     * Tests if assistant is reduced correctly
     */
    @Test
    void reduceAssistant(){
        ReducedAssistant reducedAssistant = assistant.reduceAssistant();
        assertEquals(10, reducedAssistant.getId());
        assertEquals(3, reducedAssistant.getMotherNatureMovements());
        assertEquals(Wizards.WIZARD_1, reducedAssistant.getWizard());
    }

    /**
     * Sets to null every attribute
     * <br>
     * <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown(){
        this.assistant = null;
    }

}