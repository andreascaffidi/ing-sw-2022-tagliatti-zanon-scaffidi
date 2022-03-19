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

    public void addProfessor(Professor professor){
        professors.add(professor);
    }

    public void removeProfessor(Professor professor){
        professors.remove(professor);
    }

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
