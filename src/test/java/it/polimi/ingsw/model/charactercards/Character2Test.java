package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Character2Test {

    private Character2 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character2();
        players = new ArrayList<Player>();
        player1 = new Player("1");
        player2 = new Player("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    //TODO: va fatto dopo aver implementato ProfessorTie
    @Test
    void effect() {

        //il currentplayer è player2
        this.table.setCurrentPlayer(player2);

        //e il player1 e il player2 hanno lo stesso numero di studenti nella dining room
        player1.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        player1.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));

        player2.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));
        player2.getSchoolBoard().getDiningRoom().addStudent(new Student(ColorS.BLUE));

        //dato che prima l'owner è null, prendo come professorOwner il currentPlayer (player2)
        table.setProfessorOwner(ColorS.BLUE, table.getCurrentPlayer());

        //se faccio il calcolo l'owner dovrebbe essere player2
        assertEquals(player2, table.getProfessorOwner(ColorS.BLUE));

        //se metto come currentplayer il player1
        this.table.setCurrentPlayer(player1);

        //ma non chiamo l'effetto
        table.setProfessorOwner(ColorS.BLUE, player1);

        //l'owner rimane il player2
        assertEquals(player2, table.getProfessorOwner(ColorS.BLUE));

        //dopo l'effetto è il player1 che è l'owner anche se sono pari
        character.effect(this.table);

        table.setProfessorOwner(ColorS.BLUE, player1);

        assertEquals(player1, table.getProfessorOwner(ColorS.BLUE));
    }

    @Test
    void setup() {
        assertTrue(true, "not needed");
    }
}