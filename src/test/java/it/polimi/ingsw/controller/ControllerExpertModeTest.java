package it.polimi.ingsw.controller;


import it.polimi.ingsw.exceptions.CardNotFoundException;
import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
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

    @AfterEach
    void tearDown() {
        table = null;
        controller = null;
    }

    @Test
    void update() {
        assertTrue(true, "tested in all other ControllerExpertMode methods");
    }

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

        //tbd -> exceptions
    }

    @Test
    void chooseCloud(){
        table.getCharacters().put(2, 2);
        table.addCoins(table.getCurrentPlayer(), 1);

        PayCharacter2Message pay = new PayCharacter2Message();
        ControllerMessage message = new ControllerMessage(pay, "p1");

        controller.update(message);

        ControllerMessage message2 = new ControllerMessage(new ChooseCloudMessage(1), "p1");
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));
        table.getClouds().get(0).addStudent(new Student(ColorS.PINK));

        controller.update(message2);

        //nothing to test
    }

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

        //tbd -> exceptions
    }

    @RepeatedTest(100)
    void payCharacter1() throws CardNotFoundException {

        ControllerMessage message = new ControllerMessage(new PayCharacter1Message(1, 1), "p1");

        if(table.getCharacters().containsKey(1))
        {
            Student student1 = table.getCardWithStudents(1).getStudents().get(0);

            controller.update(message);

            //l'island scelta contenga lo studente scelto
            assertTrue(table.getIsland(0).getStudents().contains(student1));

            //la carta non contenga più lo studente scelto
            assertFalse(table.getCardWithStudents(1).getStudents().contains(student1));

            //poi dalla bag estraggo un altro student e lo metto sulla carta
            assertEquals(4, table.getCardWithStudents(1).getStudents().size());
        }

        //tbd -> exceptions

    }

    @Test
    void payCharacter2() {

        table.getCharacters().put(2,2);
        table.addCoins(table.getCurrentPlayer(), 1);

        PayCharacter2Message pay = new PayCharacter2Message();
        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(2, pay.getCharacter());
        controller.update(message);

        //nothing to test

        //tbd -> exceptions
    }

    @Test
    void payCharacter3() throws ParityException {

        table.getCharacters().put(3, 3);
        table.addCoins(table.getCurrentPlayer(), 2);

        PayCharacter3Message pay = new PayCharacter3Message(1);

        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(1, pay.getIslandId());
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));

        //il player1 ha la supremazia sul blu
        table.setProfessorOwner(ColorS.BLUE, players.get(0));

        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));
        table.getIsland(0).addStudent(new Student(ColorS.RED));

        //il player2 ha la supremazia sul rosso
        table.setProfessorOwner(ColorS.RED, players.get(1));

        //aggiungo sull'isola una torre di player2

        assertEquals(players.get(1), table.getSupremacy(table.getIsland(0)));

        //assumo che l'isola scelta non abbia sopra madre natura
        table.getIsland(0).setMotherNature(false);

        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));
        table.getIsland(0).addStudent(new Student(ColorS.BLUE));

        //dopo la chiamata del metodo
        controller.update(message);

        //la supremazia dovrà essere del player1
        assertEquals(players.get(0), table.getSupremacy(table.getIsland(0)));

        //tbd -> exceptions
    }

    @Test
    void payCharacter4() {

        table.getCharacters().put(4, 1);

        ControllerMessage message = new ControllerMessage(new PayCharacter4Message(1), "p1");

        table.setMotherNature(table.getIsland(0));

        //chiamo l'effetto
        controller.update(message);

        //da testare l'effetto

        //tbd -> exceptions
    }

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

        //tbd -> exceptions

    }

    @Test
    void payCharacter6() {

        table.getCharacters().put(6, 3);
        table.addCoins(table.getCurrentPlayer(), 2);

        PayCharacter6Message pay = new PayCharacter6Message(1);

        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(1, pay.getIslandId());
        assertEquals(6, pay.getCharacter());
        controller.update(message);

        //nothing to test

        //tbd -> exceptions

    }

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

            //dopo l'effetto mi aspetto che:
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
        }

        //tbd -> exceptions
    }


    @Test
    void payCharacter8() {

        table.getCharacters().put(8, 2);
        table.addCoins(table.getCurrentPlayer(), 1);

        PayCharacter8Message pay = new PayCharacter8Message();
        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(8, pay.getCharacter());
        controller.update(message);

        //nothing to test

        //tbd -> exceptions
    }

    @Test
    void payCharacter9() {

        table.getCharacters().put(9, 3);
        table.addCoins(table.getCurrentPlayer(), 2);

        PayCharacter9Message pay = new PayCharacter9Message(ColorS.BLUE);

        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(ColorS.BLUE, pay.getColor());
        assertEquals(9, pay.getCharacter());
        controller.update(message);

        //nothing to test

        //tbd -> exceptions
    }

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

        //dopo l'effetto mi aspetto che:
        controller.update(message);

        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student1.getColor()).contains(student1));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student2.getColor()).contains(student2));

        assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student3.getColor()).contains(student3));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student4.getColor()).contains(student4));

        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student1));
        assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student2));

        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student3));
        assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student4));

        //tbd -> exceptions
    }

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

            //poi dalla bag estraggo un altro student e lo metto sulla carta
            assertEquals(4, table.getCardWithStudents(11).getStudents().size());
        }

        //tbd -> exceptions
    }

    @Test
    void payCharacter12() {

        table.getCharacters().put(12, 3);
        table.addCoins(table.getCurrentPlayer(), 2);

        PayCharacter12Message pay = new PayCharacter12Message(ColorS.BLUE);
        ControllerMessage message = new ControllerMessage(pay, "p1");

        assertEquals(ColorS.BLUE, pay.getColor());
        assertEquals(12, pay.getCharacter());
        //students dei player
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student7 = new Student(ColorS.BLUE);

        Student student4 = new Student(ColorS.BLUE);
        Student student5 = new Student(ColorS.BLUE);
        Student student6 = new Student(ColorS.RED);

        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student1);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student2);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student3);
        players.get(0).getSchoolBoard().getDiningRoom().addStudent(student7);

        //Player2 ha 2 studenti BLU e 1 ROSSO,
        //quindi quando chiamo effect(), devo metterli tutti nella bag e nessuno rimane in schoolboard

        players.get(1).getSchoolBoard().getDiningRoom().addStudent(student4);
        players.get(1).getSchoolBoard().getDiningRoom().addStudent(student5);
        players.get(1).getSchoolBoard().getDiningRoom().addStudent(student6);

        //System.out.println(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).size());

        controller.update(message);

        //System.out.println(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).size());

        assertTrue(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student1));
        assertFalse(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student2));
        assertFalse(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student3));
        assertFalse(players.get(0).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student7));

        assertFalse(players.get(1).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student4));
        assertFalse(players.get(1).getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student5));
        assertTrue(players.get(1).getSchoolBoard().getDiningRoom().getLine(ColorS.RED).contains(student6));


        assertFalse(table.getBag().getStudents().contains(student1));
        assertTrue(table.getBag().getStudents().contains(student2));
        assertTrue(table.getBag().getStudents().contains(student3));
        assertTrue(table.getBag().getStudents().contains(student7));

        //Player2 ha 2 studenti BLU e 1 ROSSO,
        //quindi quando chiamo effect(), devo metterli tutti nella bag e nessuno rimane in schoolboard

        //character.effect(table);

        assertTrue(table.getBag().getStudents().contains(student4));
        assertTrue(table.getBag().getStudents().contains(student5));
        assertFalse(table.getBag().getStudents().contains(student6));

        //tbd -> exceptions
    }


}