package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.islands.Island;

/**
 * character card effect that adds additional influence
 */
public class AdditionalInfluenceEffect implements Effect, InfluenceEffect {

    private final int additionalInfluence;
    private final Player effectOwner;

    /**
     * builds "additionalInfluence" effect
     * @param effectOwner player who creates the effect
     */
    public AdditionalInfluenceEffect(Player effectOwner){
        this.effectOwner = effectOwner;
        this.additionalInfluence = 2;
    }

    /**
     * implements InfluenceEffect interface
     * modifies the influence calculated for a player on an island adding additional influence
     * @param influence influence to modify
     * @param island island on which calculate influence
     * @param player influence's owner
     * @return modified player's influence
     */
    @Override
    public int influenceEffect(int influence, Island island, Player player) {
        if (player.equals(effectOwner)){
            influence = influence + additionalInfluence;
        }
        return influence;
    }
}
