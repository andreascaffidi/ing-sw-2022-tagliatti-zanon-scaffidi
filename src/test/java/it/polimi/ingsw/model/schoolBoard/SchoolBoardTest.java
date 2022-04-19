package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolBoardTest {
    SchoolBoard schoolBoard;
    @BeforeEach
    void init(){
        Player player = new Player("Test");
        schoolBoard = new SchoolBoard();
    }

    @Test
    void getEntrance() {
        assertInstanceOf(Entrance.class,schoolBoard.getEntrance());
    }

    @Test
    void getDiningRoom() {
        assertInstanceOf(DiningRoom.class,schoolBoard.getDiningRoom());
    }

    @Test
    void getProfessorTable() {
        assertInstanceOf(ProfessorTable.class,schoolBoard.getProfessorTable());
    }

    @Test
    void getTowers() {
        assertInstanceOf(TowerBoard.class,schoolBoard.getTowerBoard());
    }
}