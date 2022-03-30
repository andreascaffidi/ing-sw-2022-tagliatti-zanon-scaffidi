package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.enums.Wizards;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerExpertModeTest {

    PlayerExpertMode player;

    @BeforeEach
    void init() {
        List<Assistant> deck = new ArrayList<Assistant>(Arrays.asList(new Assistant(1,1, Wizards.WIZARD_1)));
        player = new PlayerExpertMode("username",deck,1, ColorT.BLACK);
    }

    @AfterEach
    void tearDown() {
        player = null;
    }

    @Test
    void addCoins() {
        player.addCoins(1);
        assertEquals(2, player.getCoins());
        player.addCoins(3);
        assertEquals(5, player.getCoins());
    }

    @Test
    void pay() {
        player.addCoins(8);
        assertEquals(9, player.getCoins());
        player.pay(6);
        assertEquals(3, player.getCoins());
    }

    @Test
    void getCoins() {
        assertTrue(true, "tested in other methods");
    }

    @Test
    void setAndIsAdditionalInfluence() {
        player.setAdditionalInfluence(true);
        assertTrue(player.isAdditionalInfluence());
    }

}