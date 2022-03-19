package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.model.pawns.Student;
import java.util.*;

public class Character1 extends Character{

    private static final int NUM_OF_STUDENTS = 4;

    private List<Student> students;

    public Character1(String name, int cost) {
        super(1, 1);
    }

    //TODO: prendi 1 studente dalla carta e piazzalo su un'isola a tua scelta, poi pesca 1 studente dal sacchetto e mettilo su questa carta
    @Override
    public void activate(Table table, Island island, int pos)
    {
        table.island.addStudent(students.remove(pos));

    }

    //TODO: all'inizio della partita, pescate 4 studenti e piazzateli sopra questa carta
    public void setup(Table table)
    {
        for(int i = 0; i < NUM_OF_STUDENTS; i++)
        {
            students.add(table.bag.drawstudent());
        }
    }
}
