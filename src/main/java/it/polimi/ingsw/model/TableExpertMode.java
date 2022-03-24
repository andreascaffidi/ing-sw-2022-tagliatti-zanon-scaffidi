package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.islands.IslandExpertMode;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;
import it.polimi.ingsw.model.schoolBoard.SchoolBoardExpertMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static it.polimi.ingsw.model.PlayerExpertMode.NUM_OF_COINS_SETUP;

public class TableExpertMode extends Table{
    private final int NUM_OF_CHARACTER_CARDS = 3;
    private final int NUM_OF_COINS = 20;

    private int bank;
    private Character[] characterCards;
    private boolean professorTie;
    private ColorS noInfluenceColor;


    private List<IslandExpertMode> islands;
    private List<PlayerExpertMode> players;               //aggiunto l'attributo players

    protected List<SchoolBoardExpertMode> boards;       //aggiunto



    public TableExpertMode(List<PlayerExpertMode> players){
        super();
        this.players = players;
        this.bank = NUM_OF_COINS - players.size()*NUM_OF_COINS_SETUP;
        this.characterCards = new Character[NUM_OF_CHARACTER_CARDS];
        this.setupCharacterCards(); //todo:fare implementazione

        this.bag = new Bag();
        this.numberOfPlayers = players.size();

        this.setupStudents();
        //this.setupPlayers(players);
        this.setupIslands();
        this.setupClouds();
        this.setupProfessors();

        /*
        //todo:schoolboardexpertmode
        for(int i = 0; i < this.numberOfPlayers; i++){
            this.boards.add(new SchoolBoard(players.get(i)));
        }*/

        for (int i = 0; i < this.numberOfPlayers; i++){
            this.boards.add(players.get(i).getSchoolBoard());
        }

        this.setupAssistantCards();
        this.setupSchoolboards();


    }

    /*
    @Override
    //FIXME: esegue il cast ma non è bellissimo
    protected void setupPlayers(List<Player>players){
        this.players = new PlayerExpertMode[numberOfPlayers];
        for (int i = 0; i<this.numberOfPlayers; i++){
            //Assegno un colore al giocatore
            players.get(i).setTowerColor(ColorT.values()[i]);
            this.players[i] = (PlayerExpertMode) players.get(i);
        }
    }*/

    @Override
    protected void setupIslands(){
        this.islands = new ArrayList<>();
        for (int i = 0; i < NUM_OF_ISLANDS; i++){
            this.islands.add(new IslandExpertMode(i));
        }

        // aggiungo al sacchetto 2 studenti per colore e li tolgo dal totale: PUNTO 3 SETUP REGOLE
        for (ColorS color : ColorS.values()) {
            for (int i=0;i<NUM_OF_STUDENTS_SETUP; i++){
                this.bag.addStudent(this.students.remove(this.students.size()));
            }
        }

        //prendo un isola a caso
        int motherNatureIslandIndex = new Random().nextInt(NUM_OF_ISLANDS);
        Island motherNatureIsland = islands.get(motherNatureIslandIndex);

        //instanzio madre natura
        this.motherNature = new MotherNature(motherNatureIsland);
        this.setMotherNature(motherNatureIsland);

        int oppositeIslandIndex = (motherNatureIslandIndex + (NUM_OF_ISLANDS/2)) % NUM_OF_ISLANDS;

        for(int i = 0; i<this.islands.size(); i++){
            if(i != motherNatureIslandIndex && i != oppositeIslandIndex) {
                this.islands.get(i).addStudent(bag.drawStudent());
            }
        }

        // aggiungo al bag tutti gli studenti rimasti
        this.bag.addStudents(students);
    }

    public void setupCharacterCards(){
        //todo leggere il json e instanziare carte
    }

    public void deposit(int coins){
        this.bank += coins;
    }




    public void resetCardsEffect(){
        this.professorTie = false;
        for (IslandExpertMode i: this.islands){
            i.setCountTowers(true);
        }
        for (PlayerExpertMode p : this.players){
            p.setAdditionalInfluence(false);
        }
        this.noInfluenceColor = null;
    }

    public void setNoInfluenceColor(ColorS noInfluenceColor) {
        this.noInfluenceColor = noInfluenceColor;
    }

    public void setProfessorTie(boolean professorTie) {
        this.professorTie = professorTie;
    }


    public Player getSupremacy(IslandExpertMode island) {
        if(island.isEntryTile()) return island.getTower().getOwner();

        Player oldIslandKing = null, newIslandKing = null;
        int playerInfluence = 0, maxInfluence = 0;
        boolean parity = false;

        if (island.getTower() != null) {
            oldIslandKing = island.getTower().getOwner();
        }

        for (PlayerExpertMode p : this.getPlayersExpertMode()) {
            for (ColorS c : ColorS.values()) {
                Professor pr = this.getProfessor(c);
                if (c != this.noInfluenceColor && pr.getOwner().equals(p)) {
                    playerInfluence = playerInfluence + island.numStudent(c);
                }
            }
            if (p.equals(oldIslandKing) && island.isCountTowers()) {
                playerInfluence = playerInfluence + island.getNumOfTowers();
            }

            if (p.isAdditionalInfluence()){
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

    @Override
    public IslandExpertMode getIsland(int id){
        for(IslandExpertMode i: this.islands)
        {
            if(i.getId() == id)
            {
                return i;
            }
        }
        throw new RuntimeException("Island not found");
    }


    //TODO: implementare
    public PlayerExpertMode getCurrentPlayer(){
        return new PlayerExpertMode("Prova",null,1,ColorT.BLACK);
    }

    // TODO: Copia
    public List<PlayerExpertMode> getPlayersExpertMode() {
        return this.players;
    }

}
