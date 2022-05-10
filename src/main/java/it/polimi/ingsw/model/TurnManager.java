package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.RoundPhases;

import java.util.*;

/**
 * class turn manager that manages turns
 */
public class TurnManager {
    private final List<Player> clockwise;
    private final List<Player> planningTurn;
    private final PriorityQueue<Player> actionTurn;
    private RoundPhases phase;

    /**
     * builds turn manager
     * @param players players of the match
     */
    public TurnManager(List<Player> players){
        this.phase = RoundPhases.PLANNING;
        this.clockwise = players;
        this.planningTurn = new ArrayList<>(players);
        this.planningTurn.remove(0);
        this.actionTurn = new PriorityQueue<>(4, new PlayerComparator());
    }

    /**
     * adds a player to the action turn queue
     * @param player player to add
     */
    public void orderPlayer(Player player){
        this.actionTurn.add(player);
    }

    /**
     * creates a new planning turn order based on clockwise order
     * @param newRoundPlayer first player of a new round
     */
    private void newPlanningTurn(Player newRoundPlayer){
        int index = clockwise.indexOf(newRoundPlayer);
        for (int i = 0; i < clockwise.size(); i++){
            planningTurn.add(clockwise.get(index));
            index = (index + 1) % clockwise.size();
        }
    }

    /**
     * chooses next player for planning and action turn
     * @return next player
     */
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

    public RoundPhases getPhase() {
        return phase;
    }
}

/**
 * player comparator private class
 */
class PlayerComparator implements Comparator<Player>{

    /**
     * override of compare method
     * compares two player to choose action turn order
     * @param p1 first player to compare
     * @param p2 second player to compare
     * @return integer which represents the comparison
     */
    public int compare(Player p1, Player p2) {
        if (p1.getDiscardPile().peek().getValue() >= p2.getDiscardPile().peek().getValue()) {
            return 1;
        }
        if (p1.getDiscardPile().peek().getValue() < p2.getDiscardPile().peek().getValue()) {
            return -1;
        }
        return 0;
    }
}

