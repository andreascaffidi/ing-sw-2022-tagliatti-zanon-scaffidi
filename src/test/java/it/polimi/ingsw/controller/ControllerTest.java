package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.AssistantNotFoundException;
import it.polimi.ingsw.exceptions.WrongPlayerException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.TypeOfMessage;
import it.polimi.ingsw.network.messages.PlayAssistantMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;
    private Table table;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player("p1");
        player2 = new Player("p2");

        List<Player> players = new ArrayList<Player>(Arrays.asList(player1, player2));

        table = new Table(players);

        controller = new Controller(table);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void playAssistant() throws AssistantNotFoundException {
        table.setCurrentPlayer(player1);
        Message message = new Message(new PlayAssistantMessage(3), "p1");
        controller.update(message);
        assertThrows(AssistantNotFoundException.class, () -> table.getCurrentPlayer().getAssistant(3));
        assertEquals(3, table.getCurrentPlayer().getDiscardPile().peek().getValue());

        table.setCurrentPlayer(player2);
        Message message2 = new Message(new PlayAssistantMessage(3), "p1");
        assertThrows(WrongPlayerException.class, () -> controller.playAssistant((PlayAssistantMessage) message2.getTypeOfMessage(), message2.getUsername()));
    }

    @Test
    void moveStudentToIsland() {
    }

    @Test
    void moveStudentToDining() {
    }

    @Test
    void moveMotherNature() {
    }

    @Test
    void chooseCloud() {
    }
}