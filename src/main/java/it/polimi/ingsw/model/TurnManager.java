package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.RoundPhases;

import java.util.*;

public class TurnManager {
    private List<Player> clockwise;
    private List<Player> planningTurn;
    private PriorityQueue<Player> actionTurn;
    private RoundPhases phase;

    public TurnManager(List<Player> players){
        this.phase = RoundPhases.PLANNING;
        this.clockwise = players;
        this.planningTurn = new ArrayList<>(players);
        this.planningTurn.remove(0);
        this.actionTurn = new PriorityQueue<>(4, new PlayerComparator());
    }

    public void orderPlayer(Player player){
        this.actionTurn.add(player);
    }

    private void newPlanningTurn(Player newRoundPlayer){
        int index = clockwise.indexOf(newRoundPlayer);
        for (int i = 0; i < clockwise.size(); i++){
            planningTurn.add(clockwise.get(index));
            index = (index + 1) % clockwise.size();
        }
    }

    public Player nextPlayer(){
        if (phase == RoundPhases.PLANNING){
            Player nextPlayer = planningTurn.remove(0);
            if (planningTurn.isEmpty()){
                phase = RoundPhases.ACTION;
            }
            return nextPlayer;
        }
        else if (phase == RoundPhases.ACTION){
            Player nextPlayer = actionTurn.poll();
            if (actionTurn.size() == clockwise.size() - 1){
                newPlanningTurn(nextPlayer);
            }
            if (actionTurn.isEmpty()){
                phase = RoundPhases.PLANNING;
            }
            return nextPlayer;
        }
        else{
            throw new RuntimeException("Idk where you are");
        }
    }
}

class PlayerComparator implements Comparator<Player>{
    public int compare(Player p1, Player p2) {
        if (p1.getDiscardPile().peek().getValue() >= p2.getDiscardPile().peek().getValue()) {
            return 1;
        }
        return -1;
    }
}

