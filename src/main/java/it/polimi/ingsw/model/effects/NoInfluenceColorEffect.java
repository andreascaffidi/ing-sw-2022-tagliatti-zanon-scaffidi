package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;

/**
 * character card effect that doesn't count a color for influence calculation
 */
public class NoInfluenceColorEffect implements Effect, InfluenceEffect{

    private final ColorS noInfluenceColor;

    /**
     * builds "noInfluenceColor" effect
     * @param color color that doesn't count for influence calculation
     */
    public NoInfluenceColorEffect(ColorS color){
        this.noInfluenceColor = color;
    }

    /**
     * implements InfluenceEffect interface
     * modifies the influence calculated for a player on an island without counting the specified color
     * @param influence influence to modify
     * @param island island on which calculate influence
     * @param player influence's owner
     * @return modified player's influence
     */
    @Override
    public int influenceEffect(int influence, Island island, Player player) {
        if(player.getSchoolBoard().getProfessorTable().hasProfessor(noInfluenceColor))
            return influence - island.numStudent(noInfluenceColor);
        else
            return influence;
    }
}
