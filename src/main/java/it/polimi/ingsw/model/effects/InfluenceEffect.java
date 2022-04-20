package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.islands.Island;

/**
 * interface for influence effects
 */
public interface InfluenceEffect {
    /**
     * modifies a player's influence on an island
     * @param influence influence to modify
     * @param island island on which calculate influence
     * @param player influence's owner
     * @return modified player's influence
     */
    int influenceEffect(int influence, Island island, Player player);
}
