package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.islands.IslandGroup;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;

import java.util.*;

public class Table {
    private List<Island> islands;
    private Bag bag;
    private List<Cloud> clouds;
    private List<SchoolBoard> boards;
    private int numberOfPlayers;
    private MotherNature motherNature;

    public Table(){

    }

    private void setupIsland(){

    }

    public void moveMotherNature(){

    }

    public IslandGroup newIslandGroup(List<Island> islands){

    }

    public List<Island> canIUnify(){

    }

    public Island motherNatureIsland(){

    }

    public Player getWinner(){

    }

    public Player professorOwner(ColorS color){

    }

    /**
     * sull'isola per ogni player calcolo l'influenza che avrebbe e me la salvo su una array temporaneo, trovo il massimo
     * dell'array (massima supremazia), se è uguale allora c'è una parità che posso gestire con un'eccezione, successivamente
     * confronto il player trovato con il proprietario della torre, se è lo stesso o se è null allora ritorno il player calcolato,
     * altrimenti se è diverso confronto la supremazia calcolata del player trovato con la supremazia del proprietario (+1)
     */
    //TODO
    public Player getSupremacy(Island island){
        int count=0, supremacy=0;
        Player player;
        ColorS colorSupremacy;
        List<Student> students = island.getStudents();
        for (ColorS c : ColorS.values()){
            for (Student s : students){
                if (s.getColor()==c){
                    count++;
                }
            }
            if (count > supremacy){
                supremacy = count;
                colorSupremacy=c;
            }
            else if (count == supremacy){
                colorSupremacy=null;
            }
        }
        player = getProfessor(colorSupremacy).getOwner();
        if (island.getTower() == null){
            return player;
        }
        else if (tower.getOwner() == player){
            return player;
        }
        else if(supremacy > island.getSupremacy()+1){
            island.setSupremacy(supremacy);
            return player;
        }
        else{
            return island.getTower().getOwner();
        }
    }

}
