package it.polimi.ingsw.network.requests.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;
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
