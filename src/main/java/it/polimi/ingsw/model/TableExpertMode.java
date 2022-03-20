package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.islands.IslandExpertMode;
import it.polimi.ingsw.model.pawns.Professor;

import java.util.List;

public class TableExpertMode extends Table{
    private int bank;
    private Character[] characterCards;
    private boolean professorTie;

    public TableExpertMode(List<Player> players){
        super(players);

        //Pescare carte personaggio
    }

    public void deposit(int coins){
        this.bank += coins;
    }

    @Override
    public Player professorOwner(ColorS color){

    }

    public void resetCardsEffect(){

    }

    @Override
    public Player getSupremacy(IslandExpertMode island) {
        Player oldIslandKing = null, newIslandKing = null;
        int playerInfluence = 0, maxInfluence = 0;
        boolean parity = false;

        if (island.getTower() != null) {
            oldIslandKing = island.getTower().getOwner();
        }

        for (Player p : this.getPlayers()) {
            for (ColorS c : ColorS.values()) {
                Professor pr = this.getProfessor(c);
                if (c != island.getNoInfluenceColor() && pr.getOwner().equals(p)) {
                    playerInfluence = playerInfluence + island.numStudent(pr.getColor());
                }
            }
            if (p.equals(oldIslandKing) && island.isCountTowers()) {
                playerInfluence = playerInfluence + island.getNumOfTowers();
            }

            if (island.isAdditionalInfluence()){        //sarebbe meglio mettere l'attributo sul playerExpertMode
                playerInfluence+=2;
            }

            if (playerInfluence > maxInfluence) {
                maxInfluence = playerInfluence;
                newIslandKing = p;
                parity = false;
            } else if (playerInfluence == maxInfluence){
                parity = true;
            }
            playerInfluence = 0;
        }

        if (parity == true){
            return oldIslandKing;
        }
        else {
            return newIslandKing;
        }
    }
}
