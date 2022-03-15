package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.Player;

public class SchoolBoard {
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
