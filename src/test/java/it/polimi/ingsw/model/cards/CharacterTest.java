package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.charactercards.Character1;
import it.polimi.ingsw.model.charactercards.Character2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private Character character;

    @BeforeEach
    void init(){
        character = new Character("1", 3, new Character1());
    }

    @Test
    void getName() {
        assertEquals("1", character.getName());
    }

    @Test
    void getAndIncrementCost() {
        assertEquals(3, character.getCost());
        character.incrementCost();
        assertEquals(4, character.getCost());
        character.incrementCost();
        assertEquals(5, character.getCost());
    }


    @Test
    void activate() {

    }

    @Test
    void setup() {

    }

    @AfterEach
    void tearDown(){
        this.character = null;
    }
}