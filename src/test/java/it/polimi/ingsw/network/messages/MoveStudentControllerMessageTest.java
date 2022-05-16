package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MoveStudentControllerMessageTest {

    @Test
    void execute() {
        assertTrue(true, "tested in ControllerTest");
    }

    @Test
    void getMovements() {
        Map<Integer, String> movements = new HashMap<>();
        movements.put(1, "DINING");
        movements.put(2, "3");
        MoveStudentMessage message = new MoveStudentMessage(movements);
        assertEquals(movements, message.getMovements());
    }

}