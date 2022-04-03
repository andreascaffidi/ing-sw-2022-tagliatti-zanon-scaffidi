package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.TypeOfMessage;

public class MoveStudentMessage implements TypeOfMessage {
    String dest;
    int idIsland;
    int studentIndex;

    public MoveStudentMessage(String dest, int studentIndex) {
        this.dest = dest;
        this.studentIndex = studentIndex;
    }

    public MoveStudentMessage(String dest,int idIsland, int studentIndex){
        this.dest = dest;
        this.studentIndex =studentIndex;
        this.idIsland = idIsland;
    }


    @Override
    public void execute(Controller controller, String username) {
        if(dest.equals("island")){
            controller.moveStudentToIsland(this,username);
        }else{
            controller.moveStudentToDining(this, username);
        }
    }

    public int getIdIsland() {
        return idIsland;
    }
    public int getStudentIndex() {
        return studentIndex;
    }
}
