package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.islands.IslandExpertMode;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.model.schoolBoard.SchoolBoardExpertMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static it.polimi.ingsw.model.PlayerExpertMode.NUM_OF_COINS_SETUP;

public class TableExpertMode extends Table {
    private final int NUM_OF_CHARACTER_CARDS = 3;
    private final int NUM_OF_COINS = 20;

    private int bank;
    private Character[] characterCards;
    private boolean professorTie;
    private ColorS noInfluenceColor;


    private List<IslandExpertMode> islands;
    private PlayerExpertMode[] players;

    //TODO; inizializzare
    private Map<Player,Integer> playersCoins;
    private Map<Player, Boolean> playersAdditionalInfluence;

    private Map<Island, Boolean> entryTiles;
    private Map<Island, Boolean> countTowers;



    public TableExpertMode(List<PlayerExpertMode> players) {
        super();
        this.bank = NUM_OF_COINS - players.size() * NUM_OF_COINS_SETUP;
        this.characterCards = new Character[NUM_OF_CHARACTER_CARDS];
        this.setupCharacterCards(); //todo: fare implementazione

        this.bag = new Bag();
        this.numberOfPlayers = players.size();

        this.setupStudents();
        this.setupPlayers2(players);
        this.setupIslands();
        this.setupClouds();
        this.setupProfessors();
        this.setupAssistantCards();
        this.setupSchoolboards();
    }

    //TODO implementazione
    private void setupMaps(){

    }

    //FIXME: esegue il cast ma non è bellissimo
    protected void setupPlayers2(List<PlayerExpertMode> players) {
        this.setCurrentPlayer(players.get(0));
        this.players = new PlayerExpertMode[numberOfPlayers];
        for (int i = 0; i<this.numberOfPlayers; i++){
            this.players[i] = players.get(i);
            if(this.numberOfPlayers < 4){
                this.players[i].setTowerColor(ColorT.values()[i]);
            }
            else{
                this.players[i].setTowerColor(ColorT.values()[players.get(i).getTagTeam()-1]);
            }
        }
    }

    @Override
    protected void setupIslands() {
        this.islands = new ArrayList<>();
        for (int i = 0; i < NUM_OF_ISLANDS; i++) {
            this.islands.add(new IslandExpertMode(i));
        }

        // aggiungo al sacchetto 2 studenti per colore e li tolgo dal totale: PUNTO 3 SETUP REGOLE
        for (ColorS color : ColorS.values()) {
            for (int i = 0; i < NUM_OF_STUDENTS_SETUP; i++) {
                this.bag.addStudent(this.students.remove(this.students.size()-1));
            }
        }

        //prendo un isola a caso
        int motherNatureIslandIndex = new Random().nextInt(NUM_OF_ISLANDS);
        Island motherNatureIsland = islands.get(motherNatureIslandIndex);

        //instanzio madre natura
        this.motherNature = new MotherNature(motherNatureIsland);
        this.setMotherNature(motherNatureIsland);

        int oppositeIslandIndex = (motherNatureIslandIndex + (NUM_OF_ISLANDS / 2)) % NUM_OF_ISLANDS;

        for (int i = 0; i < this.islands.size(); i++) {
            if (i != motherNatureIslandIndex && i != oppositeIslandIndex) {
                this.islands.get(i).addStudent(bag.drawStudent());
            }
        }

        // aggiungo al bag tutti gli studenti rimasti
        this.bag.addStudents(students);
    }

    protected void setupCharacterCards() {
        //todo leggere il json e instanziare carte
    }

    @Override
    protected void setupSchoolboards(){
        this.boards = new ArrayList<>();
        for(int i = 0; i < this.numberOfPlayers; i++){
            PlayerExpertMode player = this.players[i];
            SchoolBoardExpertMode schoolBoard = new SchoolBoardExpertMode(player);
            this.players[i].setSchoolBoard(schoolBoard);
            for(int j=0; j<NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW; j++){
                schoolBoard.getEntrance().addStudent(this.bag.drawStudent());
            }
            //piazzo le torri solo se i giocatori sono 3 o meno oppure 4 ma in questo caso solo ai primi 2 giocatori
            if(this.numberOfPlayers <= 3 || (this.numberOfPlayers == 4 && i < 2)){
                for(int j=0;j<NUM_OF_TOWER_AT_SETUP;j++){
                    schoolBoard.getTowers().addTower(new Tower(player.getTowerColor(),player));
                }
            }
            this.boards.add(schoolBoard);
        }
    }

    public void deposit(int coins) {
        this.bank += coins;
    }


    public void resetCardsEffect() {
        this.professorTie = false;
        for (IslandExpertMode i : this.islands) {
            i.setCountTowers(true);
        }
        for (PlayerExpertMode p : this.players) {
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

    public boolean isProfessorTie() {
        return professorTie;
    }

    public Player getSupremacy(IslandExpertMode island) {
        if (island.isEntryTile()) return island.getTower().getOwner();

        Player oldIslandKing = null, newIslandKing = null;
        int playerInfluence = 0, maxInfluence = 0;
        boolean parity = false;

        if (island.getTower() != null) {
            oldIslandKing = island.getTower().getOwner();
        }

        for (PlayerExpertMode p : this.getPlayers()) {
            for (ColorS c : ColorS.values()) {
                Professor pr = this.getProfessor(c);
                if (c != this.noInfluenceColor && p.equals(pr.getOwner())) {
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
    public IslandExpertMode getIsland(int id) {
        for (IslandExpertMode i : this.islands) {
            if (i.getId() == id) {
                return i;
            }
        }
        throw new RuntimeException("Island not found");
    }


    @Override
    public PlayerExpertMode getCurrentPlayer() {
        //TODO: protebbero esserci errori
        // a runtime perche ritorna un tipo Player
        return (PlayerExpertMode) super.getCurrentPlayer();
    }

   @Override
    public PlayerExpertMode[] getPlayers() {
        PlayerExpertMode[] playersReturn = new PlayerExpertMode[this.numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++) {
            playersReturn[i] = this.players[i];
        }
        return playersReturn;
    }

    @Override
    public IslandExpertMode motherNatureIsland(){
        return this.islands.stream().filter(island -> island.isMotherNature())
                .findFirst().orElseThrow(() -> new RuntimeException("Mother Nature not found"));
    }

    @Override
    public void moveMotherNature(int movement){
        int id = this.motherNatureIsland().getId();
        id = (id + movement) % this.islands.size();
        this.setMotherNature(this.getIsland(id));
    }


}
