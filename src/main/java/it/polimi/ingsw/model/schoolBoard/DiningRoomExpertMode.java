package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

import java.util.ArrayList;

public class DiningRoomExpertMode extends DiningRoom{

    public DiningRoomExpertMode()
    {
        super();
    }

    public void addStudent(ColorS color, Student student) {
        super.addStudent(color, student);
        if(getLine(color).size() == 3 || getLine(color).size() == 6 || getLine(color).size() == 9)
        {
            //TODO: chiamare il controller per incrementare le monete del player
        }
    }
}
