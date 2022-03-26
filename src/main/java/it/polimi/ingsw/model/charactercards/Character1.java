package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.TypeOfCard;
import it.polimi.ingsw.model.pawns.Student;
import java.util.*;

public class Character1 implements TypeOfCard {

    private static final int NUM_OF_STUDENTS = 4;

    private List<Student> students;

    private int islandChosen;

    private Student studentChosen;

    public Character1() {
        this.students = null;
        this.islandChosen = 0;
        this.studentChosen = null;
    }

    /**
     * takes a student from the Character and places it on an Island
     * @param table
     */
    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegliere isola e studente tra students
        this.students.remove(studentChosen);
        table.getIsland(islandChosen).addStudent(studentChosen);
        this.students.add(table.getBag().drawStudent());
    }

    /**
     * draws 4 student from the bag and places them on the Card
     * @param table
     */
    @Override
    public void setup(TableExpertMode table)
    {
        for(int i = 0; i < NUM_OF_STUDENTS; i++)
        {
            this.students.add(table.getBag().drawStudent());
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setIslandChosen(int islandChosen) {
        this.islandChosen = islandChosen;
    }

    public int getIslandChosen() {
        return islandChosen;
    }

    public void setStudentChosen(Student studentChosen) {
        this.studentChosen = studentChosen;
    }

    public Student getStudentChosen() {
        return studentChosen;
    }
}
