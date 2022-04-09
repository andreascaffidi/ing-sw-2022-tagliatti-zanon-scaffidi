package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TowerBoardTest {

    private TowerBoard towerBoard;
    private List<Tower> towerList;

    @BeforeEach
    void init(){
        towerBoard = new TowerBoard();
        Player player = new Player("username");
        towerList = new ArrayList<Tower>(Arrays.asList(new Tower(ColorT.BLACK, player), new Tower(ColorT.BLACK, player)));
    }

    @AfterEach
    void tearDown(){
        towerBoard = null;
        towerList = null;
    }

    @Test
    void addAndGetTowers() {
        for (Tower t : towerList){
            towerBoard.addTower(t);
        }
        assertEquals(towerList, towerBoard.getTowers());
    }

    @Test
    void removeLastTower() {
        for (Tower t : towerList){
            towerBoard.addTower(t);
        }
        assertEquals(towerList.get(1), towerBoard.removeLastTower());
        assertFalse(towerBoard.getTowers().contains(towerList.get(1)));
    }
}