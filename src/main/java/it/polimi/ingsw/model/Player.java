package it.polimi.ingsw.model;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;

import java.util.*;

public class Player {
    private String username;
    private List<Assistant> assistantDeck;
    private SchoolBoard schoolBoard;
    private List<Assistant> discardPile;
    private int tagTeam;
    private ColorT towerColor;

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

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if(!(o instanceof Player))
            return false;
        Player p = (Player)o;
        return this.username == p.username;
    }

    public ColorT getTowerColor() {
        return towerColor;
    }

    public void setTowerColor(ColorT towerColor) {
        this.towerColor = towerColor;
    }
}
