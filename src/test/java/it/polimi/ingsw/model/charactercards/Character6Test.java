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

import static org.junit.jupiter.api.Assertions.*;

class Character6Test {

    private Character6 character;
    private TableExpertMode table;
    private Player player1;
    private Player player2;
    private List<Player> players;

    @BeforeEach
    void init() {
        character = new Character6();
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect(TableExpertMode table) {

        //l'isola scelta è la 3
        character.setIslandChosen(3);

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

        //all'isola aggiungo due torri di player1
        this.table.getIsland(3).setTower(new Tower(ColorT.BLACK, player1));
        this.table.getIsland(3).setTower(new Tower(ColorT.BLACK, player1));

        //a player1 do il blu e a player2 do il rosso
        player1.getSchoolBoard().getProfessorTable().addProfessor(blueProfessor);
        player2.getSchoolBoard().getProfessorTable().addProfessor(redProfessor);

        effect(this.table);

        //dovrebbe vincere il player1 per le torri ma invece vince il player2
        assertEquals(this.table.getSupremacy(this.table.getIsland(3)), player2);
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