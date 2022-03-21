package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;

import java.util.List;

public class TableExpertMode extends Table{
    private int bank;
    private Character[] characterCards;
    private boolean professorTie;
    private ColorS noInfluenceColor;

    public TableExpertMode(List<Player> players){
        super(players);

        //Pescare carte personaggio
    }

    public void deposit(int coins){
        this.bank += coins;
    }

    @Override
    private void setupIsland(){

    }

    @Override
    public Player professorOwner(ColorS color){

    }

    public void resetCardsEffect(){
        this.professorTie=false;
        for (IslandExpertMode i: this.islands){
            i.setCountTowers(true);
        }
        for (PlayerExpertMode p : this.players){
            p.setAdditionalInfluence(false);
        }
        this.noInfluenceColor=null;
    }

    public void setNoInfluenceColor(ColorS noInfluenceColor) {
        this.noInfluenceColor = noInfluenceColor;
    }

    public void setProfessorTie(boolean professorTie) {
        this.professorTie = professorTie;
    }
}
