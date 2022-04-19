package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.requests.messages.MoveMotherNatureMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveMotherNatureControllerMessageTest {

    @Test
    void getMovements() {
        MoveMotherNatureMessage message = new MoveMotherNatureMessage(3);
        assertEquals(3, message.getMovements());
    }

    @Test
    void execute() {
        assertTrue(true, "tested in ControllerTest");
    }
}