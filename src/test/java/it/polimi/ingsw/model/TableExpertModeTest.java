package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.effects.*;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.network.client.reducedModel.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TableExpertModeTest {

    private TableExpertMode table2p;
    private TableExpertMode table3p;
    private TableExpertMode table4p;
    private List<Player> two;
    private List<Player> three;
    private List<Player> four;

    /**
     *  Initialises four different table (expert mode), one for each match's number of player
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
        table2p = new TableExpertMode(two);
        table3p = new TableExpertMode(three);
        table4p = new TableExpertMode(four);
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
     * Tests if character cards are correctly set up (test is repeated because the choice of the cards is random)
     */
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

    /**
     * Tests if students on card are correctly set up, when the character card should have students on it
     * (test is repeated because the choice of the cards is random)
     */
    @RepeatedTest(20)
    void setupStudentsOnCard() throws CardNotFoundException {
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
            assertThrows(CardNotFoundException.class, () -> table2p.getCardWithStudents(3));
        }
    }

    /**
     * Tests if card with students on it is got correctly
     * <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void getCardWithStudent(){
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests that coins are added and paid correctly, checking also the bank amount
     */
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

    /**
     * Tests if a card has been incremented after the first use
     */
    @Test
    void incrementCardCost(){
        if (!table2p.getCharacters().containsKey(10)){
            table2p.getCharacters().put(10, 1);
        }
        table2p.incrementCardCost(10);
        assertEquals(2, table2p.getCharacters().get(10));

        //character card can be incremented only the first time
        table2p.incrementCardCost(10);
        assertEquals(2, table2p.getCharacters().get(10));
    }

    /**
     * Tests if an effect is set and reset correctly
     * <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void setAndResetCurrentEffect(){
        assertTrue(true, "tested in other methods");
    }

    /**
     * Tests what happens when there is an effect placed on an island on which calculate supremacy
     * <ol>
     *     <li>Checks what happens when there is a NoEntryTile placed on an island</li>
     *     <li>Checks what happens when there is a AdditionalInfluence placed on an island</li>
     *     <li>Checks what happens when there is a NoInfluenceColorEffect placed on an island</li>
     *     <li>Checks what happens when there is a CountTowersEffect placed on an island</li>
     * </ol>
     */
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

    /**
     * Tests what happens when an island has a NoEntryTile on it and a new group of islands has to be created from it
     */
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

    /**
     * Tests what happens if a MoveMotherNature effect is activated
     */
    @Test
    void moveMotherNature(){
        table2p.setCurrentEffect(new AdditionalMovementEffect(2));
        int motherNatureIsland = table2p.motherNatureIsland().getId();
        table2p.moveMotherNature(3);
        int newIsland = (motherNatureIsland + 5) % 12;
        assertEquals(newIsland, table2p.motherNatureIsland().getId());
    }

    /**
     * Tests what happens if a ProfessorTie effect is activated
     */
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

    /**
     * Tests if a character card is valid for the current match
     */
    @Test
    void invalidCharacter(){
        for (int i = 1; i < 13; i++){
            if(!table2p.getCharacters().containsKey(i)){
                int character = i;
                assertThrows(GameException.class, () -> table2p.validCharacter(character));
            }
        }
    }

    /**
     * Tests if player hasn't got enough coins to pay a specific character
     */
    @Test
    void notEnoughCoins() throws GameException {
        table2p.getCharacters().put(13, 20);
        assertThrows(GameException.class, () -> table2p.validCharacter(13));
        table2p.getCharacters().put(14, 1);
        table2p.validCharacter(14);
    }

    /**
     * Tests if an additional movement chosen (from AdditionalMovement effect) is valid
     */
    @Test
    void validAdditionalMovement() throws GameException {
        table2p.validAdditionalMovement(1);
        table2p.validAdditionalMovement(2);
        assertThrows(GameException.class, () -> table2p.validAdditionalMovement(0));
        assertThrows(GameException.class, () -> table2p.validAdditionalMovement(3));
    }

    /**
     * Tests if a NoEntryTile is valid to be placed on a specific island, checks if there are two NoEntryTile on the same island
     * and if there are more than 4 NoEntryTile in this match
     */
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

    /**
     * Tests if model (expert mode) is reduced correctly
     */
    @Test
    void createReducedModel(){
        table2p.setCurrentEffect(new AdditionalMovementEffect(2));
        table2p.setNoEntryTile(table2p.getIsland(0), true);
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

        ReducedModelExpertMode reducedModelExpertMode = (ReducedModelExpertMode) reducedModel;

        //check coins value
        for (String player : reducedModelExpertMode.getCoins().keySet()){
            assertEquals(1, reducedModelExpertMode.getCoins().get(player));
        }

        //check NoEntryTile position
        for (Integer island : reducedModelExpertMode.getNoEntryTiles().keySet()){
            if (island == 0){
                assertTrue(reducedModelExpertMode.getNoEntryTiles().get(island));
            }else {
                assertFalse(reducedModelExpertMode.getNoEntryTiles().get(island));
            }
        }

        //check current effect
        String effect = "Current player can move mother nature up to 2 movements more";
        assertEquals(effect, reducedModelExpertMode.getCurrentEffect());

        //check character cards
        List<Integer> characters = reducedModelExpertMode.getCharacters().stream().map(ReducedCharacter::getId).collect(Collectors.toList());
        for (int character : table2p.getCharacters().keySet()){
            assertTrue(characters.contains(character));
        }
    }
}