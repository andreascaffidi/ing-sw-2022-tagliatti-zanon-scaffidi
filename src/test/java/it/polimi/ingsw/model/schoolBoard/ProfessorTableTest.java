package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTableTest {

    private ProfessorTable professorTable;

    @BeforeEach
    void init(){
        professorTable = new ProfessorTable();
    }

    @Test
    void addAndGetProfessor() {
        Professor prof = new Professor(ColorS.YELLOW);
        professorTable.addProfessor(prof);
        List<Professor> professors = new ArrayList<Professor>(Arrays.asList(prof));
        assertEquals(professors, professorTable.getProfessors());
    }

    @Test
    void removeProfessor() {
        List<Professor> professors = new ArrayList<Professor>(Arrays.asList(new Professor(ColorS.BLUE), new Professor(ColorS.YELLOW)));
        professorTable.removeProfessor(professors.get(0));
        assertFalse(professorTable.getProfessors().contains(professors.get(0)));
    }
}