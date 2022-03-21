package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Tower;

public class SchoolBoard {
    private final int NUM_OF_TOWER = 8;
    private Player player;
    private Entrance entrance;
    private DiningRoom diningRoom;
    private ProfessorTable professorTable;
    private Towers towers;

    public SchoolBoard(Player player){
        this.player = player;
        this.entrance = new Entrance();
        this.diningRoom = new DiningRoom();
        this.professorTable = new ProfessorTable();
        this.towers = new Towers();
        for(int i=0;i<NUM_OF_TOWER;i++){
            this.towers.addTower(new Tower(player.getTowerColor(),player));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Entrance getEntrance() {
        return entrance;
    }

    public DiningRoom getDiningRoom() {
        return diningRoom;
    }

    public ProfessorTable getProfessorTable() {
        return professorTable;
    }

    public Towers getTowers() {
        return towers;
    }

}
