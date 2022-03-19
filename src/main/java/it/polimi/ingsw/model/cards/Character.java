package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.Table;

public interface Character {
    private String name;
    private int cost;

    public Character(String name, int cost){
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void incrementCost(){
        cost = cost++;
    }

    public void activate(Table table, Controller controller){
        //TODO: qua va modellata l'interazione con il controller
    }
}
