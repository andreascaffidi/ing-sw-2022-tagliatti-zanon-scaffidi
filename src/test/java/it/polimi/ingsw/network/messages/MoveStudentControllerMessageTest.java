package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.requestMessage.MoveStudentMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveStudentControllerMessageTest {

    @Test
    void execute() {
        assertTrue(true, "tested in ControllerTest");
    }

    @Test
    void getIdIsland() {
        MoveStudentMessage message = new MoveStudentMessage("island", 3, 3);
        assertEquals(3, message.getIdIsland());
    }

    @Test
    void getStudentIndex() {
        MoveStudentMessage message = new MoveStudentMessage("dining", 3);
        assertEquals(3, message.getStudentIndex());
    }
}