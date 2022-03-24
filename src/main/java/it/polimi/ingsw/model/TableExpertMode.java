package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.islands.IslandExpertMode;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;

import java.util.ArrayList;
import java.util.List;
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
    private PlayerExpertMode[] players;               //aggiunto l'attributo players


    public TableExpertMode(List<Player> players) {
        super(players);
        this.bank = NUM_OF_COINS - players.size() * NUM_OF_COINS_SETUP;
        this.characterCards = new Character[NUM_OF_CHARACTER_CARDS];
        this.setupCharacterCards(); //todo:fare implementazione

        this.bag = new Bag();
        this.numberOfPlayers = players.size();

        this.setupStudents();
        this.setupPlayers(players);
        this.setupIslands();
        this.setupClouds();
        this.setupProfessors();

        this.setupAssistantCards();
        this.setupSchoolboards();
    }

    @Override
    //FIXME: esegue il cast ma non è bellissimo
    protected void setupPlayers(List<Player> players) {
        this.players = new PlayerExpertMode[numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++) {
            //Assegno un colore al giocatore
            players.get(i).setTowerColor(ColorT.values()[i]);
            this.players[i] = (PlayerExpertMode) players.get(i);
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
                this.bag.addStudent(this.students.remove(this.students.size()));
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
        for(int i = 0; i < this.numberOfPlayers; i++){
            Player player = this.players[i];
            SchoolBoard schoolBoard = new SchoolBoard(player);
            for(int j=0; j<NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW; j++){
                schoolBoard.getEntrance().addStudent(this.bag.drawStudent());
            }
            //piazzo le torri solo se i giocatori sono 3 o meno oppure 4 ma in questo caso solo ai primi 2 giocatori
            if(this.numberOfPlayers <= 3 || (this.numberOfPlayers == 4 && i < 3)){
                for(int j=0;i<NUM_OF_TOWER_AT_SETUP;j++){
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
        return this.getCurrentPlayer();
    }

   @Override
    public PlayerExpertMode[] getPlayers() {
        PlayerExpertMode[] playersReturn = new PlayerExpertMode[this.numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++) {
            playersReturn[i] = this.players[i];
        }
        return playersReturn;
    }
}
