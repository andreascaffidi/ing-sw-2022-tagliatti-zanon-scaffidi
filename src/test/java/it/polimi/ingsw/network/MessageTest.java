package it.polimi.ingsw.network;

import it.polimi.ingsw.network.requestMessage.PlayAssistantMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getRequestMessage() {
        PlayAssistantMessage type = new PlayAssistantMessage(2);
        Message message = new Message(type, "user");
        assertEquals(type, message.getRequestMessage());
    }

    @Test
    void getUsername() {
        Message message = new Message(new PlayAssistantMessage(2), "user");
        assertEquals("user", message.getUsername());
    }


    @Test
    void isExpertMode(){
        Message message = new Message(new PlayAssistantMessage(2), "user", true);
        Message message2 = new Message(new PlayAssistantMessage(2), "user");
        assertTrue(message.isExpertMode());
        assertFalse(message2.isExpertMode());
    }
}