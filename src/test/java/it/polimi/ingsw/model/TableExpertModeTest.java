package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.effects.*;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
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
        two = new ArrayList<>(Arrays.asList(p1,p2));
        three = new ArrayList<>(Arrays.asList(p3,p4,p5));
        four = new ArrayList<>(Arrays.asList(p6,p7,p8,p9));
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


    @RepeatedTest(20)
    void setupCharacterCards() {
        assertEquals(3, table2p.getCharacters().size());
        assertEquals(3, table3p.getCharacters().size());
        assertEquals(3, table4p.getCharacters().size());

        for (int i = 1; i < 13; i++){
            if(table2p.getCharacters().containsKey(i)){
                int price = i % 3 == 0 ? 3 : (i % 3);
                assertEquals(price, table2p.getCharacters().get(i));
            }
        }
    }

    @RepeatedTest(20)
    void setupStudentsOnCard() throws GameException {
        if(table2p.getCharacters().containsKey(1)){
            assertEquals(4, table2p.getCardWithStudents(1).getStudents().size());
        }
        if(table2p.getCharacters().containsKey(7)){
            assertEquals(6, table2p.getCardWithStudents(7).getStudents().size());
        }
        if(table2p.getCharacters().containsKey(11)){
            assertEquals(4, table2p.getCardWithStudents(11).getStudents().size());
        }
        if(table2p.getCharacters().containsKey(3)){
            assertThrows(GameException.class, () -> table2p.getCardWithStudents(3));
        }
    }

    @Test
    void getCardWithStudent(){
        assertTrue(true, "tested in other methods");
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
    void incrementCardCost(){
        table2p.getCharacters().put(20, 2);
        table2p.incrementCardCost(20);
        assertEquals(3, table2p.getCharacters().get(20));
    }

    @Test
    void setAndResetCurrentEffect(){
        assertTrue(true, "tested in other methods");
    }

    @Test
    void getSupremacy() throws ParityException {
        table2p.motherNatureIsland().setTower(new Tower(ColorT.BLACK, two.get(0)));
        Player oldKing = table2p.motherNatureIsland().getTower().getOwner();
        table2p.getProfessor(ColorS.BLUE).setOwner(two.get(1));
        table2p.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table2p.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table2p.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table2p.setNoEntryTile(table2p.motherNatureIsland(), true);
        assertTrue(table2p.isNoEntryTile(table2p.motherNatureIsland()));
        assertEquals(oldKing, table2p.getSupremacy(table2p.motherNatureIsland()));
        assertFalse(table2p.isNoEntryTile(table2p.motherNatureIsland()));

        List<Student> students = new ArrayList<>(Arrays.asList(new Student(ColorS.YELLOW), new Student(ColorS.YELLOW), new Student(ColorS.BLUE)));

        table3p.getPlayers()[0].getSchoolBoard().getProfessorTable().addProfessor(table3p.getProfessor(ColorS.BLUE));
        table3p.getPlayers()[1].getSchoolBoard().getProfessorTable().addProfessor(table3p.getProfessor(ColorS.YELLOW));
        table3p.getProfessor(ColorS.BLUE).setOwner(three.get(0));
        table3p.getProfessor(ColorS.YELLOW).setOwner(three.get(1));

        table3p.motherNatureIsland().addStudents(students);
        assertEquals(three.get(1), table3p.getSupremacy(table3p.motherNatureIsland()));
        table3p.setCurrentEffect(new AdditionalInfluenceEffect(three.get(0)));
        assertEquals(three.get(0), table3p.getSupremacy(table3p.motherNatureIsland()));
        table3p.resetCurrentEffect();
        assertEquals(three.get(1), table3p.getSupremacy(table3p.motherNatureIsland()));
        table3p.setCurrentEffect(new NoInfluenceColorEffect(ColorS.YELLOW));
        assertEquals(three.get(0), table3p.getSupremacy(table3p.motherNatureIsland()));
        table3p.resetCurrentEffect();
        assertEquals(three.get(1), table3p.getSupremacy(table3p.motherNatureIsland()));

        students.add(new Student(ColorS.BLUE));
        students.add(new Student(ColorS.BLUE));
        table4p.getProfessor(ColorS.BLUE).setOwner(four.get(0));
        table4p.getProfessor(ColorS.YELLOW).setOwner(four.get(1));
        table4p.motherNatureIsland().addStudents(students);
        table4p.motherNatureIsland().setTower(new Tower(ColorT.WHITE, four.get(1)));
        Exception exception = assertThrows(ParityException.class, () -> table4p.getSupremacy(table4p.motherNatureIsland()));
        assertEquals("there's a parity", exception.getMessage());
        table4p.setCurrentEffect(new CountTowersEffect(table4p.motherNatureIsland()));
        assertEquals(four.get(0), table4p.getSupremacy(table4p.motherNatureIsland()));
    }

    @Test
    void newIslandGroup(){
        table2p.getIsland(2).setTower(new Tower(ColorT.BLACK, two.get(0)));
        table2p.getIsland(3).setTower(new Tower(ColorT.BLACK, two.get(0)));
        table2p.setNoEntryTile(table2p.getIsland(3), true);
        List<Island> islands = new ArrayList<>();
        islands.add(table2p.getIsland(2));
        islands.add(table2p.getIsland(3));
        table2p.newIslandGroup(islands);
        assertTrue(table2p.isNoEntryTile(table2p.getIsland(2)));
    }

    @Test
    void setProfessorOwner(){
        table2p.setCurrentEffect(new ProfessorTieEffect(table2p.getPlayers()[1]));
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

    @Test
    void invalidCharacter(){
        for (int i = 1; i < 13; i++){
            if(!table2p.getCharacters().containsKey(i)){
                int character = i;
                assertThrows(GameException.class, () -> table2p.validCharacter(character));
            }
        }
    }

    @Test
    void notEnoughCoins() throws GameException,NotEnoughCoinsException {
        table2p.getCharacters().put(13, 20);
        assertThrows(NotEnoughCoinsException.class, () -> table2p.validCharacter(13));
        table2p.getCharacters().put(14, 1);
        table2p.validCharacter(14);
    }

    @Test
    void validAdditionalMovement() throws GameException {
        table2p.validAdditionalMovement(1);
        table2p.validAdditionalMovement(2);
        assertThrows(GameException.class, () -> table2p.validAdditionalMovement(0));
        assertThrows(GameException.class, () -> table2p.validAdditionalMovement(3));
    }

    @Test
    void validNoEntryTile() throws GameException{
        table2p.validNoEntryTile(table2p.getIsland(1));
        table2p.setNoEntryTile(table2p.getIsland(1), true);
        table2p.validNoEntryTile(table2p.getIsland(2));
        table2p.setNoEntryTile(table2p.getIsland(2), true);
        table2p.validNoEntryTile(table2p.getIsland(3));
        table2p.setNoEntryTile(table2p.getIsland(3), true);
        assertThrows(GameException.class, () -> table2p.validNoEntryTile(table2p.getIsland(3)));
        table2p.validNoEntryTile(table2p.getIsland(4));
        table2p.setNoEntryTile(table2p.getIsland(4), true);
        assertThrows(GameException.class, () -> table2p.validNoEntryTile(table2p.getIsland(5)));
    }
}