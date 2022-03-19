package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

import java.util.ArrayList;

public class DiningRoomExpertMode extends DiningRoom{

    public DiningRoomExpertMode()
    {
        super();
    }

    public void addStudent(Student student) {
        super.addStudent(student);
        if(getLine(student.getColor()).size() == 3 || getLine(student.getColor()).size() == 6 || getLine(student.getColor()).size() == 9)
        {
            //TODO: chiamare il controller per incrementare le monete del player
        }
    }
}
