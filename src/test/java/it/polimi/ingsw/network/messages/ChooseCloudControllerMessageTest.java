package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChooseCloudControllerMessageTest {

    @Test
    void getId() {
        ChooseCloudMessage message = new ChooseCloudMessage(3);
        assertEquals(3, message.getId());
    }

    @Test
    void execute() {
        assertTrue(true, "tested in ControllerTest");
    }
}