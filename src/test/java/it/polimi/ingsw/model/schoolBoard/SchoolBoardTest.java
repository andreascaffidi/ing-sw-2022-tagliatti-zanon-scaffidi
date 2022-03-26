package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolBoardTest {
    SchoolBoard schoolBoard;
    @BeforeEach
    void init(){
        Player player = new Player("Test");
        schoolBoard = new SchoolBoard(player);
    }
    @Test
    void getPlayer() {
        assertInstanceOf(Player.class,schoolBoard.getPlayer());
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
        assertInstanceOf(Towers.class,schoolBoard.getTowers());
    }
}