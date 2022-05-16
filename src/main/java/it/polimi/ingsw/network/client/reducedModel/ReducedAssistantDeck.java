package it.polimi.ingsw.network.client.reducedModel;

import java.io.Serializable;
import java.util.List;

/**
 * reduced serializable version of player's assistant deck
 */
public class ReducedAssistantDeck implements Serializable {

    private final List<ReducedAssistant> assistantCards;
    private final ReducedAssistant playedAssistant;

    /**
     * builds the reduced assistant deck
     * @param assistantCards list of reduced assistant cards
     * @param playedAssistant the last played card (on top of the discard pile)
     */
    public ReducedAssistantDeck(List<ReducedAssistant> assistantCards, ReducedAssistant playedAssistant) {
        this.assistantCards = assistantCards;
        this.playedAssistant = playedAssistant;
    }

    /**
     * gets deck's assistant cards
     * @return deck's assistant cards
     */
    public List<ReducedAssistant> getAssistantCards() {
        return assistantCards;
    }

    /**
     * gets the last played card
     * @return the last played card
     */
    public ReducedAssistant getPlayedAssistant() {
        return playedAssistant;
    }
}
