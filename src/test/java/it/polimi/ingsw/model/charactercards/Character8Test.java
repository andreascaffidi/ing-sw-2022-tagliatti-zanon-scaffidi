package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Character8Test {
    private Character8 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character8();
        players = new ArrayList<Player>();
        player1 = new Player("1");
        player2 = new Player("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {
       table.setCurrentPlayer(player1);

        //creo studenti e professori
        Professor blueProfessor = new Professor(ColorS.BLUE);
        Professor redProfessor = new Professor(ColorS.RED);
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.RED);
        Student student4 = new Student(ColorS.RED);
        Student student5 = new Student(ColorS.RED);

        //all'isola aggiungo 3 studenti rossi e 2 blu
        this.table.getIsland(3).addStudent(student1);
        this.table.getIsland(3).addStudent(student2);
        this.table.getIsland(3).addStudent(student3);
        this.table.getIsland(3).addStudent(student4);
        this.table.getIsland(3).addStudent(student5);

        //a player1 do il blu e a player2 do il rosso
        table.setProfessorOwner(ColorS.BLUE, player1);
        table.setProfessorOwner(ColorS.RED, player2);

        character.effect(this.table);

        Player winner = new Player("ciao");

        //dovrebbe vincere il player2 ma vince il player1
        try {
            assertEquals(player1, this.table.getSupremacy(this.table.getIsland(3)));
            winner = this.table.getSupremacy(this.table.getIsland(3));
        } catch (ParityException e) {
            e.printStackTrace();
        }

        assertEquals(player1, winner);
    }

    @Test
    void setup() {
        assertTrue(true, "not needed");
    }
}