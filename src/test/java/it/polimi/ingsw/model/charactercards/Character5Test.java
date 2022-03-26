package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Character5Test {

    private Character5 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;
    private static final int MAX_ENTRY_TILE = 4;

    @BeforeEach
    void init() {
        character = new Character5();
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect(TableExpertMode table) {
        //l'isola scelta è la numero 1
        character.setIslandChosen(1);

        //il numero di entrytiles è 1
        character.setNumOfEntryTile(1);

        //se chiamo l'effetto allora
        effect(this.table);

        //nell'isola scelta entrytile è true
        assertTrue(table.getIsland(character.getIslandChosen()).isEntryTile());

        //e incremento il numero di entrytiles piazzati
        assertEquals(character.getNumOfEntryTile(), 2);

        //se invece metto il numero di entrytiles a 4
        character.setNumOfEntryTile(4);

        //se rimetto l'entry tile dell'isola scelta a false
        table.getIsland(character.getIslandChosen()).setEntryTile(false);

        //e non ho un entrytile sull'isola scelta
        if(!table.getIsland(character.getIslandChosen()).isEntryTile())
        {
            //dopo aver chiamato il metodo
            effect(this.table);

            //non cambia nulla sull'isola scelta
            assertFalse(table.getIsland(character.getIslandChosen()).isEntryTile());

            //e non varia il numero di entrytiles globali
            assertEquals(character.getNumOfEntryTile(), 4);
        }
    }

    @Test
    void setup() {
        assertTrue(true, "not needed");
    }

    @Test
    void setAndGetIslandChosen() {
        character.setIslandChosen(1);
        assertEquals(character.getIslandChosen(),1);
    }

    @Test
    void setAndGetNumOfEntryTile() {
        character.setNumOfEntryTile(1);
        assertEquals(character.getNumOfEntryTile(),1);
    }

}