package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.charactercards.Character1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private Character character;

    @BeforeEach
    void init(){
        character = new Character1();
    }

    @AfterEach
    void tearDown(){
        character = null;
    }

    @Test
    void getName() {
        assertEquals("Character1", character.getName());
    }

    @Test
    void getAndIncrementCost() {
        assertEquals(1, character.getCost());
        character.incrementCost();
        assertEquals(2, character.getCost());
    }

}