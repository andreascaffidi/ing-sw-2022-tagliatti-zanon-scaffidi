package it.polimi.ingsw.controller;


import it.polimi.ingsw.exceptions.CardNotFoundException;
import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.effects.AdditionalMovementEffect;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.requests.gameMessages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExpertModeTest {

    private ControllerExpertMode controller;
    private TableExpertMode table;
    private List<Player> players;

    /**
     *  Initialises two different players, a table and a controller (expert mode)
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void setUp() {
        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);
    }

    /**
     * Sets to null every attribute
     *  <br>
     *  <u>It's called after each test</u>
     */
    @AfterEach
    void tearDown() {
        table = null;
        controller = null;
    }

    /**
     * Tests if a message is received from the connection and handled correctly
     *  <u>This method is implicitly tested by other tests</u>
     */
    @Test
    void update() {
        assertTrue(true, "tested in all other ControllerExpertMode methods");
    }

    /**
     * Tests if player gets a coin adding three student (same color) to the dining room
     */
    @Test
    void moveStudentToDining() {
        Player player = table.getCurrentPlayer();
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));

        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.GREEN));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.GREEN));

        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.RED));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.RED));

        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));

        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.PINK));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.PINK));

        assertEquals(1, table.getPlayerCoins(player));

        Map<Integer,String> movement = new HashMap<>();
        movement.put(3, "DINING ROOM");
        ControllerMessage message = new ControllerMessage(new MoveStudentMessage(movement), table.getCurrentPlayer().getUsername());
        controller.update(message);

        assertEquals(2, table.getPlayerCoins(player));
    }

    /**
     * Tests IndexOutOfBoundException in moveStudentToDining()
     */
    @Test
    void moveStudentToDiningStudentIndexOutOfBound() {
        Player player = table.getCurrentPlayer();
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        assertEquals(1, table.getPlayerCoins(player));

        Map<Integer,String> movement = new HashMap<>();
        movement.put(8, "DINING");
        ControllerMessage message = new ControllerMessage(new MoveStudentMessage(movement), "p1");
        controller.update(message);

        //check if IndexOutOfBoundException is caught (nothing happens)
        assertEquals(1, table.getPlayerCoins(player));
    }

    /**
     * Tests if current effect is reset correctly, setting an effect and checking that doesn't work
     */
    @Test
    void chooseCloud(){
        table.getCharacters().put(2, 2);
        table.addCoins(table.getCurrentPlayer(), 1);
        table.setCurrentEffect(new AdditionalMovementEffect(2));

        PayCharacter2Message pay = new PayCharacter2Message();
        ControllerMessage message = new ControllerMessage(pay, "p1");

        controller.update(message);

        ControllerMessage message2 = new ControllerMessage(new ChooseCloudMessage(1), "p1");
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));

        controller.update(message2);

        //check effect doesn't work
        int motherNatureIsland = table.motherNatureIsland().getId();
        table.moveMotherNature(2);
        assertEquals((motherNatureIsland + 2) % 12, table.motherNatureIsland().getId());
    }

    /**
     * Tests if player pays coins correctly
     */
    @Test
    void pay(){
        Player player = table.getCurrentPlayer();
        assertEquals(1, table.getPlayerCoins(player));
        table.addCoins(player, 12);

        table.getCharacters().put(12, 3);

        ControllerMessage message = new ControllerMessage(new PayCharacter12Message(ColorS.BLUE), "p1");
        controller.update(message);

        assertEquals(10, table.getPlayerCoins(player));

        ControllerMessage message2 = new ControllerMessage(new PayCharacter12Message(ColorS.BLUE), "p1");
        controller.update(message2);

        //check player can't pay another character in the same round
        assertEquals(10, table.getPlayerCoins(player));

        //check if boolean "characterAlreadyPlayed" is reset to false
        ControllerMessage message3 = new ControllerMessage(new ChooseCloudMessage(1), "p1");
        controller.update(message3);

        Player player2 = table.getCurrentPlayer();
        assertEquals(1, table.getPlayerCoins(player2));
        table.addCoins(player2, 12);

        //remember that this card has incremented its cost
        ControllerMessage message4 = new ControllerMessage(new PayCharacter12Message(ColorS.BLUE), "p2");
        controller.update(message4);

        assertEquals(9, table.getPlayerCoins(player2));
    }

    /**
     * Tests if character 1 has activated correctly its effect (repeated because character cards are chosen randomly)
     * <ol>
     *     <li>Creates the payCharacter1 message</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if students have been switched correctly</li>
     *     <li>Checks if a new student has been drawn from the bag</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @RepeatedTest(100)
    void payCharacter1() throws CardNotFoundException {

        ControllerMessage message = new ControllerMessage(new PayCharacter1Message(1, 1), "p1");

        if(table.getCharacters().containsKey(1))
        {
            Student student1 = table.getCardWithStudents(1).getStudents().get(0);

            controller.update(message);

            //check student on island
            assertTrue(table.getIsland(0).getStudents().contains(student1));

            //check student is removed form the card
            assertFalse(table.getCardWithStudents(1).getStudents().contains(student1));

            //check if a new student has been drawn and placed on the card
            assertEquals(4, table.getCardWithStudents(1).getStudents().size());
        }

        //check if GameException is caught (nothing happens)
        ControllerMessage message2 = new ControllerMessage(new PayCharacter1Message(20, 1), "p1");

        if(table.getCharacters().containsKey(1))
        {
            Student student1 = table.getCardWithStudents(1).getStudents().get(0);
            controller.update(message2);
            assertFalse(table.getIsland(0).getStudents().contains(student1));
            assertTrue(table.getCardWithStudents(1).getStudents().contains(student1));
        }

    }

    /**
     * Tests if character 2 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter2 message</li>
     *     <li>Controller handles the message</li>
     *     <li>Sets three yellow students to player 2</li>
     *     <li>Checks if the effect owner wins the parity case</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @Test
    void payCharacter2() {

        table.getCharacters().put(2,2);
        table.addCoins(table.getCurrentPlayer(), 1);

        PayCharacter2Message pay = new PayCharacter2Message();
        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(2, pay.getCharacter());
        controller.update(message);

        //set yellow professor to player 2
        for (int i = 0; i < 3; i++) {
            table.getPlayers()[1].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        }
        table.setProfessorOwner(ColorS.YELLOW, table.getPlayers()[1]);
        assertEquals(players.get(1), table.getProfessorOwner(ColorS.YELLOW));

        //create a parity case for yellow professor and check that player 1 wins anyway
        for (int i = 0; i < 3; i++) {
            table.getPlayers()[0].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        }
        table.setProfessorOwner(ColorS.YELLOW, table.getPlayers()[0]);
        assertEquals(players.get(0), table.getProfessorOwner(ColorS.YELLOW));

        //check if GameException is caught (nothing happens)
        table = new TableExpertMode(players);
        table.getCharacters().remove(2);
        ControllerMessage message2 = new ControllerMessage(new PayCharacter2Message(), "p1");
        controller.update(message2);

        for (int i = 0; i < 3; i++) {
            table.getPlayers()[1].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        }
        table.setProfessorOwner(ColorS.YELLOW, table.getPlayers()[1]);

        for (int i = 0; i < 3; i++) {
            table.getPlayers()[0].getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.YELLOW));
        }
        table.setProfessorOwner(ColorS.YELLOW, table.getPlayers()[0]);
        assertEquals(players.get(1), table.getProfessorOwner(ColorS.YELLOW));
    }

    /**
     * Tests if character 3 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter3 message with an island chosen</li>
     *     <li>Controller handles the message</li>
     *     <li>Sets supremacy to player 2</li>
     *     <li>Adds more students to make player 1 win</li>
     *     <li>Checks if supremacy has been changed after the handling of the message</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @Test
    void payCharacter3() throws ParityException {

        table.getCharacters().put(3, 3);
        table.addCoins(table.getCurrentPlayer(), 2);

        PayCharacter3Message pay = new PayCharacter3Message(1);

        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(1, pay.getIslandId());
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));

        //player 1 gets blue professor and player 2 gets red professor
        table.setProfessorOwner(ColorS.BLUE, players.get(0));
        table.setProfessorOwner(ColorS.RED, players.get(1));

        //player 2 gets the supremacy on the island chosen
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        assertEquals(players.get(1), table.getSupremacy(table.getIsland(0)));

        //adding other students to the island
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));

        //check player 1 gets the supremacy after the handling of the message
        controller.update(message);
        assertEquals(players.get(0), table.getIsland(0).getTower().getOwner());

        //check if GameException is caught (nothing happens)
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        assertEquals(players.get(1), table.getSupremacy(table.getIsland(0)));
        table.getIsland(0).setTower(new Tower(ColorT.BLACK, players.get(1)));

        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));

        table.resetCurrentEffect();
        table.getCharacters().remove(3);
        ControllerMessage message2 = new ControllerMessage(new PayCharacter3Message(1), "p1");
        controller.update(message2);
        assertEquals(players.get(1), table.getIsland(0).getTower().getOwner());
    }

    /**
     * Tests if character 4 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter4 message with a movement chosen</li>
     *     <li>Controller handles the message</li>
     *     <li>Check if mother nature has been moved correctly</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @Test
    void payCharacter4() {
        table.getCharacters().put(4, 1);
        ControllerMessage message = new ControllerMessage(new PayCharacter4Message(1), "p1");
        controller.update(message);

        int motherNatureIsland = table.motherNatureIsland().getId();
        table.moveMotherNature(3);
        int newIsland = (motherNatureIsland + 4) % 12;
        assertEquals(newIsland, table.motherNatureIsland().getId());

        //check if GameException is caught (nothing happens)
        table.resetCurrentEffect();
        table.getCharacters().remove(4);
        ControllerMessage message2 = new ControllerMessage(new PayCharacter4Message(1), "p1");
        controller.update(message2);

        table.moveMotherNature(3);
        int newIsland2 = (newIsland + 3) % 12;
        assertEquals(newIsland2, table.motherNatureIsland().getId());

    }

    /**
     * Tests if character 5 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter5 message with an island chosen</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if there is a NoEntryTile on teh chosen island</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @Test
    void payCharacter5() {
        table.getCharacters().put(5, 2);
        table.addCoins(table.getCurrentPlayer(), 1);
        PayCharacter5Message pay = new PayCharacter5Message(1);
        ControllerMessage message = new ControllerMessage(pay, table.getCurrentPlayer().getUsername());
        assertEquals(1, pay.getIslandId());
        assertFalse(table.isNoEntryTile(table.getIsland(0)));
        controller.update(message);
        assertTrue(table.isNoEntryTile(table.getIsland(0)));

        //check if GameException is caught (nothing happens)
        table.getCharacters().remove(5);
        ControllerMessage message2 = new ControllerMessage(new PayCharacter5Message(2), "p1");
        assertFalse(table.isNoEntryTile(table.getIsland(1)));
        controller.update(message2);
        assertFalse(table.isNoEntryTile(table.getIsland(1)));
    }

    /**
     * Tests if character 6 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter6 message with an island chosen</li>
     *     <li>Controller handles the message</li>
     *     <li>Sets a case of parity with a tower on the island</li>
     *     <li>Checks if tower isn't counted</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @Test
    void payCharacter6() throws ParityException {
        table.getCharacters().put(6, 3);
        table.addCoins(table.getCurrentPlayer(), 2);
        int motherNatureIsland = table.motherNatureIsland().getId();
        PayCharacter6Message pay = new PayCharacter6Message(motherNatureIsland+1);
        ControllerMessage message = new ControllerMessage(pay, "p1");
        assertEquals(motherNatureIsland+1, pay.getIslandId());
        assertEquals(6, pay.getCharacter());


        //set a case of parity thanks to the tower on th island
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table.motherNatureIsland().addStudent(new Student(ColorS.YELLOW));
        table.motherNatureIsland().setTower(new Tower(ColorT.WHITE, players.get(1)));
        table.getProfessor(ColorS.BLUE).setOwner(players.get(0));
        table.getProfessor(ColorS.YELLOW).setOwner(players.get(1));

        //check case of parity
        Exception exception = assertThrows(ParityException.class, () -> table.getSupremacy(table.motherNatureIsland()));
        assertEquals("there's a parity", exception.getMessage());

        //handle the message -> player 1 wins because tower is no longer counted
        controller.update(message);
        assertEquals(players.get(0), table.getSupremacy(table.motherNatureIsland()));

        //check if GameException is caught (nothing happens)
        table = new TableExpertMode(players);
        table.getCharacters().remove(6);
        ControllerMessage message2 = new ControllerMessage(new PayCharacter6Message(motherNatureIsland+1), "p1");

        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table.motherNatureIsland().addStudent(new Student(ColorS.YELLOW));
        table.motherNatureIsland().setTower(new Tower(ColorT.WHITE, players.get(1)));
        table.getProfessor(ColorS.BLUE).setOwner(players.get(0));
        table.getProfessor(ColorS.YELLOW).setOwner(players.get(1));
        controller.update(message2);
        assertThrows(ParityException.class, () -> table.getSupremacy(table.motherNatureIsland()));
    }

    /**
     * Tests if character 7 has activated correctly its effect (repeated because character cards are chosen randomly)
     * <ol>
     *     <li>Creates the payCharacter7 message</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if students have been switched correctly</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @RepeatedTest(100)
    void payCharacter7() throws CardNotFoundException {

        if (table.getCharacters().containsKey(7)) {

            Student student1 = table.getCardWithStudents(7).getStudents().get(0);
            Student student2 = table.getCardWithStudents(7).getStudents().get(1);
            Student student3 = table.getCardWithStudents(7).getStudents().get(2);

            List<Integer> cardStudentChosen = new ArrayList<>();

            cardStudentChosen.add(1);
            cardStudentChosen.add(2);
            cardStudentChosen.add(3);

            Student student4 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(0);
            Student student5 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(1);
            Student student6 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(2);

            List<Integer> entranceStudentChosen = new ArrayList<>();

            entranceStudentChosen.add(1);
            entranceStudentChosen.add(2);
            entranceStudentChosen.add(3);

            ControllerMessage message = new ControllerMessage(new PayCharacter7Message(cardStudentChosen, entranceStudentChosen), "p1");

            //after handling the message it's expected to:
            controller.update(message);

            assertFalse(table.getCardWithStudents(7).getStudents().contains(student1));
            assertFalse(table.getCardWithStudents(7).getStudents().contains(student2));
            assertFalse(table.getCardWithStudents(7).getStudents().contains(student3));

            assertTrue(table.getCardWithStudents(7).getStudents().contains(student4));
            assertTrue(table.getCardWithStudents(7).getStudents().contains(student5));
            assertTrue(table.getCardWithStudents(7).getStudents().contains(student6));

            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student1));
            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student2));
            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student3));

            assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student4));
            assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student5));
            assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student6));

            //check if GameException is caught (nothing happens)
            table.getCharacters().remove(7);
            ControllerMessage message2 = new ControllerMessage(new PayCharacter7Message(entranceStudentChosen, cardStudentChosen), "p1");
            controller.update(message2);

            assertTrue(table.getCardWithStudents(7).getStudents().contains(student4));
            assertTrue(table.getCardWithStudents(7).getStudents().contains(student5));
            assertTrue(table.getCardWithStudents(7).getStudents().contains(student6));

            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student1));
            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student2));
            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student3));
        }
    }

    /**
     * Tests if character 8 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter8 message</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if additional influence has been given to current player</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @Test
    void payCharacter8() throws ParityException {
        table.getCharacters().put(8, 2);
        table.addCoins(table.getCurrentPlayer(), 1);
        PayCharacter8Message pay = new PayCharacter8Message();
        ControllerMessage message = new ControllerMessage(pay, "p1");
        assertEquals(8, pay.getCharacter());

        table.getProfessor(ColorS.RED).setOwner(players.get(0));
        table.getProfessor(ColorS.BLUE).setOwner(players.get(1));
        table.motherNatureIsland().addStudent(new Student(ColorS.RED));
        table.motherNatureIsland().addStudent(new Student(ColorS.RED));
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        assertEquals(players.get(1), table.getSupremacy(table.motherNatureIsland()));

        controller.update(message);
        assertEquals(players.get(0), table.getSupremacy(table.motherNatureIsland()));

        //check if GameException is caught (nothing happens)
        table.resetCurrentEffect();
        table.getCharacters().remove(8);
        ControllerMessage message2 = new ControllerMessage(new PayCharacter8Message(), "p1");
        controller.update(message2);
        assertEquals(players.get(1), table.getSupremacy(table.motherNatureIsland()));
    }

    /**
     * Tests if character 9 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter9 message</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if color chosen ha no influence on supremacy calculation</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @Test
    void payCharacter9() throws ParityException {

        table.getCharacters().put(9, 3);
        table.addCoins(table.getCurrentPlayer(), 2);
        PayCharacter9Message pay = new PayCharacter9Message(ColorS.BLUE);
        ControllerMessage message = new ControllerMessage(pay, "p1");
        assertEquals(ColorS.BLUE, pay.getColor());
        assertEquals(9, pay.getCharacter());

        table.getPlayers()[0].getSchoolBoard().getProfessorTable().addProfessor(table.getProfessor(ColorS.RED));
        table.getPlayers()[1].getSchoolBoard().getProfessorTable().addProfessor(table.getProfessor(ColorS.BLUE));
        table.getProfessor(ColorS.RED).setOwner(players.get(0));
        table.getProfessor(ColorS.BLUE).setOwner(players.get(1));
        table.motherNatureIsland().addStudent(new Student(ColorS.RED));
        table.motherNatureIsland().addStudent(new Student(ColorS.RED));
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        table.motherNatureIsland().addStudent(new Student(ColorS.BLUE));
        assertEquals(players.get(1), table.getSupremacy(table.motherNatureIsland()));

        controller.update(message);
        assertEquals(players.get(0), table.getSupremacy(table.motherNatureIsland()));

        //check if GameException is caught (nothing happens)
        table.resetCurrentEffect();
        table.getCharacters().remove(9);
        ControllerMessage message2 = new ControllerMessage(new PayCharacter9Message(ColorS.BLUE), "p1");
        controller.update(message2);
        assertEquals(players.get(1), table.getSupremacy(table.motherNatureIsland()));
    }

    /**
     * Tests if character 10 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter10 message</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if students have been switched correctly</li>
     *     <li>Checks also GameException thrown case (when dining room is empty)</li>
     * </ol>
     */
    @Test
    void payCharacter10() {

        table.getCharacters().put(10, 1);

        table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).add(new Student(ColorS.BLUE));
        table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).add(new Student(ColorS.BLUE));

        Student student1 = table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).get(0);
        Student student2 = table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).get(1);

        List<ColorS> diningRoomStudentChosen = new ArrayList<>();

        diningRoomStudentChosen.add(ColorS.BLUE);
        diningRoomStudentChosen.add(ColorS.BLUE);

        Student student3 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(0);
        Student student4 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(1);

        List<Integer> entranceStudentChosen = new ArrayList<>();

        entranceStudentChosen.add(1);
        entranceStudentChosen.add(2);

        ControllerMessage message = new ControllerMessage(new PayCharacter10Message(diningRoomStudentChosen, entranceStudentChosen), "p1");

        //after handling the message it's expected to:
        controller.update(message);

        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student1.getColor()).contains(student1));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student2.getColor()).contains(student2));

        assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student3.getColor()).contains(student3));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student4.getColor()).contains(student4));

        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student1));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student2));

        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student3));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student4));

        //check if GameException is caught (nothing happens)
        table = new TableExpertMode(players);
        table.getCharacters().put(10, 1);

        List<ColorS> diningRoomStudentChosen2 = new ArrayList<>();
        diningRoomStudentChosen2.add(ColorS.BLUE);
        diningRoomStudentChosen2.add(ColorS.BLUE);

        Student student5 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(0);
        Student student6 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(1);
        List<Integer> entranceStudentChosen2 = new ArrayList<>();
        entranceStudentChosen2.add(1);
        entranceStudentChosen2.add(2);

        ControllerMessage message2 = new ControllerMessage(new PayCharacter10Message(diningRoomStudentChosen2, entranceStudentChosen2), "p1");
        controller.update(message2);

        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student5.getColor()).contains(student5));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student6.getColor()).contains(student6));

        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student5));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student6));

    }

    /**
     * Tests if character 11 has activated correctly its effect (repeated because character cards are chosen randomly)
     * <ol>
     *     <li>Creates the payCharacter11 message</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if students have been moved to dining correctly</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @RepeatedTest(100)
    void payCharacter11() throws CardNotFoundException {

        PayCharacter11Message pay = new PayCharacter11Message(1);
        ControllerMessage message = new ControllerMessage(pay, "p1");
        table.addCoins(table.getCurrentPlayer(), 1);

        if(table.getCharacters().containsKey(11))
        {
            assertEquals(1, pay.getStudentId());
            Student student1 = table.getCardWithStudents(11).getStudents().get(0);
            controller.update(message);

            assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student1.getColor()).contains(student1));
            assertFalse(table.getCardWithStudents(11).getStudents().contains(student1));

            //check another student is drawn form the bag
            assertEquals(4, table.getCardWithStudents(11).getStudents().size());

            //check if GameException is caught (nothing happens)
            table.getCharacters().remove(11);
            ControllerMessage message2 = new ControllerMessage(new PayCharacter11Message(1), "p1");
            Student student2 = table.getCardWithStudents(11).getStudents().get(0);
            controller.update(message2);

            assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student2.getColor()).contains(student2));
            assertTrue(table.getCardWithStudents(11).getStudents().contains(student2));
        }
    }

    /**
     * Tests if character 12 has activated correctly its effect (this character is added manually)
     * <ol>
     *     <li>Creates the payCharacter12 message</li>
     *     <li>Controller handles the message</li>
     *     <li>Checks if students have been moved to the bag correctly</li>
     *     <li>Checks also GameException thrown case</li>
     * </ol>
     */
    @Test
    void payCharacter12() {

        table.getCharacters().put(12, 3);
        table.addCoins(table.getCurrentPlayer(), 2);

        PayCharacter12Message pay = new PayCharacter12Message(ColorS.BLUE);
        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(ColorS.BLUE, pay.getColor());
        assertEquals(12, pay.getCharacter());

        //setting player 1 students on his dining room
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student4 = new Student(ColorS.BLUE);

        //setting player 2 students on his dining room
        Student student5 = new Student(ColorS.BLUE);
        Student student6 = new Student(ColorS.BLUE);
        Student student7 = new Student(ColorS.RED);

        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student1);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student2);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student3);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student4);

        players.get(1).getSchoolBoard().getDiningRoom().addStudent(student5);
        players.get(1).getSchoolBoard().getDiningRoom().addStudent(student6);
        players.get(1).getSchoolBoard().getDiningRoom().addStudent(student7);

        controller.update(message);

        //player 1 has only one blue student on dining room
        assertTrue(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student1));
        assertFalse(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student2));
        assertFalse(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student3));
        assertFalse(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student4));

        //player 2 has only one red student on dining room
        assertFalse(players.get(1).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student5));
        assertFalse(players.get(1).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student6));
        assertTrue(players.get(1).getSchoolBoard().getDiningRoom().getLine(ColorS.RED).contains(student7));

        //students have been moved to the bag
        assertTrue(table.getBag().getStudents().contains(student2));
        assertTrue(table.getBag().getStudents().contains(student3));
        assertTrue(table.getBag().getStudents().contains(student4));
        assertTrue(table.getBag().getStudents().contains(student5));

        //check if GameException is caught (nothing happens)
        Student student8 = new Student(ColorS.BLUE);
        Student student9 = new Student(ColorS.BLUE);
        Student student10 = new Student(ColorS.RED);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student8);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student9);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student10);
        table.getCharacters().remove(12);

        ControllerMessage message2 = new ControllerMessage(new PayCharacter12Message(ColorS.BLUE), "p1");
        controller.update(message2);

        assertTrue(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student8));
        assertTrue(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student9));
        assertTrue(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.RED).contains(student10));
    }

}