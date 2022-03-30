package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;


class Character3Test {

    private Character3 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character3();
        players = new ArrayList<Player>();
        player1 = new Player("1");
        player2 = new Player("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {

        int islandChosen = 4;

        //aggiungo sull'isola due student BLU
        table.getIsland(islandChosen).addStudent(new Student(ColorS.BLUE));
        table.getIsland(islandChosen).addStudent(new Student(ColorS.BLUE));
        table.getIsland(islandChosen).addStudent(new Student(ColorS.BLUE));

        //il player1 ha la supremazia sul blu
        table.setProfessorOwner(ColorS.BLUE, player1);

        table.getIsland(islandChosen).addStudent(new Student(ColorS.RED));

        //il player2 ha la supremazia sul rosso
        table.setProfessorOwner(ColorS.RED, player2);

        //quindi il professor owner è effettivamente player1
        assertEquals(player2, table.getProfessorOwner(ColorS.RED));

        //aggiungo sull'isola una torre di player2

        System.out.println(table.getIsland(4).getNumOfTowers());
        table.getIsland(4).setTower(new Tower(ColorT.BLACK, player2));

        //assumo che l'isola scelta non abbia sopra madre natura
        character.setIslandChosen(islandChosen);
        table.getIsland(islandChosen).setMotherNature(false);

        //dopo la chiamata del metodo
        character.effect(table);

        int i = this.table.getInfluence(table.getIsland(islandChosen), player1);
        int j = this.table.getInfluence(table.getIsland(islandChosen), player2);

        System.out.println(i);
        System.out.println(j);

        //TODO: rivedere calcolo supremazia

        //la supremazia dovrà essere del player1
        assertEquals(player1, table.getSupremacy(table.getIsland(islandChosen)));
    }

    @Test
    void setup() {
        assertTrue(true, "not needed");
    }

    @Test
    void setAndGetIslandChosen() {
        character.setIslandChosen(3);
        assertEquals(character.getIslandChosen(), 3);
    }
}