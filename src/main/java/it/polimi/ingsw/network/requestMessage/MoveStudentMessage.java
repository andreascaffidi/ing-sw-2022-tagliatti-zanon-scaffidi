package it.polimi.ingsw.network.requestMessage;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.ControllerExecute;
import it.polimi.ingsw.view.View;

public class MoveStudentMessage implements RequestMessage, ControllerExecute {
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
    public void execute(Controller controller, String username, View view) {
        if(dest.equals("island")){
            controller.moveStudentToIsland(this,username,view);
        }else{
            controller.moveStudentToDining(this, username,view);
        }
    }

    public int getIdIsland() {
        return idIsland;
    }
    public int getStudentIndex() {
        return studentIndex;
    }
}
