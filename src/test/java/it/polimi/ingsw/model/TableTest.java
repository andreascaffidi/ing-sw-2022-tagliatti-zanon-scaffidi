package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.network.client.reducedModel.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private Table table2p;
    private Table table3p;
    private Table table4p;
    private List<Player> two;
    private List<Player> three;
    private List<Player> four;

    /**
     *  Initialises four different table, one for each match's number of player
     *  <br>
     *  <u>It's called before each test</u>
     */
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
        table2p = new Table(two);
        table3p = new Table(three);
        table4p = new Table(four);
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        table2p = null;
        table3p = null;
        table4p = null;
        two = null;
        three = null;
        four = null;
    }

    /**
     * Tests if there are more than four players (RunTimeException thrown)
     */
    @Test
    void moreThanFourPlayers(){
        List<Player> players = new ArrayList<>(Arrays.asList(new Player("1"), new Player("2"), new Player("3"), new Player("4"), new Player("5")));
        assertThrows(RuntimeException.class, () -> new Table(players));
    }

    /**
     * Tests if players are correctly set up, checking their towers' color
     */
    @Test
    void setupPlayers() {
        //test setting of tower color for each type of table
        assertArrayEquals(two.toArray(), table2p.getPlayers());
        assertEquals(ColorT.BLACK, table2p.getPlayers()[0].getTowerColor());
        assertEquals(ColorT.WHITE, table2p.getPlayers()[1].getTowerColor());

        assertArrayEquals(three.toArray(), table3p.getPlayers());
        assertEquals(ColorT.BLACK, table3p.getPlayers()[0].getTowerColor());
        assertEquals(ColorT.WHITE, table3p.getPlayers()[1].getTowerColor());
        assertEquals(ColorT.GREY, table3p.getPlayers()[2].getTowerColor());

        assertArrayEquals(four.toArray(), table4p.getPlayers());
        assertEquals(ColorT.BLACK, table4p.getPlayers()[0].getTowerColor());
        assertEquals(ColorT.BLACK, table4p.getPlayers()[2].getTowerColor());
        assertEquals(ColorT.WHITE, table4p.getPlayers()[1].getTowerColor());
        assertEquals(ColorT.WHITE, table4p.getPlayers()[3].getTowerColor());
    }

    /**
     * Tests if islands are correctly set up
     * <ol>
     *     <li>Checks the number of islands on the table</li>
     *     <li>Checks if mother nature is present on a random island</li>
     *     <li>Checks if each islands has the correct students</li>
     * </ol>
     */
    @Test
    void setupIslands() {
        //test number of Islands for each type of table
        assertEquals(12, table2p.getIslands().size());
        assertEquals(12, table3p.getIslands().size());
        assertEquals(12, table4p.getIslands().size());

        //test if MotherNature is present on only one island
        assertEquals(1, table2p.getIslands().stream().filter(Island::isMotherNature).count());
        assertEquals(1, table3p.getIslands().stream().filter(Island::isMotherNature).count());
        assertEquals(1, table4p.getIslands().stream().filter(Island::isMotherNature).count());

        //test opposite island calculation
        int idTest = 3;
        assertEquals(9, (idTest+6) % 12);

        //test if every island has a student except mother and opposite (for each type of table)
        int motherNatureId = table2p.motherNatureIsland().getId();
        int oppositeId =  (motherNatureId + 6) % 12;
        for (Island i : table2p.getIslands()){
            if(i.getId() != motherNatureId && i.getId() != oppositeId){
                assertEquals(1, i.getStudents().size());
            }
            else{
                assertEquals(0, i.getStudents().size());
            }
        }

        motherNatureId = table3p.motherNatureIsland().getId();
        oppositeId =  (motherNatureId + 6) % 12;
        for (Island i : table3p.getIslands()){
            if(i.getId() != motherNatureId && i.getId() != oppositeId){
                assertEquals(1, i.getStudents().size());
            }
            else{
                assertEquals(0, i.getStudents().size());
            }
        }

        motherNatureId = table4p.motherNatureIsland().getId();
        oppositeId =  (motherNatureId + 6) % 12;
        for (Island i : table4p.getIslands()){
            if(i.getId() != motherNatureId && i.getId() != oppositeId){
                assertEquals(1, i.getStudents().size());
            }
            else{
                assertEquals(0, i.getStudents().size());
            }
        }

        //test if student are two 2 per color (for each type of table)
        for (ColorS c : ColorS.values()){
            assertEquals(2, table2p.getIslands().stream().flatMap(i -> i.getStudents().stream()).filter(s -> s.getColor() == c).count());
        }

        for (ColorS c : ColorS.values()){
            assertEquals(2, table3p.getIslands().stream().flatMap(i -> i.getStudents().stream()).filter(s -> s.getColor() == c).count());
        }

        for (ColorS c : ColorS.values()){
            assertEquals(2, table4p.getIslands().stream().flatMap(i -> i.getStudents().stream()).filter(s -> s.getColor() == c).count());
        }
    }

    /**
     * Tests if clouds are correctly set up, checking the number on the table
     */
    @Test
    void setupClouds() {
        //test if number of clouds equals number of players
        assertEquals(2, table2p.getClouds().size());
        assertEquals(3, table3p.getClouds().size());
        assertEquals(4, table4p.getClouds().size());
    }

    /**
     * Tests if students are correctly set up, checking the number on the bag
     */
    @Test
    void setupStudents() {
        //test number of students remained in the bag after table instantiation, for each type of table
        assertEquals(100, table2p.getBag().getStudents().size());
        assertEquals(81, table3p.getBag().getStudents().size());
        assertEquals(80, table4p.getBag().getStudents().size());
    }

    /**
     * Tests if professors are correctly set up
     */
    @Test
    void setupProfessors() {
        for (ColorS c : ColorS.values()){
            assertNotNull(table2p.getProfessor(c));
            assertNotNull(table3p.getProfessor(c));
            assertNotNull(table4p.getProfessor(c));
        }
    }

    /**
     * Tests if school boards are correctly set up
     * <ol>
     *     <li>Checks if school boards are instantiated correctly</li>
     *     <li>Checks if there is the correct number of students and towers on each board</li>
     *     <li>Checks the same for 4-players match</li>
     * </ol>
     */
    @Test
    void setupSchoolBoards() {
        //test SchoolBoard created for each player (for each type of table)
        for (int i=0; i<table2p.getPlayers().length; i++){
            assertNotNull(table2p.getPlayers()[i].getSchoolBoard());
        }

        for (int i=0; i<table3p.getPlayers().length; i++){
            assertNotNull(table3p.getPlayers()[i].getSchoolBoard());
        }

        for (int i=0; i<table4p.getPlayers().length; i++){
            assertNotNull(table4p.getPlayers()[i].getSchoolBoard());
        }

        //test number of students on each player's entrance (for each type of table)
        for (int i=0; i<table2p.getPlayers().length; i++){
            assertEquals(7, table2p.getPlayers()[i].getSchoolBoard().getEntrance().getStudents().size());
        }

        for (int i=0; i<table3p.getPlayers().length; i++){
            assertEquals(9, table3p.getPlayers()[i].getSchoolBoard().getEntrance().getStudents().size());
        }

        for (int i=0; i<table2p.getPlayers().length; i++){
            assertEquals(7, table4p.getPlayers()[i].getSchoolBoard().getEntrance().getStudents().size());
        }

        //test correct number of towers on each player's TowersBoard and test the same tower color for each tower (for each type of table)
        for (int i=0; i<table2p.getPlayers().length; i++){
            assertEquals(8, table2p.getPlayers()[i].getSchoolBoard().getTowerBoard().getTowers().size());
            for (int j=0; j<8; j++){
                assertEquals(table2p.getPlayers()[i].getTowerColor(), table2p.getPlayers()[i].getSchoolBoard().getTowerBoard().getTowers().get(j).getColor());
            }
        }

        for (int i=0; i<table3p.getPlayers().length; i++){
            assertEquals(6, table3p.getPlayers()[i].getSchoolBoard().getTowerBoard().getTowers().size());
            for (int j=0; j<6; j++){
                assertEquals(table3p.getPlayers()[i].getTowerColor(), table3p.getPlayers()[i].getSchoolBoard().getTowerBoard().getTowers().get(j).getColor());
            }
        }

        //test case 4 players is different
        for (int i=0; i<table4p.getPlayers().length; i++){
            if (i<2) {
                assertEquals(8, table4p.getPlayers()[i].getSchoolBoard().getTowerBoard().getTowers().size());
                for (int j=0; j<8; j++){
                    assertEquals(table4p.getPlayers()[i].getTowerColor(), table4p.getPlayers()[i].getSchoolBoard().getTowerBoard().getTowers().get(j).getColor());
                }
            }
            else
            {
                assertEquals(0, table4p.getPlayers()[i].getSchoolBoard().getTowerBoard().getTowers().size());
            }
        }
    }

    /**
     * Tests if assistant cards are correctly set up, checking if each card has the correct values
     */
    @Test
    void setupAssistantCards() {
        int motherNatureMov = 0;

        //for each player check number and values of the assistant cards
        for (int i = 0; i < table2p.getPlayers().length; i++){
            assertEquals(10, table2p.getPlayers()[i].getAssistantDeck().size());
            for (int j = 0; j < table2p.getPlayers()[i].getAssistantDeck().size(); j++){
                if (j % 2 == 0){
                    motherNatureMov++;
                }
                assertEquals(Wizards.values()[i], table2p.getPlayers()[i].getAssistantDeck().get(j).getWizard());
                assertEquals(j + 1, table2p.getPlayers()[i].getAssistantDeck().get(j).getValue());
                assertEquals(motherNatureMov, table2p.getPlayers()[i].getAssistantDeck().get(j).getMotherNatureMovements());
            }
            motherNatureMov = 0;
        }

        for (int i = 0; i < table3p.getPlayers().length; i++){
            assertEquals(10, table3p.getPlayers()[i].getAssistantDeck().size());
            for (int j = 0; j < table3p.getPlayers()[i].getAssistantDeck().size(); j++){
                if (j % 2 == 0){
                    motherNatureMov++;
                }
                assertEquals(Wizards.values()[i], table3p.getPlayers()[i].getAssistantDeck().get(j).getWizard());
                assertEquals(j + 1, table3p.getPlayers()[i].getAssistantDeck().get(j).getValue());
                assertEquals(motherNatureMov, table3p.getPlayers()[i].getAssistantDeck().get(j).getMotherNatureMovements());
            }
            motherNatureMov = 0;
        }

        for (int i = 0; i < table4p.getPlayers().length; i++){
            assertEquals(10, table4p.getPlayers()[i].getAssistantDeck().size());
            for (int j = 0; j < table4p.getPlayers()[i].getAssistantDeck().size(); j++){
                if (j % 2 == 0){
                    motherNatureMov++;
                }
                assertEquals(Wizards.values()[i], table4p.getPlayers()[i].getAssistantDeck().get(j).getWizard());
                assertEquals(j + 1, table4p.getPlayers()[i].getAssistantDeck().get(j).getValue());
                assertEquals(motherNatureMov, table4p.getPlayers()[i].getAssistantDeck().get(j).getMotherNatureMovements());
            }
            motherNatureMov = 0;
        }
    }

    /**
     * Tests if mother nature is set correctly
     */
    @Test
    void setMotherNature() {
        table2p.setMotherNature(table2p.getIsland(0));
        assertEquals(0, table2p.motherNatureIsland().getId());
        assertEquals(1, table2p.getIslands().stream().filter(Island::isMotherNature).count());
    }

    /**
     * Tests if mother nature is moved correctly, checking also with a new group of islands
     */
    @Test
    void moveMotherNature() {
        table2p.setMotherNature(table2p.getIsland(9));
        table2p.moveMotherNature(5);
        assertEquals(2, table2p.motherNatureIsland().getId());

        table3p.setMotherNature(table3p.getIsland(5));
        table3p.moveMotherNature(10);
        assertEquals(3, table3p.motherNatureIsland().getId());

        table4p.setMotherNature(table4p.getIsland(3));
        table4p.moveMotherNature(2);
        assertEquals(5, table4p.motherNatureIsland().getId());

        //test after a new group of islands
        List<Island> islands = new ArrayList<>(Arrays.asList(table2p.getIsland(3), table2p.getIsland(4)));
        table2p.newIslandGroup(islands);
        table2p.setMotherNature(table2p.getIsland(9));
        table2p.moveMotherNature(5);
        assertEquals(3, table2p.motherNatureIsland().getId());
    }

    /**
     * Tests if professor is correctly got
     */
    @Test
    void getProfessor() {
        Professor prof = table2p.getProfessor(ColorS.BLUE);
        assertEquals(ColorS.BLUE, prof.getColor());
    }

    /**
     * Tests that the current player is set (and so got) correctly
     */
    @Test
    void getAndSetCurrentPlayer() {
        table2p.setCurrentPlayer(two.get(0));
        assertEquals(two.get(0), table2p.getCurrentPlayer());
    }

    /**
     * Tests if a new island group is created correctly (for different cases)
     * <ol>
     *     <li>Checks if islands have been removed</li>
     *     <li>Checks if students have been merged</li>
     *     <li>Checks what happens if mother nature was present on an island</li>
     *     <li>Checks if towers have been merged</li>
     *     <li>Checks if island IDs have been changed</li>
     * </ol>
     */
    @Test
    void newIslandGroup() {
        List<Island> twoIslands = new ArrayList<>(Arrays.asList(table2p.getIsland(3), table2p.getIsland(4)));
        Student s1 = new Student(ColorS.BLUE);
        Student s2 = new Student(ColorS.YELLOW);
        table2p.setMotherNature(table2p.getIsland(4));
        table2p.getIsland(3).setTower(new Tower(ColorT.BLACK, two.get(0)));
        table2p.getIsland(3).addStudent(s1);
        table2p.getIsland(4).addStudent(s2);
        Island island6 = table2p.getIsland(6);
        Island island2 = table2p.getIsland(2);
        table2p.newIslandGroup(twoIslands);

        //test islands removed
        assertEquals(11, table2p.getIslands().size());

        //test students merged
        assertTrue(table2p.getIsland(3).getStudents().contains(s1));
        assertTrue(table2p.getIsland(3).getStudents().contains(s2));

        //test mother nature is present on new island
        assertTrue(table2p.getIsland(3).isMotherNature());

        //test towers merged
        assertEquals(ColorT.BLACK, table2p.getIsland(3).getTower().getColor());
        assertEquals(2, table2p.getIsland(3).getNumOfTowers());

        //test id changed
        assertEquals(island6, table2p.getIsland(5));
        assertEquals(island2, table2p.getIsland(2));

        //test another case
        List<Island> threeIslands = new ArrayList<>(Arrays.asList(table3p.getIsland(10), table3p.getIsland(11), table3p.getIsland(0)));
        table3p.setMotherNature(table3p.getIsland(3));
        table3p.getIsland(10).setTower(new Tower(ColorT.BLACK, three.get(0)));
        table3p.getIsland(10).addStudent(s1);
        table3p.getIsland(0).addStudent(s2);
        Island island1 = table3p.getIsland(1);
        Island island9 = table3p.getIsland(9);
        table3p.newIslandGroup(threeIslands);

        //test islands removed
        assertEquals(10, table3p.getIslands().size());

        //test students merged
        assertTrue(table3p.getIsland(0).getStudents().contains(s1));
        assertTrue(table3p.getIsland(0).getStudents().contains(s2));

        //test mother nature is present on new island
        assertFalse(table3p.getIsland(0).isMotherNature());

        //test towers merged
        assertEquals(ColorT.BLACK, table3p.getIsland(0).getTower().getColor());
        assertEquals(3, table3p.getIsland(0).getNumOfTowers());

        //test id changed
        assertEquals(island1, table3p.getIsland(1));
        assertEquals(island9, table3p.getIsland(9));
    }

    /**
     * Tests if returns true when a group of islands can be unified
     */
    @Test
    void canIUnify() {
        Tower tower1 = new Tower(ColorT.BLACK,two.get(0));
        Tower tower2 = new Tower(ColorT.WHITE,two.get(1));
        List<Island> expIslandsToUnify = new ArrayList<>();
        expIslandsToUnify.add(this.table2p.getIslands().get(0));
        expIslandsToUnify.add(this.table2p.getIslands().get(1));
        expIslandsToUnify.add(this.table2p.getIslands().get(2));
        this.table2p.getIslands().get(0).setTower(tower1);
        this.table2p.getIslands().get(1).setTower(tower1);
        this.table2p.getIslands().get(2).setTower(tower1);
        this.table2p.getIslands().get(11).setTower(tower2);
        List<Island> islandsToUnify = this.table2p.canIUnify(this.table2p.getIslands().get(0));
        for(Island i : islandsToUnify){
            assertTrue(expIslandsToUnify.contains(i));
        }
        this.table2p.getIslands().get(11).setTower(tower1);
        expIslandsToUnify.add(this.table2p.getIslands().get(11));
        islandsToUnify = this.table2p.canIUnify(this.table2p.getIslands().get(0));
        for(Island i : islandsToUnify){
            assertTrue(expIslandsToUnify.contains(i));
        }
    }

    /**
     * Tests if mother nature island is got correctly
     */
    @Test
    void motherNatureIsland() {
        table2p.setMotherNature(table2p.getIsland(11));
        assertEquals(table2p.getIsland(11), table2p.motherNatureIsland());
    }

    /**
     * Tests if professor owner (for a specific color) is got correctly
     */
    @Test
    void getProfessorOwner() {
        Professor prof = table2p.getProfessor(ColorS.BLUE);
        prof.setOwner(two.get(1));
        assertEquals(two.get(1), table2p.getProfessorOwner(ColorS.BLUE));
    }

    /**
     * Tests if bag is got correctly
     * <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getBag() {
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if an island (with a specific ID) is got correctly
     */
    @Test
    void getIsland() {
        assertEquals(table2p.getIslands().get(10), table2p.getIsland(10));
        assertThrows(RuntimeException.class, () -> table2p.getIsland(13));
    }

    /**
     * Tests if supremacy on an island is calculated correctly (using mother nature island because
     * it has no students at the beginning of the game)
     * <ol>
     *     <li>Checks if returns the correct "king" of the island based on players' influence</li>
     *     <li>Checks what happens in parity case</li>
     *     <li>Checks the same for each type of match (4-players match tests the teams' influence)</li>
     * </ol>
     * <br>
     * <u>Tests also the private method: getInfluence()</u>
     */
    @Test
    void getSupremacy() throws ParityException{
        List<Student> students = new ArrayList<>(Arrays.asList(new Student(ColorS.YELLOW), new Student(ColorS.YELLOW), new Student(ColorS.BLUE)));

        //using mother nature island because there aren't any students at the beginning of the match
        int index2p = table2p.motherNatureIsland().getId();

        //case 2 players
        table2p.getProfessor(ColorS.BLUE).setOwner(two.get(0));
        table2p.getProfessor(ColorS.YELLOW).setOwner(two.get(1));
        table2p.getIsland(index2p).addStudents(students);
        assertEquals(two.get(1), table2p.getSupremacy(table2p.getIsland(index2p)));

        //parity case
        table2p.getIsland(index2p).addStudent(new Student(ColorS.BLUE));
        Exception exception = assertThrows(ParityException.class, () -> table2p.getSupremacy(table2p.getIsland(index2p)));
        assertEquals("there's a parity", exception.getMessage());

        //case 3 players
        int index3p = table3p.motherNatureIsland().getId();
        students.add(new Student(ColorS.GREEN));
        table3p.getProfessor(ColorS.BLUE).setOwner(three.get(0));
        table3p.getProfessor(ColorS.YELLOW).setOwner(three.get(1));
        table3p.getProfessor(ColorS.GREEN).setOwner(three.get(2));
        table3p.getIsland(index3p).addStudents(students);
        assertEquals(three.get(1), table3p.getSupremacy(table3p.getIsland(index3p)));

        //parity case
        table3p.getIsland(index3p).addStudent(new Student(ColorS.GREEN));
        exception = assertThrows(ParityException.class, () -> table3p.getSupremacy(table3p.getIsland(index3p)));
        assertEquals("there's a parity", exception.getMessage());

        //case 4 players
        int index4p = table4p.motherNatureIsland().getId();
        students.add(new Student(ColorS.YELLOW));
        students.add(new Student(ColorS.YELLOW));
        students.add(new Student(ColorS.RED));
        students.add(new Student(ColorS.RED));
        students.add(new Student(ColorS.BLUE));
        table4p.getProfessor(ColorS.BLUE).setOwner(four.get(0));
        table4p.getProfessor(ColorS.GREEN).setOwner(four.get(2));
        table4p.getProfessor(ColorS.RED).setOwner(four.get(2));
        table4p.getProfessor(ColorS.YELLOW).setOwner(four.get(1));
        table4p.getProfessor(ColorS.PINK).setOwner(four.get(3));
        table4p.getIsland(index4p).addStudents(students);
        assertEquals(four.get(0), table4p.getSupremacy(table4p.getIsland(index4p)));

        //parity case
        table4p.getIsland(index4p).addStudent(new Student(ColorS.PINK));
        exception = assertThrows(ParityException.class, () -> table4p.getSupremacy(table4p.getIsland(index4p)));
        assertEquals("there's a parity", exception.getMessage());

        //old owner case
        table4p.getIsland(index4p).setTower(new Tower(ColorT.BLACK, four.get(1)));
        assertEquals(four.get(1), table4p.getSupremacy(table4p.getIsland(index4p)));
    }

    /**
     * Tests if all islands are got correctly
     * <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getIslands() {
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if all players are got correctly
     * <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getPlayers() {
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if an island is correctly processed (so it calculates the supremacy on the island and then,
     * possibly, it creates a new island group)
     * <ol>
     *     <li>Creates a particular set of island on the table, it expects that two islands merge together</li>
     *     <li>Checks if two islands have been merged correctly</li>
     *     <li>Tests the end game condition: player with no towers</li>
     *     <li>Tests the end game condition: 3 islands remained</li>
     *     <li>Tests what happens if players has the same number of towers</li>
     * </ol>
     * <br>
     * <u>The three methods: getPlayerWithMinTowers(), getPlayerWithMaxProfessor() and endGame() can't be tested
     * because they don't change the model but they send a message. In this test they are only called</u>
     */
    @Test
    void processIsland(){
        table2p.getIsland(0).setTower(table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table2p.getIsland(1).setTower(table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table2p.getIsland(2).setTower(table2p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table2p.getIsland(2).addStudents(new ArrayList<>(Arrays.asList(new Student(ColorS.BLUE), new Student(ColorS.BLUE))));
        table2p.setProfessorOwner(ColorS.BLUE, table2p.getPlayers()[0]);
        table2p.processIsland(table2p.getIsland(2));
        assertEquals(10, table2p.getIslands().size());

        //test end game case: player with no towers
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.getIsland(1).addStudents(new ArrayList<>(Arrays.asList(new Student(ColorS.BLUE), new Student(ColorS.BLUE))));
        table2p.processIsland(table2p.getIsland(1));
        assertTrue(table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().getTowers().isEmpty());


        //test end game case: 3 islands remained
        table3p.getIsland(0).setTower(table3p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(1).setTower(table3p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(2).setTower(table3p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(3).setTower(table3p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(4).setTower(table3p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(5).setTower(table3p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(6).setTower(table3p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(7).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(8).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(9).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(10).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(11).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.processIsland(table3p.getIsland(7));
        table3p.processIsland(table3p.getIsland(4));
        table3p.processIsland(table3p.getIsland(0));

        //test end game case: 3 islands remained and parity case
        table3p = new Table(three);
        table3p.getIsland(0).setTower(table3p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(1).setTower(table3p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(2).setTower(table3p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(3).setTower(table3p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(4).setTower(table3p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(5).setTower(table3p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(6).setTower(table3p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(7).setTower(table3p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(8).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(9).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(10).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());
        table3p.getIsland(11).setTower(table3p.getPlayers()[2].getSchoolBoard().getTowerBoard().removeLastTower());

        three.get(0).getSchoolBoard().getProfessorTable().addProfessor(new Professor(ColorS.RED));
        table3p.processIsland(table3p.getIsland(11));
        table3p.processIsland(table3p.getIsland(7));
        table3p.processIsland(table3p.getIsland(0));
    }

    /**
     * Tests if students are added to cloud correctly
     */
    @Test
    void addStudentsToCloud(){
        //considering that on table2p there are already three students
        table2p.addStudentsToCloud(table2p.getClouds().get(0));
        assertEquals(6, table2p.getClouds().get(0).getStudents().size());

        //considering that on table3p there are already four students
        table3p.addStudentsToCloud(table3p.getClouds().get(0));
        assertEquals(8, table3p.getClouds().get(0).getStudents().size());

        //considering that on table4p there are already three students
        table4p.addStudentsToCloud(table4p.getClouds().get(0));
        assertEquals(6, table4p.getClouds().get(0).getStudents().size());
    }

    /**
     * Tests if all clouds on the table are got correctly
     * <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getClouds(){
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if mother nature is got correctly
     * <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getMotherNature(){
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests if a player can take a professor
     * <br>
     * <u>Tests also the private method: getNumberOfStudents()</u>
     */
    @Test
    void setProfessorOwner(){
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
        assertEquals(two.get(0), table2p.getProfessorOwner(ColorS.YELLOW));

        table2p.getPlayers()[1].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        table2p.setProfessorOwner(ColorS.YELLOW, table2p.getPlayers()[1]);
        assertEquals(two.get(1), table2p.getProfessorOwner(ColorS.YELLOW));
    }

    /**
     * Tests if an assistant card is correctly played
     * <ol>
     *     <li>Checks if an assistant card played is on the top of the discard pile</li>
     *     <li>Checks what happens if the next player plays the same card</li>
     *     <li>Checks what happens if all assistants have been already played</li>
     * </ol>
     */
    @Test
    void playAssistant() throws AssistantNotFoundException, GameException {
        table2p.setCurrentPlayer(table2p.getPlayers()[0]);
        table2p.playAssistant(table2p.getPlayers()[0].getAssistant(3));
        assertEquals(3, table2p.getPlayers()[0].getDiscardPile().peek().getValue());
        table2p.setCurrentPlayer(table2p.getPlayers()[1]);
        Exception e = assertThrows(GameException.class, () -> table2p.playAssistant(table2p.getPlayers()[1].getAssistant(3)));
        assertEquals("Not playable assistant", e.getMessage());

        //case all assistant already played
        table3p.setCurrentPlayer(table3p.getPlayers()[0]);
        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(1));
        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(2));
        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(3));
        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(4));
        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(5));
        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(6));
        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(7));
        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(8));

        table3p.getPlayers()[1].addToDiscardPile(table3p.getPlayers()[1].getAssistant(9));

        table3p.getPlayers()[2].addToDiscardPile(table3p.getPlayers()[2].getAssistant(10));

        table3p.getPlayers()[0].addToDiscardPile(table3p.getPlayers()[0].getAssistant(10));

        assertEquals(10, table3p.getPlayers()[0].getDiscardPile().peek().getValue());
    }

    /**
     * Tests if next player is correctly set
     */
    @Test
    void nextPlayer(){
        table2p.nextPlayer();
        assertEquals(table2p.getPlayers()[1], table2p.getCurrentPlayer());

        table3p.nextPlayer();
        assertEquals(table3p.getPlayers()[1], table3p.getCurrentPlayer());

        table4p.nextPlayer();
        assertEquals(table4p.getPlayers()[2], table4p.getCurrentPlayer());
    }

    /**
     * Tests if an island is valid (tests only the corner cases that thrown exceptions)
     */
    @Test
    void validIsland(){
        assertThrows(GameException.class, () -> table2p.validIsland(13));
        assertThrows(GameException.class, () -> table2p.validIsland(-1));
    }

    /**
     * Tests if a cloud is valid (tests only the corner cases that thrown exceptions)
     */
    @Test
    void validCloud(){
        assertThrows(GameException.class, () -> table2p.validCloud(3));
        assertThrows(GameException.class, () -> table2p.validCloud(-1));
    }

    /**
     * Tests if model is reduced correctly
     */
    @Test
    void createReducedModel() {
        ReducedModel reducedModel = table2p.createReducedModel();

        List<ReducedIsland> islands = table2p.getIslands().stream().map(Island::reduceIsland).collect(Collectors.toList());

        List<ReducedCloud> clouds = new ArrayList<>();
        for (int i = 0; i < table2p.getClouds().size(); i++){
            Cloud cloud = table2p.getClouds().get(i);
            clouds.add(new ReducedCloud(i, cloud.getStudents().stream().map(Student::getColor).collect(Collectors.toList())));
        }

        List<ReducedBoard> boards = new ArrayList<>();
        for (Player p : table2p.getPlayers()){
            boards.add(p.reduceBoard());
        }

        //check same current player
        assertEquals(two.get(0).getUsername(), reducedModel.getCurrentPlayer());

        //check same island values
        for (int i = 0; i < 12; i++){
            assertEquals(islands.get(i).getId(), reducedModel.getIslands().get(i).getId());
            assertEquals(islands.get(i).getStudents(), reducedModel.getIslands().get(i).getStudents());
            assertEquals(islands.get(i).getTower(), reducedModel.getIslands().get(i).getTower());
            assertEquals(islands.get(i).getNumOfTowers(), reducedModel.getIslands().get(i).getNumOfTowers());
        }

        //check same cloud values
        for (int i = 0; i < 2; i++){
            assertEquals(clouds.get(i).getId(), reducedModel.getClouds().get(i).getId());
            assertEquals(clouds.get(i).getStudents(), reducedModel.getClouds().get(i).getStudents());
        }

        //check same boards values
        for (int i = 0; i < 2; i++){
            assertEquals(boards.get(i).getStudents(), reducedModel.getBoards().get(i).getStudents());
            assertEquals(boards.get(i).getEntranceStudents(), reducedModel.getBoards().get(i).getEntranceStudents());
            assertEquals(boards.get(i).getProfessors(), reducedModel.getBoards().get(i).getProfessors());
        }
    }

    /**
     * Tests if game starts correctly (this method can't be tested because it doesn't change the model)
     */
    @Test
    void startGame(){
        table2p.startGame();
    }

    /**
     * Tests if students are added correctly to the entrance (at the beginning of each round)
     */
    @Test
    void addStudentsToEntrance() throws AssistantNotFoundException, GameException {
        List<Student> students = table2p.getClouds().get(0).getStudents();

        //set action phase
        table2p.playAssistant(table2p.getPlayers()[0].getAssistant(1));
        table2p.playAssistant(table2p.getPlayers()[1].getAssistant(2));

        Player currentPlayer = table2p.getCurrentPlayer();
        table2p.addStudentsToEntrance(table2p.getClouds().get(0));

        assertTrue(currentPlayer.getSchoolBoard().getEntrance().getStudents().containsAll(students));
        assertEquals(two.get(1), table2p.getCurrentPlayer());

        //check if in the planning phase clouds are re-filled
        table2p.addStudentsToEntrance(table2p.getClouds().get(1));
        assertFalse(table2p.getClouds().get(0).getStudents().isEmpty());
        assertFalse(table2p.getClouds().get(1).getStudents().isEmpty());

        //endgame condition: check if endGame() is called: winner should be null
        table2p.getPlayers()[1].getAssistantDeck().clear();
        assertThrows(NullPointerException.class, () -> table2p.addStudentsToEntrance(table2p.getClouds().get(0)));
    }

    /**
     * Tests if a specific player is the current player
     */
    @Test
    void checkCurrentPlayer(){
        assertThrows(GameException.class, () -> table2p.checkCurrentPlayer(two.get(1).getUsername()));
    }

    /**
     * Tests if returns the correct boolean "lastRound"
     */
    @Test
    void isLastRound(){
        assertFalse(table2p.isLastRound());
    }

    /**
     * Tests what happens if it's the last round of the match (this method can't be tested because it doesn't change the model)
     */
    @Test
    void lastRound() throws AssistantNotFoundException, GameException {
        //set action phase
        table2p.playAssistant(table2p.getPlayers()[0].getAssistant(1));
        table2p.playAssistant(table2p.getPlayers()[1].getAssistant(2));

        assertEquals(two.get(0), table2p.getCurrentPlayer());
        table2p.lastRound();
        assertEquals(two.get(1), table2p.getCurrentPlayer());

        //check if endGame() is called: winner should be null
        assertThrows(NullPointerException.class, () -> table2p.lastRound());
    }
}