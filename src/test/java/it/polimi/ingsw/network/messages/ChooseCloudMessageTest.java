package it.polimi.ingsw.network.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChooseCloudMessageTest {

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