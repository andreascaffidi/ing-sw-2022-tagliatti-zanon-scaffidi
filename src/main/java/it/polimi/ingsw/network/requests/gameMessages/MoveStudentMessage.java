package it.polimi.ingsw.network.requests.gameMessages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.RequestMessage;

import java.util.Map;


/**
 * game message to move a student
 */
public class MoveStudentMessage implements RequestMessage, ControllerExecute {

    private final Map<Integer, String> movements;

    /**
     * builds move student message
     * @param movements map of all 3 movements chosen
     */
    public MoveStudentMessage(Map<Integer, String> movements){
        this.movements = movements;
    }

    /**
     * gets the map of movements
     * @return map of movements
     */
    public Map<Integer, String> getMovements() {
        return movements;
    }

    /**
     * executes controller method move students
     * @param controller game controller
     */
    @Override
    public void execute(Controller controller) {
        controller.moveStudents(this);
    }

}
