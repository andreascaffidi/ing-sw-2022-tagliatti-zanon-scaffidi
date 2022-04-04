package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableExpertModeTest {

    private TableExpertMode table2p;
    private TableExpertMode table3p;
    private TableExpertMode table4p;
    private List<Player> two;
    private List<Player> three;
    private List<Player> four;

    @BeforeEach
    void init() {
        Player p1,p2,p3,p4,p5,p6,p7,p8,p9;
        p1 = new Player("player1");
        p2 = new Player("player2");
        p3 = new Player("player3");
        p4 = new Player("player4");
        p5 = new Player("player5");
        p6 = new Player("player6", 1);
        p7 = new Player("player7", 1);
        p8 = new Player("player8", 2);
        p9 = new Player("player9", 2);
        two = new ArrayList<Player>(Arrays.asList(p1,p2));
        three = new ArrayList<Player>(Arrays.asList(p3,p4,p5));
        four = new ArrayList<Player>(Arrays.asList(p6,p7,p8,p9));
        table2p = new TableExpertMode(two);
        table3p = new TableExpertMode(three);
        table4p = new TableExpertMode(four);
    }

    @AfterEach
    void tearDown() {
        table2p = null;
        table3p = null;
        table4p = null;
        two = null;
        three = null;
        four = null;
    }


    @Test
    void setupCharacterCards() {
    }

    @Test
    void addAndPayCoins() {
        assertEquals(18, table2p.getBank());
        assertEquals(1, table2p.getPlayerCoins(two.get(0)));
        table2p.addCoins(two.get(0), 1);
        assertEquals(2,table2p.getPlayerCoins(two.get(0)));
        assertEquals(17, table2p.getBank());
        table2p.pay(two.get(0), 2);
        assertEquals(0,table2p.getPlayerCoins(two.get(0)));
        assertEquals(19, table2p.getBank());

        assertEquals(17, table3p.getBank());
        assertEquals(1, table3p.getPlayerCoins(three.get(2)));
        table3p.addCoins(three.get(2), 1);
        assertEquals(2,table3p.getPlayerCoins(three.get(2)));
        assertEquals(16, table3p.getBank());
        table3p.pay(three.get(0), 1);
        table3p.pay(three.get(2), 2);
        assertEquals(0,table3p.getPlayerCoins(three.get(0)));
        assertEquals(0,table3p.getPlayerCoins(three.get(2)));
        assertEquals(19, table3p.getBank());

        assertEquals(16, table4p.getBank());
        assertEquals(1, table4p.getPlayerCoins(four.get(3)));
        table4p.addCoins(four.get(1), 1);
        table4p.addCoins(four.get(2), 1);
        table4p.addCoins(four.get(3), 1);
        assertEquals(2, table4p.getPlayerCoins(four.get(1)));
        assertEquals(2, table4p.getPlayerCoins(four.get(2)));
        assertEquals(2, table4p.getPlayerCoins(four.get(3)));
        assertEquals(13, table4p.getBank());
        table4p.pay(four.get(1), 1);
        assertEquals(1, table4p.getPlayerCoins(four.get(1)));
        assertEquals(14, table4p.getBank());
    }

    @Test
    void resetCardsEffect() {
        table2p.setAdditionalInfluence(two.get(0), true);
        table2p.setCountTowers(table2p.motherNatureIsland(), false);
        table2p.setProfessorTie(two.get(1), true);
        table2p.setNoInfluenceColor(ColorS.BLUE);
        assertTrue(table2p.isAdditionalInfluence(two.get(0)));
        assertFalse(table2p.isCountTowers(table2p.motherNatureIsland()));
        assertTrue(table2p.isProfessorTie(two.get(1)));
        assertEquals(ColorS.BLUE, table2p.getNoInfluenceColor());
        table2p.resetCardsEffect();
        assertFalse(table2p.isAdditionalInfluence(two.get(0)));
        assertTrue(table2p.isCountTowers(table2p.motherNatureIsland()));
        assertFalse(table2p.isProfessorTie(two.get(1)));
        assertNull(table2p.getNoInfluenceColor());

        table3p.setAdditionalInfluence(three.get(0), true);
        table3p.setCountTowers(table3p.motherNatureIsland(), false);
        table3p.setProfessorTie(three.get(2), true);
        table3p.setNoInfluenceColor(ColorS.YELLOW);
        assertTrue(table3p.isAdditionalInfluence(three.get(0)));
        assertFalse(table3p.isCountTowers(table3p.motherNatureIsland()));
        assertTrue(table3p.isProfessorTie(three.get(2)));
        assertEquals(ColorS.YELLOW, table3p.getNoInfluenceColor());
        table3p.resetCardsEffect();
        assertFalse(table3p.isAdditionalInfluence(three.get(0)));
        assertTrue(table3p.isCountTowers(table3p.motherNatureIsland()));
        assertFalse(table3p.isProfessorTie(three.get(2)));
        assertNull(table3p.getNoInfluenceColor());

        table4p.setAdditionalInfluence(four.get(1), true);
        table4p.setCountTowers(table4p.motherNatureIsland(), false);
        table4p.setProfessorTie(four.get(2), true);
        table4p.setNoInfluenceColor(ColorS.RED);
        assertTrue(table4p.isAdditionalInfluence(four.get(1)));
        assertFalse(table4p.isCountTowers(table4p.motherNatureIsland()));
        assertTrue(table4p.isProfessorTie(four.get(2)));
        assertEquals(ColorS.RED, table4p.getNoInfluenceColor());
        table4p.resetCardsEffect();
        assertFalse(table4p.isAdditionalInfluence(four.get(1)));
        assertTrue(table4p.isCountTowers(table4p.motherNatureIsland()));
        assertFalse(table4p.isProfessorTie(four.get(2)));
        assertNull(table4p.getNoInfluenceColor());
    }

    @Test
    void getSupremacy() throws ParityException {
        table2p.motherNatureIsland().setTower(new Tower(ColorT.BLACK, two.get(0)));
        Player oldKing = table2p.motherNatureIsland().getTower().getOwner();
        table2p.getProfessor(ColorS.BLUE).setOwner(two.get(1));
        table2p.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table2p.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table2p.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table2p.setEntryTile(table2p.motherNatureIsland(), true);
        table2p.decrementEntryTile();
        assertEquals(3, table2p.getNumOfEntryTile());
        assertEquals(oldKing, table2p.getSupremacy(table2p.motherNatureIsland()));
        assertEquals(4, table2p.getNumOfEntryTile());
        assertFalse(table2p.isEntryTile(table2p.motherNatureIsland()));

        List<Student> students = new ArrayList<>(Arrays.asList(new Student(ColorS.YELLOW), new Student(ColorS.YELLOW), new Student(ColorS.BLUE)));

        table3p.getProfessor(ColorS.BLUE).setOwner(three.get(0));
        table3p.getProfessor(ColorS.YELLOW).setOwner(three.get(1));
        table3p.motherNatureIsland().addStudents(students);
        assertEquals(three.get(1), table3p.getSupremacy(table3p.motherNatureIsland()));
        table3p.setAdditionalInfluence(three.get(0), true);
        assertEquals(three.get(0), table3p.getSupremacy(table3p.motherNatureIsland()));
        table3p.setAdditionalInfluence(three.get(0), false);
        assertEquals(three.get(1), table3p.getSupremacy(table3p.motherNatureIsland()));
        table3p.setNoInfluenceColor(ColorS.YELLOW);
        assertEquals(three.get(0), table3p.getSupremacy(table3p.motherNatureIsland()));
        table3p.resetCardsEffect();
        assertEquals(three.get(1), table3p.getSupremacy(table3p.motherNatureIsland()));

        students.add(new Student(ColorS.BLUE));
        students.add(new Student(ColorS.BLUE));
        table4p.getProfessor(ColorS.BLUE).setOwner(four.get(0));
        table4p.getProfessor(ColorS.YELLOW).setOwner(four.get(1));
        table4p.motherNatureIsland().addStudents(students);
        table4p.motherNatureIsland().setTower(new Tower(ColorT.WHITE, four.get(1)));
        Exception exception = assertThrows(ParityException.class, () -> table4p.getSupremacy(table4p.motherNatureIsland()));
        assertEquals("there's a parity", exception.getMessage());
        table4p.setCountTowers(table4p.motherNatureIsland(), false);
        assertEquals(four.get(0), table4p.getSupremacy(table4p.motherNatureIsland()));
    }


    @Test
    void setProfessorOwner(){
        table2p.setProfessorTie(two.get(1), true);
        table2p.getPlayers()[0].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        table2p.getPlayers()[0].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        table2p.getPlayers()[0].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        table2p.setProfessorOwner(ColorS.YELLOW, table2p.getPlayers()[0]);
        assertEquals(two.get(0), table2p.getProfessorOwner(ColorS.YELLOW));

        table2p.getPlayers()[1].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        table2p.getPlayers()[1].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        table2p.setProfessorOwner(ColorS.YELLOW, table2p.getPlayers()[1]);
        assertEquals(two.get(0), table2p.getProfessorOwner(ColorS.YELLOW));

        table2p.getPlayers()[1].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        table2p.setProfessorOwner(ColorS.YELLOW, table2p.getPlayers()[1]);
        assertEquals(two.get(1), table2p.getProfessorOwner(ColorS.YELLOW));
    }

}