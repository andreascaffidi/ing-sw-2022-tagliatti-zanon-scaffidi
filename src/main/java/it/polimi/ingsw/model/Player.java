package it.polimi.ingsw.model;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;

import java.util.*;

public class Player {
    private String username;
    private List<Assistant> assistantDeck;
    private SchoolBoard schoolBoard;
    private List<Assistant> discardPile;
    private int tagTeam;

    public Player(String username) {
        this.username = username;
    }

    public Player(String username, int tagTeam) {
        this.username = username;
        this.tagTeam = tagTeam;
    }

    public Player(String username, List<Assistant> assistantDeck, SchoolBoard schoolBoard, int tagTeam) {
        this.username = username;
        this.assistantDeck = assistantDeck;
        this.schoolBoard = schoolBoard;
        this.discardPile = new ArrayList<Assistant>();
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

    public List<Assistant> getDiscardPile() {
        return discardPile;
    }

    public int getTagTeam() {
        return tagTeam;
    }

    /**
     * definito Override metodo equals()
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Player)) return false;
        if(o==null) return false;
        Player that = (Player) o;
        return this.username==that.username;
    }
}
