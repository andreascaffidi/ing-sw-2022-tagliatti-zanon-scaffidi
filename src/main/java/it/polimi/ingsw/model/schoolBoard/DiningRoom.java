package it.polimi.ingsw.model.schoolBoard;
import com.sun.source.tree.DefaultCaseLabelTree;
import it.polimi.ingsw.model.ColorS;
import it.polimi.ingsw.model.Student;
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

    public List<Student> getGreenLine() {
        return greenLine;
    }

    public List<Student> getRedLine() {
        return redLine;
    }

    public List<Student> getYellowLine() {
        return yellowLine;
    }

    public List<Student> getBlueLine() {
        return blueLine;
    }

    public List<Student> getPinkLine() {
        return pinkLine;
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
            default: return //TODO: ritorna eccezione nel caso in cui il colore non fosse giusto
        }
    }

    public void addStudent(ColorS color, Student student){
        switch (color)
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
            default: return //TODO: ritorna eccezione nel caso in cui il colore non fosse giusto
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
            default: return //TODO: ritorna eccezione nel caso in cui il colore non fosse giusto
        }
    }
}
