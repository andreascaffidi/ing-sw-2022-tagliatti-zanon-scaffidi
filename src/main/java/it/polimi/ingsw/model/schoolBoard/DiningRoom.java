package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.exceptions.GetCoinException;
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
        }
        throw new RuntimeException();
    }

    public void addStudent(Student student){
        this.getLine(student.getColor()).add(student);
    }

    public int getNumberOfStudentsPerColor(ColorS color){
        return this.getLine(color).size();
    }

    public void removeStudent(Student s) {
       this.getLine(s.getColor()).remove(s);
    }

    public Student removeStudent(ColorS color){
       return this.getLine(color).remove(this.getLine(color).size()-1);
    }
}
