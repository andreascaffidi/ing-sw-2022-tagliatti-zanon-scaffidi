package it.polimi.ingsw.model.schoolBoard;


/**
 * school board
 */
public class SchoolBoard {

    private final Entrance entrance;
    private final DiningRoom diningRoom;
    private final ProfessorTable professorTable;
    private final TowerBoard towerBoard;

    /**
     * builds player's school board
     */
    public SchoolBoard(){
        this.entrance = new Entrance();
        this.diningRoom = new DiningRoom();
        this.professorTable = new ProfessorTable();
        this.towerBoard = new TowerBoard();
    }

    /**
     * gets school board entrance
     * @return school board entrance
     */
    public Entrance getEntrance() {
        return entrance;
    }

    /**
     * gets school board dining room
     * @return school board dining room
     */
    public DiningRoom getDiningRoom() {
        return diningRoom;
    }

    /**
     * gets school board professor table
     * @return school board professor table
     */
    public ProfessorTable getProfessorTable() {
        return professorTable;
    }

    /**
     * gets school board tower board
     * @return school board tower board
     */
    public TowerBoard getTowerBoard() {
        return towerBoard;
    }

}
