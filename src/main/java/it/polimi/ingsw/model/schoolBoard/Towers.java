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

    public void addTower(Tower tower){
        towers.add(tower);
    }

    public Tower removeLastTower(){
        return towers.remove(towers.size()-1);
    }
}
