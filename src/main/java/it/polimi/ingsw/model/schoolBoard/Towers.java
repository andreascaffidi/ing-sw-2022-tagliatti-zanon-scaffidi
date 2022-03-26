package it.polimi.ingsw.model.schoolBoard;
import it.polimi.ingsw.model.pawns.Tower;
import java.util.*;

public class Towers {
    private List<Tower> towers;

    public Towers(){
        towers = new ArrayList<Tower>();
    }

    public List<Tower> getTowers() {
        return towers;
    }

    /**
     * adds Tower to Towers
     * @param tower
     */

    public void addTower(Tower tower){
        towers.add(tower);
    }

    /**
     * removes Tower from the last position of Towers
     * @return
     */
    public Tower removeLastTower(){
        return towers.remove(towers.size()-1);
    }
}
