package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.exceptions.GetCoinException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

import java.util.ArrayList;

public class DiningRoomExpertMode extends DiningRoom{

    public DiningRoomExpertMode()
    {
        super();
    }

    //fixme: sistemare override
    public void addStudent2(Student student) throws GetCoinException {
        super.addStudent(student);
        int position = getLine(student.getColor()).size();
        if(position == 3 || position == 6 || position == 9)
        {
            throw new GetCoinException("Reached position "+position+". You get a coin!");
        }
    }

}
