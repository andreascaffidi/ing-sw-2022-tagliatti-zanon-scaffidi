package it.polimi.ingsw.model.schoolBoard;
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
            default: return null;
        }
    }

    public void addStudent(Student student){
        switch (student.getColor())
        {
            case BLUE:
                blueLine.add(student);
            case PINK:
                pinkLine.add(student);
            case GREEN:
                greenLine.add(student);
            case RED:
                redLine.add(student);
            case YELLOW:
                yellowLine.add(student);
            default: return;
        }
    }

    public int getNumberOfStudentsPerColor(ColorS color){
        switch (color)
        {
            case BLUE:
                return blueLine.size();
            case PINK:
                return pinkLine.size();
            case GREEN:
                return greenLine.size();
            case RED:
                return redLine.size();
            case YELLOW:
                return yellowLine.size();
            default: return 0;
        }
    }

    public void removeStudent(Student s) {
       this.getLine(s.getColor()).remove(s);
    }

    public Student removeStudent(ColorS color){
       return this.getLine(color).remove(this.getLine(color).size()-1);
    }
}
