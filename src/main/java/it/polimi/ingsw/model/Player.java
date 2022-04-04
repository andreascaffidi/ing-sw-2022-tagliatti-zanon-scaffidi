package it.polimi.ingsw.model;
import it.polimi.ingsw.exceptions.AssistantNotFoundException;
import it.polimi.ingsw.exceptions.MovementNotValidException;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;

import java.util.*;

public class Player {
    private String username;
    private List<Assistant> assistantDeck;
    private SchoolBoard schoolBoard;
    private int tagTeam;
    private ColorT towerColor;
    private Stack<Assistant> discardPile;
    public Player(String username) {
        this.username = username;
        this.assistantDeck = new ArrayList<>();
        this.discardPile = new Stack<>();
    }

    public Player(String username, int tagTeam) {
        this.username = username;
        this.tagTeam = tagTeam;
        this.assistantDeck = new ArrayList<>();
        this.discardPile = new Stack<>();
    }

    public Player(String username, List<Assistant> assistantDeck, int tagTeam, ColorT towerColor) {
        this.username = username;
        this.towerColor = towerColor;
        this.assistantDeck = assistantDeck;
        this.discardPile = new Stack<>();
        this.tagTeam = tagTeam;
    }

    public String getUsername() {
        return username;
    }

    public List<Assistant> getAssistantDeck() {
        return assistantDeck;
    }

    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

    public Stack<Assistant> getDiscardPile() {
        return discardPile;
    }

    public int getTagTeam() {
        return tagTeam;
    }

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

    public ColorT getTowerColor() {
        return towerColor;
    }

    public Assistant getAssistant(int value) throws AssistantNotFoundException {
        for(Assistant assistant : assistantDeck){
            if(assistant.getValue() == value)
                return assistant;
        }
        throw new AssistantNotFoundException();
    }

    public void addToDiscardPile(Assistant assistant){
        this.discardPile.push(assistant);
        this.assistantDeck.remove(assistant);
    }

    public void setTowerColor(ColorT towerColor) {
        this.towerColor = towerColor;
    }

    public void setSchoolBoard(SchoolBoard schoolBoard) {
        this.schoolBoard = schoolBoard;
    }

    public void validMovement(int movement) throws MovementNotValidException {
        if (movement <= 0 || movement > this.discardPile.peek().getMotherNatureMovements()){
            throw new MovementNotValidException();
        }
    }
}
