package it.polimi.ingsw.network.client.reducedModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReducedAssistantDeck implements Serializable {

    private List<ReducedAssistant> assistantCards;
    private ReducedAssistant playedAssistant;

    public ReducedAssistantDeck(List<ReducedAssistant> assistantCards, ReducedAssistant playedAssistant) {
        this.assistantCards = assistantCards;
        this.playedAssistant = playedAssistant;
    }

    public List<ReducedAssistant> getAssistantCards() {
        return assistantCards;
    }

    public ReducedAssistant getPlayedAssistant() {
        return playedAssistant;
    }
}
