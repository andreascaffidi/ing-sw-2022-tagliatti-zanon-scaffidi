package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

import java.util.*;

/**
 * school board's dining room
 */
public class DiningRoom {
    private final List<Student> greenLine;
    private final List<Student> redLine;
    private final List<Student> yellowLine;
    private final List<Student> blueLine;
    private final List<Student> pinkLine;

    /**
     * builds dining room
     */
    public DiningRoom() {
        this.greenLine = new ArrayList<>();
        this.redLine = new ArrayList<>();
        this.yellowLine = new ArrayList<>();
        this.blueLine = new ArrayList<>();
        this.pinkLine = new ArrayList<>();
    }

    /**
     * gets line of the given color
     * @param color line color
     * @return line of given color
     */
    public List<Student> getLine(ColorS color)
    {
        switch (color) {
            case RED:
                return redLine;
            case BLUE:
                return blueLine;
            case GREEN:
                return greenLine;
            case YELLOW:
                return yellowLine;
            case PINK:
                return pinkLine;
        }
        throw new RuntimeException("Color not found");
    }

    /**
     * adds a student to the dining room
     * @param student student to add
     */
    public void addStudent(Student student){
        this.getLine(student.getColor()).add(student);
    }

    /**
     * gets the number of students on color line
     * @param color line color
     * @return number of students on color line
     */
    public int getNumberOfStudentsPerColor(ColorS color){
        return this.getLine(color).size();
    }

    /**
     * removes student from the dining room
     * @param color student color
     * @return removed student
     */
    public Student removeStudent(ColorS color){
       return this.getLine(color).remove(this.getLine(color).size()-1);
    }

    /**
     * checks colors validity
     * @param colors colors to check
     * @throws GameException invalid chosen colors
     */
    public void validColors(List<ColorS> colors) throws GameException {
        int minStudent = 1;
        if (colors.size() == 2 && colors.get(0) == colors.get(1)){
            minStudent = 2;
        }
        for (ColorS color : colors) {
            if (this.getLine(color).size() < minStudent) {
                throw new GameException("Invalid colors");
            }
        }
    }
}
