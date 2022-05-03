package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

/**
 * game message move student
 */
public class MoveStudentMessage implements RequestMessage, ControllerExecute {
    String dest;
    int idIsland;
    int studentIndex;

    /**
     * builds move student message
     * @param dest student destination
     * @param studentIndex student index
     */
    public MoveStudentMessage(String dest, int studentIndex) {
        this.dest = dest;
        this.studentIndex = studentIndex;
    }

    /**
     * builds move student message with destination island
     * @param dest student destination
     * @param idIsland destination island id
     * @param studentIndex student index
     */
    public MoveStudentMessage(String dest,int idIsland, int studentIndex){
        this.dest = dest;
        this.studentIndex =studentIndex;
        this.idIsland = idIsland;
    }

    /**
     * executes controller method move student
     * @param controller game controller
     * @param username of the player that moves the student
     */
    @Override
    public void execute(Controller controller, String username) {
        if(dest.equals("island")){
            controller.moveStudentToIsland(this,username);
        }else{
            controller.moveStudentToDining(this, username);
        }
    }

    /**
     * gets island id
     * @return island id
     */
    public int getIdIsland() {
        return idIsland;
    }

    /**
     * gets student index
     * @return student index
     */
    public int getStudentIndex() {
        return studentIndex;
    }
}
