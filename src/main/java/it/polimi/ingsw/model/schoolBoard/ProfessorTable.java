package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Professor;
import java.util.*;


public class ProfessorTable {
    private List<Professor> professors;

    public ProfessorTable(){
        professors = new ArrayList<Professor>();
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    /**
     * adds Professor to ProfessorTable
     * @param professor
     */

    public void addProfessor(Professor professor){
        professors.add(professor);
    }

    /**
     * removes Professor to ProfessorTable
     * @param professor
     */

    public void removeProfessor(Professor professor){
        professors.remove(professor);
    }

    /**
     * checks if the ProfessorTable has the Professor of the given Color
     * @param color
     * @return
     */

    public boolean hasProfessor(ColorS color){
        for(Professor p: this.professors)
        {
            if(p.getColor() == color)
            {
                return true;
            }
        }
        return false;
    }
}
