package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.exceptions.ColorNotFoundException;
import it.polimi.ingsw.exceptions.InvalidColorsException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

import java.util.*;

public class DiningRoom {
    private List<Student> greenLine;
    private List<Student> redLine;
    private List<Student> yellowLine;
    private List<Student> blueLine;
    private List<Student> pinkLine;

    public DiningRoom() {
        this.greenLine = new ArrayList<Student>();
        this.redLine = new ArrayList<Student>();
        this.yellowLine = new ArrayList<Student>();
        this.blueLine = new ArrayList<Student>();
        this.pinkLine = new ArrayList<Student>();
    }

    /**
     * returns the Line of the given Color
     * @param color
     * @return
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
     * adds a Student to the chosen line
     * @param student
     */

    public void addStudent(Student student){
        this.getLine(student.getColor()).add(student);
    }

    /**
     * returns the number of students for that Color on the line
     * @param color
     * @return
     */
    public int getNumberOfStudentsPerColor(ColorS color){
        return this.getLine(color).size();
    }

    /**
     * delete given Student from the Line
     * @param s
     */

    public void removeStudent(Student s) {
       this.getLine(s.getColor()).remove(s);
    }

    /**
     * delete a Student from the line of the given Color
     * @param color
     * @return
     */

    public Student removeStudent(ColorS color){
       return this.getLine(color).remove(this.getLine(color).size()-1);
    }

    public void validColors(List<String> colors) throws InvalidColorsException, ColorNotFoundException {
        int minStudent = 1;
        if (colors.size() == 2 && ColorS.parseToColor(colors.get(0)) == ColorS.parseToColor(colors.get(1))){
            minStudent = 2;
        }
        for (String color : colors) {
            if (this.getLine(ColorS.parseToColor(color)).size() < minStudent) {
                throw new InvalidColorsException("Invalid colors");
            }
        }
    }
}
