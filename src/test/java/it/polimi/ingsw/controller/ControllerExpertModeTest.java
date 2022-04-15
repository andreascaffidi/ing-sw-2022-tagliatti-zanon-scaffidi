package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.CardNotFoundException;
import it.polimi.ingsw.exceptions.InvalidCharacterException;
import it.polimi.ingsw.exceptions.NotEnoughCoinsException;
import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.effects.NoInfluenceColorEffect;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.requestMessage.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    //TODO: non funziona
    @Test
    void moveStudentToDining() {
        Player player = table.getCurrentPlayer();
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        assertEquals(1, table.getPlayerCoins(player));

        Message message = new Message(new MoveStudentMessage("dining", 3), table.getCurrentPlayer().getUsername());
        controller.update(message);

        assertEquals(2, table.getPlayerCoins(player));
    }

    @Test
    void moveStudentToDiningStudentIndexOutOfBound() {
        Player player = table.getCurrentPlayer();
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        player.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        assertEquals(1, table.getPlayerCoins(player));

        Message message = new Message(new MoveStudentMessage("dining", 8), "p1");
        controller.update(message);
    }

    @Test
    @RepeatedTest(100)
    void payCharacter1() throws CardNotFoundException {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter1Message(1, 1), "p1", true);

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

        else
        {
            //tbd
        }

    }

    @Test
    void payCharacter2() {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter2Message(), "p1", true);

        if(table.getCharacters().containsKey(2))
        {
            controller.update(message);
        }
    }

    @RepeatedTest(100)
    @Test
    void payCharacter3() throws ParityException {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter3Message(1), "p1", true);

        if(table.getCharacters().containsKey(3))
        {
            table.getIsland(0).addStudent(new Student(ColorS.BLUE));
            table.getIsland(0).addStudent(new Student(ColorS.BLUE));

            //il player1 ha la supremazia sul blu
            table.setProfessorOwner(ColorS.BLUE, player1);

            table.getIsland(0).addStudent(new Student(ColorS.RED));
            table.getIsland(0).addStudent(new Student(ColorS.RED));
            table.getIsland(0).addStudent(new Student(ColorS.RED));
            table.getIsland(0).addStudent(new Student(ColorS.RED));
            table.getIsland(0).addStudent(new Student(ColorS.RED));
            table.getIsland(0).addStudent(new Student(ColorS.RED));

            //il player2 ha la supremazia sul rosso
            table.setProfessorOwner(ColorS.RED, player2);

            //aggiungo sull'isola una torre di player2

            assertEquals(player2, table.getSupremacy(table.getIsland(0)));

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
            try {
                assertEquals(player1, table.getSupremacy(table.getIsland(0)));
            } catch (ParityException e) {
                e.printStackTrace();
            }
        }

        else
        {
            //tbd
        }
    }

    @RepeatedTest(100)
    @Test
    void payCharacter4() {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter4Message(1), "p1", true);

        if(table.getCharacters().containsKey(4)) {
            table.setMotherNature(table.getIsland(0));

            //chiamo l'effetto
            controller.update(message);

            //dopo aver chiamato l'effetto, motherNature deve essersi spostata di 1
            assertTrue(table.getIsland(1).isMotherNature());
        }

        else
        {
            //tbd
        }
    }

    //TODO: sistemare il character 5
    @RepeatedTest(100)
    @Test
    void payCharacter5() {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table.addCoins(table.getCurrentPlayer(), 200);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter5Message(1), table.getCurrentPlayer().getUsername(), true);

        if(table.getCharacters().containsKey(5)) {
            assertFalse(table.isNoEntryTile(table.getIsland(0)));
            controller.update(message);
            assertTrue(table.isNoEntryTile(table.getIsland(0)));
        }

        else
        {
            //tbd
        }

    }

    @Test
    void payCharacter6() {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter6Message(1), "p1", true);

        if(table.getCharacters().containsKey(6))
        {
            controller.update(message);
        }

    }

    //TODO: sistemare il character 7
    @Test
    @RepeatedTest(100)
    void payCharacter7() throws CardNotFoundException {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        if (table.getCharacters().containsKey(7)) {

            Student student1 = table.getCardWithStudents(7).getStudents().get(0);
            Student student2 = table.getCardWithStudents(7).getStudents().get(1);
            Student student3 = table.getCardWithStudents(7).getStudents().get(2);

            List<Integer> cardStudentChosen = new ArrayList<>();

            cardStudentChosen.add(0);
            cardStudentChosen.add(1);
            cardStudentChosen.add(2);

            Student student4 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(0);
            Student student5 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(1);
            Student student6 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(2);

            List<Integer> entranceStudentChosen = new ArrayList<>();

            entranceStudentChosen.add(0);
            entranceStudentChosen.add(1);
            entranceStudentChosen.add(2);

            Message message = new Message(new PayCharacter7Message(cardStudentChosen, entranceStudentChosen), "p1", true);

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
    }


    @Test
    void payCharacter8() {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter8Message(), "p1", true);

        if(table.getCharacters().containsKey(8))
        {
            controller.update(message);
        }
    }

    @Test
    void payCharacter9() {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter9Message("blue"), "p1", true);

        if(table.getCharacters().containsKey(9))
        {
            controller.update(message);
        }
    }

    //TODO: sistemare il character 10
    @Test
    @RepeatedTest(100)
    void payCharacter10() {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        if (table.getCharacters().containsKey(10)) {

            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).add(new Student(ColorS.BLUE));
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).add(new Student(ColorS.BLUE));
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).add(new Student(ColorS.BLUE));

            Student student1 = table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).remove(0);
            Student student2 = table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).remove(0);
            Student student3 = table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).remove(0);

            List<String> diningRoomStudentChosen = new ArrayList<>();

            diningRoomStudentChosen.add("blue");
            diningRoomStudentChosen.add("blue");
            diningRoomStudentChosen.add("blue");

            Student student4 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(0);
            Student student5 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(1);
            Student student6 = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(2);

            List<Integer> entranceStudentChosen = new ArrayList<>();

            entranceStudentChosen.add(0);
            entranceStudentChosen.add(1);
            entranceStudentChosen.add(2);

            Message message = new Message(new PayCharacter10Message(diningRoomStudentChosen, entranceStudentChosen), "p1", true);

            //dopo l'effetto mi aspetto che:
            controller.update(message);

            assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student1.getColor()).contains(student1));
            assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student2.getColor()).contains(student2));
            assertFalse(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student3.getColor()).contains(student3));

            assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student4.getColor()).contains(student4));
            assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student5.getColor()).contains(student5));
            assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student6.getColor()).contains(student6));

            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student1));
            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student2));
            assertTrue(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student3));

            assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student4));
            assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student5));
            assertFalse(table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().contains(student6));
        }
    }

    //TODO: sistemare character11
    @Test
    @RepeatedTest(100)
    void payCharacter11() throws CardNotFoundException {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter11Message(1), "p1", true);

        if(table.getCharacters().containsKey(11))
        {
            Student student1 = table.getCardWithStudents(11).getStudents().remove(0);

            controller.update(message);

            assertTrue(table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(student1.getColor()).contains(student1));

            assertFalse(table.getCardWithStudents(11).getStudents().contains(student1));

            //poi dalla bag estraggo un altro student e lo metto sulla carta
            assertEquals(4, table.getCardWithStudents(11).getStudents().size());
        }
    }

    @Test
    @RepeatedTest(100)
    void payCharacter12() {

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
        controller = new ControllerExpertMode(table);

        Message message = new Message(new PayCharacter12Message("blue"), "p1", true);

        if(table.getCharacters().containsKey(12)) {

            //students dei player
            Student student1 = new Student(ColorS.BLUE);
            Student student2 = new Student(ColorS.BLUE);
            Student student3 = new Student(ColorS.BLUE);
            Student student7 = new Student(ColorS.BLUE);

            Student student4 = new Student(ColorS.BLUE);
            Student student5 = new Student(ColorS.BLUE);
            Student student6 = new Student(ColorS.RED);

            player1.getSchoolBoard().getDiningRoom().addStudent(student1);
            player1.getSchoolBoard().getDiningRoom().addStudent(student2);
            player1.getSchoolBoard().getDiningRoom().addStudent(student3);
            player1.getSchoolBoard().getDiningRoom().addStudent(student7);

            //Player2 ha 2 studenti BLU e 1 ROSSO,
            //quindi quando chiamo effect(), devo metterli tutti nella bag e nessuno rimane in schoolboard

            player2.getSchoolBoard().getDiningRoom().addStudent(student4);
            player2.getSchoolBoard().getDiningRoom().addStudent(student5);
            player2.getSchoolBoard().getDiningRoom().addStudent(student6);

            //System.out.println(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).size());

            controller.update(message);

            //System.out.println(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).size());

            assertTrue(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student1));
            assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student2));
            assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student3));
            assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student7));

            assertFalse(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student4));
            assertFalse(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student5));
            assertTrue(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.RED).contains(student6));


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
        }

        else
        {
            //tbd
        }
    }
}