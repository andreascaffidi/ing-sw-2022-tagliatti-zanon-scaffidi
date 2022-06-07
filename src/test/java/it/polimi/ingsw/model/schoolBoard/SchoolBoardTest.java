package it.polimi.ingsw.model.schoolBoard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolBoardTest {

    private SchoolBoard schoolBoard;

    /**
     *  Initialises a school board
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init(){
        schoolBoard = new SchoolBoard();
    }

    /**
     * Tests if it returns the entrance correctly
     */
    @Test
    void getEntrance() {
        assertInstanceOf(Entrance.class,schoolBoard.getEntrance());
    }

    /**
     * Tests if it returns the dining room correctly
     */
    @Test
    void getDiningRoom() {
        assertInstanceOf(DiningRoom.class,schoolBoard.getDiningRoom());
    }

    /**
     * Tests if it returns the professor table correctly
     */
    @Test
    void getProfessorTable() {
        assertInstanceOf(ProfessorTable.class,schoolBoard.getProfessorTable());
    }

    /**
     * Tests if it returns the tower board correctly
     */
    @Test
    void getTowerBoard() {
        assertInstanceOf(TowerBoard.class,schoolBoard.getTowerBoard());
    }
}