package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
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
        two = new ArrayList<Player>(Arrays.asList(p1,p2));
        three = new ArrayList<Player>(Arrays.asList(p3,p4,p5));
        four = new ArrayList<Player>(Arrays.asList(p6,p7,p8,p9));
        table2p = new Table(two);
        table3p = new Table(three);
        table4p = new Table(four);
    }

    @AfterEach
    void tearDown() {
        table2p = null;
        table3p = null;
        table4p = null;
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
    void setupSchoolboards() {
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
            assertEquals(8, table2p.getPlayers()[i].getSchoolBoard().getTowers().getTowers().size());
            for (int j=0; j<8; j++){
                assertEquals(table2p.getPlayers()[i].getTowerColor(), table2p.getPlayers()[i].getSchoolBoard().getTowers().getTowers().get(j).getColor());
            }
        }

        for (int i=0; i<table3p.getPlayers().length; i++){
            assertEquals(6, table3p.getPlayers()[i].getSchoolBoard().getTowers().getTowers().size());
            for (int j=0; j<6; j++){
                assertEquals(table3p.getPlayers()[i].getTowerColor(), table3p.getPlayers()[i].getSchoolBoard().getTowers().getTowers().get(j).getColor());
            }
        }

        //test case 4 players is different
        for (int i=0; i<table4p.getPlayers().length; i++){
            if (i<2) {
                assertEquals(8, table4p.getPlayers()[i].getSchoolBoard().getTowers().getTowers().size());
                for (int j=0; j<8; j++){
                    assertEquals(table4p.getPlayers()[i].getTowerColor(), table4p.getPlayers()[i].getSchoolBoard().getTowers().getTowers().get(j).getColor());
                }
            }
            else
            {
                assertEquals(0, table4p.getPlayers()[i].getSchoolBoard().getTowers().getTowers().size());
            }
        }
    }

    @Test
    void setupAssistantCards() {
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
    }

    @Test
    void motherNatureIsland() {
        table2p.setMotherNature(table2p.getIsland(11));
        assertEquals(table2p.getIsland(11), table2p.motherNatureIsland());
    }

    @Test
    void getPlayerWithMinTowers() {
        table2p.getPlayers()[0].getSchoolBoard().getTowers().getTowers().remove(0);
        assertEquals(two.get(0), table2p.getPlayerWithMinTowers());

        //todo: testare caso di parità
    }

    @Test
    void getPlayerWithMaxProfessor() {
        table2p.getPlayers()[0].getSchoolBoard().getProfessorTable().addProfessor(new Professor(ColorS.BLUE));
        assertEquals(two.get(0), table2p.getPlayerWithMaxProfessor());

        //todo: testare caso di parità
    }

    @Test
    void professorOwner() {
        Professor prof = table2p.getProfessor(ColorS.BLUE);
        prof.setOwner(two.get(1));
        assertEquals(two.get(1), table2p.professorOwner(ColorS.BLUE));
    }

    @Test
    void getBag() {
        assertTrue(true, "tested in other methods");
    }

    @Test
    void getIsland() {
        assertEquals(table2p.getIslands().get(10), table2p.getIsland(10));
    }

    @Test
    void getSupremacy() {
        List<Student> students = new ArrayList<>(Arrays.asList(new Student(ColorS.YELLOW), new Student(ColorS.YELLOW), new Student(ColorS.YELLOW), new Student(ColorS.BLUE)));
        table2p.getProfessor(ColorS.BLUE).setOwner(two.get(0));
        table2p.getProfessor(ColorS.YELLOW).setOwner(two.get(1));
        table2p.getIsland(3).addStudents(students);
        assertEquals(two.get(1), table2p.getSupremacy(table2p.getIsland(3)));

        //todo: testare caso di parità
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
}