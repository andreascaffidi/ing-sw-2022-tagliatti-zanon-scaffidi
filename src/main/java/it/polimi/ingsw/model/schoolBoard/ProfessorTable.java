package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Professor;
import java.util.*;

/**
 * school board's professor table
 */
public class ProfessorTable {
    private final List<Professor> professors;

    /**
     * builds professor table
     */
    public ProfessorTable(){
        professors = new ArrayList<>();
    }

    /**
     * gets professors on professor table
     * @return professors on professor table
     */
    public List<Professor> getProfessors() {
        return professors;
    }

    /**
     * adds professor to professor table
     * @param professor professor to add
     */
    public void addProfessor(Professor professor){
        professors.add(professor);
    }

    /**
     * removes professor from professor table
     * @param professor professor to remove
     */
    public void removeProfessor(Professor professor){
        professors.remove(professor);
    }

    /**
     * checks if there's the professor with a specified color
     * @param color professor's color to check
     * @return true if there's that professor, false otherwise
     */
    public boolean hasProfessor(ColorS color) {
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
