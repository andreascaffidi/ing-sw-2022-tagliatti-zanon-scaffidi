package it.polimi.ingsw.model;
import it.polimi.ingsw.exceptions.AssistantNotFoundException;
import it.polimi.ingsw.exceptions.GameException;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;
import it.polimi.ingsw.network.client.reducedModel.ReducedAssistantDeck;
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * class player
 */
public class Player {

    private final String username;
    private final List<Assistant> assistantDeck;
    private SchoolBoard schoolBoard;
    private int tagTeam;
    private ColorT towerColor;
    private final Stack<Assistant> discardPile;

    /**
     * builds a player for a 2 or 3 players match
     * @param username player username
     */
    public Player(String username) {
        this.username = username;
        this.assistantDeck = new ArrayList<>();
        this.discardPile = new Stack<>();
    }

    /**
     * builds a player for a 4-players match
     * @param username player username
     * @param tagTeam player tag team
     */
    public Player(String username, int tagTeam) {
        this.username = username;
        this.tagTeam = tagTeam;
        this.assistantDeck = new ArrayList<>();
        this.discardPile = new Stack<>();
    }

    /**
     * gets player username
     * @return player username
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets player assistant deck
     * @return player assistant deck
     */
    public List<Assistant> getAssistantDeck() {
        return assistantDeck;
    }

    /**
     * gets player school board
     * @return player school board
     */
    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

    /**
     * gets player discard pile
     * @return player discard pile
     */
    public Stack<Assistant> getDiscardPile() {
        return discardPile;
    }

    /**
     * gets player tag team
     * @return player tag team
     */
    public int getTagTeam() {
        return tagTeam;
    }

    /**
     * override of the "equals" method for player
     * @param o object to confront
     * @return if two players are equals
     */
    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if(!(o instanceof Player))
            return false;
        Player p = (Player)o;
        return this.username.equals(p.username);
    }

    /**
     * gets player tower color
     * @return player tower color
     */
    public ColorT getTowerColor() {
        return towerColor;
    }

    /**
     * gets player assistant
     * @param value assistant value
     * @return player assistant
     * @throws AssistantNotFoundException assistant not found in player assistant deck
     */
    public Assistant getAssistant(int value) throws AssistantNotFoundException {
        for(Assistant assistant : assistantDeck){
            if(assistant.getValue() == value)
                return assistant;
        }
        throw new AssistantNotFoundException("Assistant not found");
    }

    /**
     * adds assistant to discard pile
     * @param assistant assistant to add
     */
    public void addToDiscardPile(Assistant assistant){
        this.discardPile.push(assistant);
        this.assistantDeck.remove(assistant);
    }

    /**
     * sets tower color
     * @param towerColor tower color
     */
    public void setTowerColor(ColorT towerColor) {
        this.towerColor = towerColor;
    }

    /**
     * sets player school board
     * @param schoolBoard player school board
     */
    public void setSchoolBoard(SchoolBoard schoolBoard) {
        this.schoolBoard = schoolBoard;
    }

    /**
     * checks if mother nature's movement is valid
     * @param movement mother nature's movement
     * @throws GameException invalid chosen movement
     */
    public void validMovement(int movement) throws GameException {
        if (movement <= 0 || movement > this.discardPile.peek().getMotherNatureMovements()){
            throw new GameException("Not valid movement");
        }
    }

    /**
     * gets a reduced version of the entire player's board, including player's assistant deck
     * @return reduced board
     */
    public ReducedBoard reduceBoard()
    {
        return new ReducedBoard(this.username, this.towerColor,
                this.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(ColorS.YELLOW),
                this.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(ColorS.BLUE),
                this.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(ColorS.RED),
                this.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(ColorS.PINK),
                this.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(ColorS.GREEN),
                this.getSchoolBoard().getEntrance().getStudents().stream().map(Student::getColor).collect(Collectors.toList()),
                this.getSchoolBoard().getProfessorTable().getProfessors().stream().map(Professor::getColor).collect(Collectors.toList()),
                this.getSchoolBoard().getTowerBoard().getTowers().size(),
                new ReducedAssistantDeck(this.getAssistantDeck().stream().map(Assistant::reduceAssistant).collect(Collectors.toList()),
                        !this.discardPile.isEmpty() ? this.discardPile.peek().reduceAssistant() : null));
    }
}
