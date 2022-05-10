package it.polimi.ingsw.network.client.reducedModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReducedCharacterDeck implements Serializable {

    private List<ReducedCharacter> characterCards;

    public ReducedCharacterDeck(List<ReducedCharacter> characterCards) {
        this.characterCards = characterCards;
    }

    public List<ReducedCharacter> getCharacterCards() {
        return characterCards;
    }
}
