package it.polimi.ingsw.network.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayAssistantMessageTest {

    @Test
    void execute() {
        assertTrue(true, "tested in ControllerTest");
    }

    @Test
    void getValue() {
        PlayAssistantMessage message = new PlayAssistantMessage(3);
        assertEquals(3, message.getValue());
    }
}