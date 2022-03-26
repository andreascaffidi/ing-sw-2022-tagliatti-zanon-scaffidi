package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerExpertMode;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

class Character3Test {

    private Character3 character;
    private TableExpertMode table;
    private PlayerExpertMode player1;
    private PlayerExpertMode player2;
    private List<PlayerExpertMode> players;

    @BeforeEach
    void init() {
        character = new Character3();
        players = new ArrayList<PlayerExpertMode>();
        player1 = new PlayerExpertMode("1");
        player2 = new PlayerExpertMode("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {
        int islandChosen = 3;
        //aggiungo sull'isola uno student BLU e solo quello
        table.getIsland(islandChosen).addStudent(new Student(ColorS.BLUE));
        table.getIsland(islandChosen).addStudent(new Student(ColorS.BLUE));

        //il player ha tecnicamente la supremazia ma non c'è madre natura
        Professor blueProfessor = new Professor(ColorS.BLUE);
        player1.getSchoolBoard().getProfessorTable().addProfessor(blueProfessor);

        //assumo che l'isola scelta non abbia sopra madre natura
        character.setIslandChosen(islandChosen);
        assumeFalse(table.getIsland(islandChosen).isMotherNature());

        //di conseguenza l'owner dell'isola dovrà essere null
        if(table.getSupremacy(table.getIsland(islandChosen))==null)
        {
            assertNull(table.getIsland(islandChosen).getTower().getOwner());
        }

        //dopo la chiamata del metodo
        character.effect(table);

        //la supremazia dovrà essere del player1
        if(table.getSupremacy(table.getIsland(islandChosen)).equals(player1))
        {
            assertEquals(table.getIsland(islandChosen).getTower().getOwner(), player1);
        }

        //se spostassi il professor su player2
        player2.getSchoolBoard().getProfessorTable().removeProfessor(blueProfessor);
        player1.getSchoolBoard().getProfessorTable().addProfessor(blueProfessor);

        //dato che non c'è madre natura la supremazia è ancora di player1
        if(table.getSupremacy(table.getIsland(islandChosen)).equals(player1))
        {
            assertEquals(table.getIsland(islandChosen).getTower().getOwner(), player1);
        }

        //dopo la chiamata del metodo
        character.effect(table);

        //la supremazia dovrà essere del player2
        if(table.getSupremacy(table.getIsland(islandChosen)).equals(player2))
        {
            assertEquals(table.getIsland(islandChosen).getTower().getOwner(), player2);
        }
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