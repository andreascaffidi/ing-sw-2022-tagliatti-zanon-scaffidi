package it.polimi.ingsw.model.effects;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.islands.Island;

public interface InfluenceEffect {
    int influenceEffect(int influence, Island island, Player player);
}
