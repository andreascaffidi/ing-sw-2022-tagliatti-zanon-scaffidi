package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;

import java.util.ArrayList;

public class DiningRoomExpertMode extends DiningRoom{

    private PlayerExpertMode player;

    public DiningRoomExpertMode(PlayerExpertMode player)
    {
        super();
        this.player = player;
    }

    public void addStudent(Student student) {
        super.addStudent(student);
        if(getLine(student.getColor()).size() % 3 == 0)
        {
            this.player.addCoins(1);
        }
    }

    public Student removeStudent(ColorS color){
        return super.getLine(color).remove(super.getLine(color).size()-1);
    }
}
