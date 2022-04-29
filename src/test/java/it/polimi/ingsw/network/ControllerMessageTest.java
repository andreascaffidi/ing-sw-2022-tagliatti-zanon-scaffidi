package it.polimi.ingsw.network;

import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerMessageTest {

    @Test
    void getRequestMessage() {
        PlayAssistantMessage type = new PlayAssistantMessage(2);
        ControllerMessage message = new ControllerMessage(type, "user");
        assertEquals(type, message.getRequestMessage());
    }

    @Test
    void getUsername() {
        ControllerMessage message = new ControllerMessage(new PlayAssistantMessage(2), "user");
        assertEquals("user", message.getUsername());
    }


    @Test
    void isExpertMode(){
        ControllerMessage message = new ControllerMessage(new PlayAssistantMessage(2), "user", true);
        ControllerMessage message2 = new ControllerMessage(new PlayAssistantMessage(2), "user");
        assertTrue(message.isExpertMode());
        assertFalse(message2.isExpertMode());
    }
}