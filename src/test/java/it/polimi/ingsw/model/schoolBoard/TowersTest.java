package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TowersTest {

    private Towers towers;
    private List<Tower> towerList;

    @BeforeEach
    void init(){
        towers = new Towers();
        Player player = new Player("username");
        towerList = new ArrayList<Tower>(Arrays.asList(new Tower(ColorT.BLACK, player), new Tower(ColorT.BLACK, player)));
    }

    @AfterEach
    void tearDown(){
        towers = null;
        towerList = null;
    }

    @Test
    void addAndGetTowers() {
        for (Tower t : towerList){
            towers.addTower(t);
        }
        assertEquals(towerList, towers.getTowers());
    }

    @Test
    void removeLastTower() {
        for (Tower t : towerList){
            towers.addTower(t);
        }
        assertEquals(towerList.get(1), towers.removeLastTower());
        assertFalse(towers.getTowers().contains(towerList.get(1)));
    }
}