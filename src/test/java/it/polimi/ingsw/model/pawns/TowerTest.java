package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {

    @Test
    void getOwner() {
        Player owner = new Player("nickname");
        Tower tower = new Tower(ColorT.BLACK, owner);
        assertEquals(owner, tower.getOwner());
    }
}