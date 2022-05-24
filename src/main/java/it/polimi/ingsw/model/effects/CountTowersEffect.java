package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.islands.Island;

/**
 * character card effect that doesn't count towers for influence calculation
 */
public class CountTowersEffect implements Effect, InfluenceEffect{

    private final Island islandEffect;

    /**
     * builds "countTowers" effect
     * @param island island on which create the effect
     */
    public CountTowersEffect(Island island){
        this.islandEffect = island;
    }

    /**
     * implements InfluenceEffect interface
     * modifies the influence calculated for a player on an island without counting the number of towers on the island
     * @param influence influence to modify
     * @param island island on which calculate influence
     * @param player influence's owner
     * @return modified player's influence
     */
    @Override
    public int influenceEffect(int influence, Island island, Player player) {
        if (islandEffect.equals(island) && island.getTower() != null && player.equals(island.getTower().getOwner())){
            influence -= island.getNumOfTowers();
        }
        return influence;
    }

    /**
     * override of toString() method
     * @return specified effect
     */
    @Override
    public String toString() {
        return "Towers on island " + (islandEffect.getId()+1) + " aren't counted for influence in this round";
    }
}
