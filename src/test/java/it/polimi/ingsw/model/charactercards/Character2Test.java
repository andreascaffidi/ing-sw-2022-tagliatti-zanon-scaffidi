package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerExpertMode;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Character2Test {

    private Character2 character;
    private TableExpertMode table;
    private PlayerExpertMode player1;
    private PlayerExpertMode player2;
    private List<PlayerExpertMode> players;

    @BeforeEach
    void init() {
        character = new Character2();
        players = new ArrayList<PlayerExpertMode>();
        player1 = new PlayerExpertMode("1");
        player2 = new PlayerExpertMode("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    //TODO: va fatto dopo aver implementato ProfessorTie
    @Test
    void effect() {
        //ho aggiunto lo stesso numero di professori
        player1.getSchoolBoard().getProfessorTable().addProfessor(new Professor(ColorS.BLUE));
        player2.getSchoolBoard().getProfessorTable().addProfessor(new Professor(ColorS.BLUE));
        table.setProfessorTie(false);
        character.effect(table);
        assertTrue(table.isProfessorTie(), String.valueOf(true));
    }

    @Test
    void setup() {
        assertTrue(true, "not needed");
    }
}