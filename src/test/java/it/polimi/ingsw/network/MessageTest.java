package it.polimi.ingsw.network;

import it.polimi.ingsw.network.messages.MoveStudentMessage;
import it.polimi.ingsw.network.messages.PlayAssistantMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getTypeOfMessage() {
        PlayAssistantMessage type = new PlayAssistantMessage(2);
        Message message = new Message(type, "user");
        assertEquals(type, message.getTypeOfMessage());
    }

    @Test
    void getUsername() {
        Message message = new Message(new PlayAssistantMessage(2), "user");
        assertEquals("user", message.getUsername());
    }
}