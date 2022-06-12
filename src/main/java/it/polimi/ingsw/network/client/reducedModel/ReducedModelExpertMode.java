package it.polimi.ingsw.network.client.reducedModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * reduced serializable version of the entire model expert mode
 */
public class ReducedModelExpertMode extends ReducedModel implements Serializable {

    private final String currentEffect;
    private final Map<String, Integer> coins;
    private final Map<Integer, Boolean> noEntryTiles;
    private final List<ReducedCharacter> characters;
    private final boolean characterAlreadyPlayed;

    /**
     * builds a reduced model for expert mode
     * @param islands reduced islands
     * @param clouds reduced clouds
     * @param currentPlayer current player's username
     * @param boards reduced boards
     * @param currentEffect current effect's type
     * @param coins number of players' coins
     * @param noEntryTiles no entry tiles placed
     * @param characters reduced character cards
     * @param characterAlreadyPlayed if a player has already played a character in this round
     */
    public ReducedModelExpertMode(List<ReducedIsland> islands, List<ReducedCloud> clouds,
                                  String currentPlayer, List<ReducedBoard> boards,
                                  String currentEffect, Map<String, Integer> coins,
                                  Map<Integer, Boolean> noEntryTiles,
                                  List<ReducedCharacter> characters, boolean characterAlreadyPlayed) {
        super(islands, clouds, currentPlayer, boards);
        this.currentEffect = currentEffect;
        this.coins = coins;
        this.noEntryTiles = noEntryTiles;
        this.characters = characters;
        this.characterAlreadyPlayed = characterAlreadyPlayed;
    }

    /**
     * gets current effect's type
     * @return current effect's type
     */
    public String getCurrentEffect() {
        return currentEffect;
    }

    /**
     * gets number of players' coins
     * @return number of players' coins
     */
    public Map<String, Integer> getCoins() {
        return coins;
    }

    /**
     * gets no entry tiles placed
     * @return no entry tiles placed
     */
    public Map<Integer, Boolean> getNoEntryTiles() {
        return noEntryTiles;
    }

    /**
     * gets reduced character cards
     * @return reduced character cards
     */
    public List<ReducedCharacter> getCharacters() {
        return characters;
    }

    /**
     * gets a specific character by its ID
     * @param character character ID
     * @return reduced character
     */
    public ReducedCharacter getCharacterById(int character){
        return characters.stream().filter(c -> c.getId() == character).findFirst().orElse(null);
    }

    /**
     * checks if a player has already played a character
     * @return if a player has already played a character
     */
    public boolean isCharacterAlreadyPlayed() {
        return characterAlreadyPlayed;
    }
}
