package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTableTest {

    private ProfessorTable professorTable;

    /**
     *  Initialises a professor table
     *  <br>
     *  <u>It's called before each test</u>
     */
    @BeforeEach
    void init(){
        professorTable = new ProfessorTable();
    }

    /**
     * Tests that a professor is added and got correctly
     */
    @Test
    void addAndGetProfessor() {
        Professor prof = new Professor(ColorS.YELLOW);
        professorTable.addProfessor(prof);
        List<Professor> professors = new ArrayList<>(Collections.singletonList(prof));
        assertEquals(professors, professorTable.getProfessors());
    }

    /**
     * Tests if a professor is removed correctly from the professor table
     */
    @Test
    void removeProfessor() {
        List<Professor> professors = new ArrayList<>(Arrays.asList(new Professor(ColorS.BLUE), new Professor(ColorS.YELLOW)));
        professorTable.removeProfessor(professors.get(0));
        assertFalse(professorTable.getProfessors().contains(professors.get(0)));
    }

    /**
     * Tests if the professor table has a specific professor
     */
    @Test
    void hasProfessor(){
        professorTable.addProfessor(new Professor(ColorS.BLUE));
        assertTrue(professorTable.hasProfessor(ColorS.BLUE));
        assertFalse(professorTable.hasProfessor(ColorS.RED));
        assertFalse(professorTable.hasProfessor(ColorS.PINK));
    }
}