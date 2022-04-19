package it.polimi.ingsw.network;

import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.requests.messages.PlayAssistantMessage;
import it.polimi.ingsw.view.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerMessageTest {

    @Test
    void getRequestMessage() {
        PlayAssistantMessage type = new PlayAssistantMessage(2);
        ControllerMessage message = new ControllerMessage(type, "user", new View());
        assertEquals(type, message.getRequestMessage());
    }

    @Test
    void getUsername() {
        ControllerMessage message = new ControllerMessage(new PlayAssistantMessage(2), "user", new View());
        assertEquals("user", message.getUsername());
    }


    @Test
    void isExpertMode(){
        ControllerMessage message = new ControllerMessage(new PlayAssistantMessage(2), "user", new View() , true);
        ControllerMessage message2 = new ControllerMessage(new PlayAssistantMessage(2), "user", new View());
        assertTrue(message.isExpertMode());
        assertFalse(message2.isExpertMode());
    }
}