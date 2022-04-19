package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private Table table2p;
    private Table table3p;
    private Table table4p;
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
        table2p = new Table(two);
        table3p = new Table(three);
        table4p = new Table(four);
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
    void moreThanFourPlayers(){
        List<Player> players = new ArrayList<>(Arrays.asList(new Player("1"), new Player("2"), new Player("3"), new Player("4"), new Player("5")));
        Exception e = assertThrows(RuntimeException.class, () -> new Table(players));
        System.out.println(e.getMessage());
    }

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
        assertEquals(ColorT.BLACK, table4p.getPlayers()[1].getTowerColor());
        assertEquals(ColorT.WHITE, table4p.getPlayers()[2].getTowerColor());
        assertEquals(ColorT.WHITE, table4p.getPlayers()[3].getTowerColor());
    }

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
        int motherNatureId = table2p.getMotherNature().getIsland().getId();
        int oppositeId =  (motherNatureId + 6) % 12;
        for (Island i : table2p.getIslands()){
            if(i.getId() != motherNatureId && i.getId() != oppositeId){
                assertEquals(1, i.getStudents().size());
            }
            else{
                assertEquals(0, i.getStudents().size());
            }
        }

        motherNatureId = table3p.getMotherNature().getIsland().getId();
        oppositeId =  (motherNatureId + 6) % 12;
        for (Island i : table3p.getIslands()){
            if(i.getId() != motherNatureId && i.getId() != oppositeId){
                assertEquals(1, i.getStudents().size());
            }
            else{
                assertEquals(0, i.getStudents().size());
            }
        }

        motherNatureId = table4p.getMotherNature().getIsland().getId();
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

    @Test
    void setupClouds() {
        //test if number of clouds equals number of players
        assertEquals(2, table2p.getClouds().size());
        assertEquals(3, table3p.getClouds().size());
        assertEquals(4, table4p.getClouds().size());
    }

    @Test
    void setupStudents() {
        //test number of students remained in the bag after table instantiation, for each type of table
        assertEquals(106, table2p.getBag().getStudents().size());
        assertEquals(93, table3p.getBag().getStudents().size());
        assertEquals(92, table4p.getBag().getStudents().size());
    }

    @Test
    void setupProfessors() {
        for (ColorS c : ColorS.values()){
            assertNotNull(table2p.getProfessor(c));
            assertNotNull(table3p.getProfessor(c));
            assertNotNull(table4p.getProfessor(c));
        }
    }

    @Test
    void setupSchoolBoards() {
        //test SchoolBoard created for each player (for each type of table)
        for (int i=0; i<table2p.getPlayers().length; i++){
            assertNotNull(table2p.getPlayers()[i].getSchoolBoard());
            assertEquals(two.get(i), table2p.getPlayers()[i].getSchoolBoard().getPlayer());
        }

        for (int i=0; i<table3p.getPlayers().length; i++){
            assertNotNull(table3p.getPlayers()[i].getSchoolBoard());
            assertEquals(three.get(i), table3p.getPlayers()[i].getSchoolBoard().getPlayer());
        }

        for (int i=0; i<table4p.getPlayers().length; i++){
            assertNotNull(table4p.getPlayers()[i].getSchoolBoard());
            assertEquals(four.get(i), table4p.getPlayers()[i].getSchoolBoard().getPlayer());
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

    @Test
    void setMotherNature() {
        table2p.setMotherNature(table2p.getIsland(0));
        assertEquals(0, table2p.motherNatureIsland().getId());
        assertEquals(1, table2p.getIslands().stream().filter(Island::isMotherNature).count());
    }

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

    @Test
    void getProfessor() {
        Professor prof = table2p.getProfessor(ColorS.BLUE);
        assertEquals(ColorS.BLUE, prof.getColor());
    }

    @Test
    void getAndSetCurrentPlayer() {
        table2p.setCurrentPlayer(two.get(0));
        assertEquals(two.get(0), table2p.getCurrentPlayer());
    }

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

    @Test
    void motherNatureIsland() {
        table2p.setMotherNature(table2p.getIsland(11));
        assertEquals(table2p.getIsland(11), table2p.motherNatureIsland());
    }

    @Test
    void getPlayerWithMinTowers() throws ParityException {
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().getTowers().remove(0);
        assertEquals(two.get(0), table2p.getPlayerWithMinTowers());
        table2p.getPlayers()[1].getSchoolBoard().getTowerBoard().getTowers().remove(0);
        Exception exception = assertThrows(ParityException.class, () -> table2p.getPlayerWithMinTowers());
        assertEquals("there's a parity", exception.getMessage());
    }

    @Test
    void getPlayerWithMaxProfessor() {
        table2p.getPlayers()[0].getSchoolBoard().getProfessorTable().addProfessor(new Professor(ColorS.BLUE));
        assertEquals(two.get(0), table2p.getPlayerWithMaxProfessor());
    }

    @Test
    void getProfessorOwner() {
        Professor prof = table2p.getProfessor(ColorS.BLUE);
        prof.setOwner(two.get(1));
        assertEquals(two.get(1), table2p.getProfessorOwner(ColorS.BLUE));
    }

    @Test
    void getBag() {
        assertTrue(true, "tested in other methods");
    }

    @Test
    void getIsland() {
        assertEquals(table2p.getIslands().get(10), table2p.getIsland(10));
        Exception e = assertThrows(RuntimeException.class, () -> table2p.getIsland(13));
        System.out.println(e.getMessage());
    }

    @Test
    void getSupremacy() throws ParityException{
        List<Student> students = new ArrayList<>(Arrays.asList(new Student(ColorS.YELLOW), new Student(ColorS.YELLOW), new Student(ColorS.BLUE)));

        //using mother nature island because there aren't any students at the beginning of the match
        int index2p = table2p.getMotherNature().getIsland().getId();

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
        int index3p = table3p.getMotherNature().getIsland().getId();
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
        int index4p = table4p.getMotherNature().getIsland().getId();
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

    @Test
    void getIslands() {
        assertTrue(true, "tested in other methods");
    }

    @Test
    void getPlayers() {
        assertTrue(true, "tested in other methods");
    }

    @Test
    void processIsland(){
        table2p.getIsland(0).setTower(table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table2p.getIsland(1).setTower(table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower());
        table2p.getIsland(2).setTower(table2p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower());
        table2p.getIsland(2).addStudents(new ArrayList<>(Arrays.asList(new Student(ColorS.BLUE), new Student(ColorS.BLUE))));
        table2p.setProfessorOwner(ColorS.BLUE, table2p.getPlayers()[0]);
        table2p.processIsland(table2p.getIsland(2));
        assertEquals(10, table2p.getIslands().size());

        //test end game case: player with no towers (tbd)
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.getIsland(1).addStudents(new ArrayList<>(Arrays.asList(new Student(ColorS.BLUE), new Student(ColorS.BLUE))));
        table2p.processIsland(table2p.getIsland(1));
        System.out.println(table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().getTowers().size());
        assertTrue(table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().getTowers().isEmpty());


        //test end game case: 3 islands remained (tbd)
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
    }

    @Test
    void addStudentsToCloud(){
        table2p.addStudentsToCloud(table2p.getClouds().get(0));
        assertEquals(3, table2p.getClouds().get(0).getStudents().size());

        table3p.addStudentsToCloud(table3p.getClouds().get(0));
        assertEquals(4, table3p.getClouds().get(0).getStudents().size());

        table4p.addStudentsToCloud(table4p.getClouds().get(0));
        assertEquals(3, table4p.getClouds().get(0).getStudents().size());
    }

    @Test
    void getClouds(){
        assertTrue(true, "tested in other methods");
    }

    @Test
    void getMotherNature(){
        assertTrue(true, "tested in other methods");
    }

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

    @Test
    void playAssistant() throws AssistantNotFoundException, AssistantNotPlayableException {
        table2p.setCurrentPlayer(table2p.getPlayers()[0]);
        table2p.playAssistant(table2p.getPlayers()[0].getAssistant(3));
        assertEquals(3, table2p.getPlayers()[0].getDiscardPile().peek().getValue());
        table2p.setCurrentPlayer(table2p.getPlayers()[1]);
        Exception e = assertThrows(AssistantNotPlayableException.class, () -> table2p.playAssistant(table2p.getPlayers()[1].getAssistant(3)));
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

    @Test
    void nextPlayer(){
        table2p.nextPlayer();
        assertEquals(table2p.getPlayers()[1], table2p.getCurrentPlayer());

        table3p.nextPlayer();
        assertEquals(table3p.getPlayers()[1], table3p.getCurrentPlayer());

        table4p.nextPlayer();
        assertEquals(table4p.getPlayers()[1], table4p.getCurrentPlayer());
    }

    @Test
    void validIsland(){
        Exception exception = assertThrows(IslandNotValidException.class, () -> table2p.validIsland(13));
        assertEquals("Not valid Island", exception.getMessage());

        Exception exception1 = assertThrows(IslandNotValidException.class, () -> table2p.validIsland(-1));
        assertEquals("Not valid Island", exception1.getMessage());
    }

    @Test
    void validCloud(){
        Exception exception = assertThrows(CloudNotValidException.class, () -> table2p.validCloud(3));
        assertEquals("Not valid cloud", exception.getMessage());

        Exception exception1 = assertThrows(CloudNotValidException.class, () -> table2p.validCloud(-1));
        assertEquals("Not valid cloud", exception1.getMessage());
    }

    //TODO: implement endgame
    @Test
    void endGame(){
        table2p.getPlayers()[0].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.endGame();

        //case of parity
        table2p.getPlayers()[1].getSchoolBoard().getTowerBoard().removeLastTower();
        table2p.endGame();
    }
}