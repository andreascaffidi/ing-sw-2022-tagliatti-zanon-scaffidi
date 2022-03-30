package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.enums.Wizards;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssistantTest {

    private Assistant assistant;

    @BeforeEach
    void init(){
        assistant = new Assistant(10,3, Wizards.WIZARD_1);
    }

    @Test
    void getValue() {
        assertEquals(10, assistant.getValue());
    }

    @Test
    void getMotherNatureMovements() {
        assertEquals(3, assistant.getMotherNatureMovements());
    }

    @Test
    void playedAssistant() {
        assertEquals(false, assistant.isPlayed());
        assistant.playAssistant();
        assertEquals(true, assistant.isPlayed());
    }

    @Test
    void getWizard() {
        assertEquals(Wizards.WIZARD_1, assistant.getWizard());
    }

    @AfterEach
    void tearDown(){
        this.assistant = null;
    }

}