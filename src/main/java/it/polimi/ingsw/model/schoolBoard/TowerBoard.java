package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.model.pawns.Tower;
import java.util.*;

/**
 * school board's tower board
 */
public class TowerBoard {

    private final List<Tower> towers;

    /**
     * builds tower board
     */
    public TowerBoard(){
        towers = new ArrayList<>();
    }

    /**
     * gets towers on tower board
     * @return towers on tower board
     */
    public List<Tower> getTowers() {
        return towers;
    }

    /**
     * adds tower to tower board
     * @param tower tower to add
     */
    public void addTower(Tower tower){
        towers.add(tower);
    }

    /**
     * removes tower from the last position of tower board
     * @return last tower on tower board
     */
    public Tower removeLastTower(){
        return towers.remove(towers.size()-1);
    }
}
