package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.islands.Island;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Character4Test {
    private Character4 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character4();
        players = new ArrayList<Player>();
        player1 = new Player("1");
        player2 = new Player("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {

        table.setMotherNature(table.getIsland(0));
        //setto il movimento addizionale a 1
        character.setAdditionalMovements(1);

        //chiamo l'effetto
        character.effect(this.table);

        //dopo aver chiamato l'effetto, motherNature deve essersi spostata di 1
        assertEquals(1, table.motherNatureIsland().getId());
    }

    @Test
    void setup() {
        assertTrue(true, "not needed");
    }

    @Test
    void setAndGetAdditionalMovements() {
        character.setAdditionalMovements(1);
        assertEquals(character.getAdditionalMovements(), 1);
    }
}