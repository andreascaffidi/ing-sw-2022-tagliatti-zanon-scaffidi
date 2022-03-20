package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.islands.IslandGroup;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;

import java.util.*;

public class Table {

    private static final int NUM_OF_ISLANDS = 12;
    public static final int NUM_OF_STUDENTS_PER_COLOR = 26;
    public static final int NUM_OF_STUDENTS_SETUP = 2;
    public static final int NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW = 7;


    private int numberOfPlayers;
    private List<Island> islands;
    private MotherNature motherNature;
    private Bag bag;
    private List<Cloud> clouds;
    private List<SchoolBoard> boards;
    private Player[] players;               //aggiunto l'attributo players

    //todo: attributi aggiunti dopo
    private List<Professor> professors;
    private List<Student> students;

    public Table(List<Player> players){
        this.bag = new Bag();
        this.numberOfPlayers = players.size();
        this.setupStudents();

        //inizializzo players
        //todo: forse conviene lasciare una lista per i giocatori
        this.players = new Player[numberOfPlayers];
        for (int i = 0; i<this.numberOfPlayers; i++){
            this.players[i] = players.get(i);
        }


        //PUNTO 1-2-3-5
        this.setupIsland();

        //PUNTO 5
        this.clouds = new ArrayList<Cloud>();
        // The number of clouds is the same as the number of players
        for(int i = 0; i < this.numberOfPlayers; i++){
            this.clouds.add(new Cloud());
        }

        //PUNTO 6
        this.setupProfessors();

        //PUNTO 7
        for(int i = 0; i < this.numberOfPlayers; i++){
          this.boards.add(new SchoolBoard(players.get(i)));
        }
        //TODO PUNTO 8 SU SCHOOL BOARD

        //TODO PUNTO 9 INTERAZIONE GIOCATORE
        //scegliere mago


        //PUNTO 10
        for(int i = 0; i < this.boards.size(); i++){
            List<Student> studentsToAdd = new ArrayList<Student>();
            for(int j=0; j<NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW; j++){
                this.boards.get(i).getEntrance().addStudent(this.bag.drawStudent());
            }
        }
    }

    private void setupIsland(){
        this.islands = new ArrayList<Island>();
        for (int i = 0; i < NUM_OF_ISLANDS; i++){
            this.islands.add( new Island());
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

    /**
     * Creates all 130 students and shuffle them
     */
    public void setupStudents(){
        this.students = new ArrayList<Student>();
        for (ColorS color : ColorS.values()) {
            for (int i=0;i<NUM_OF_STUDENTS_PER_COLOR; i++){
                this.students.add(new Student(color));
            }
        }
        Collections.shuffle(this.students);
    }

    /**
     * Creates all professors, one for each color
     */
    public void setupProfessors(){
        this.professors = new ArrayList<Professor>();
        for (ColorS color : ColorS.values()) {
            professors.add(new Professor(color));
        }
    }

    /**
     * Set the current island of mother nature
     * @param motherNatureIsland
     */
    public void setMotherNature(Island motherNatureIsland){
        motherNatureIsland.setMotherNature(true);
        this.motherNature.setIsland(motherNatureIsland);
    }

    public void moveMotherNature(){

    }


    public Professor getProfessor(ColorS color){
        return this.professors.stream().filter(professor->professor.getColor() == color)
                .findAny().orElseThrow(()->new RuntimeException("Professor not found"));
    }

    public IslandGroup newIslandGroup(List<Island> islands){

    }

    public List<Island> canIUnify(){

    }

    //TODO: testare il funzionamento
    public Island motherNatureIsland(){
        return this.islands.stream().filter(island -> island.isMotherNature())
                .findFirst().orElseThrow(() -> new RuntimeException("Mother Nature not found"));
    }

    //TODO: testare perch non sono convinto ahahah
    public Player getPlayerWithMaxTowers(){
        Map<Player,Integer> towers = new HashMap<>();
        for(int i = 0; i< this.players.length; i++) {
            for (Island island : islands) {
                if (island.getTower().getOwner() == this.players[i]) {
                    towers.replace(players[i],towers.get(players[i]),towers.get(players[i])+1);
                }
            }
        }
        return Collections.max(towers.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
    //TODO: testare perchè non sono convinto, come sopra; più che altro perche ritorno la chiave della mappa
    // che credo non sia lo stesso oggetto player in players
    // (un po come nei foreach)
    public Player getPlayerWithMaxProfessor(){
        Map<Player,Integer> professors = new HashMap<>();
        for(int i = 0; i< this.boards.size(); i++) {
            int nProfessor = boards.get(i).getProfessorTable().getProfessors().size();
            professors.put(boards.get(i).getPlayer(),nProfessor);
        }
        return Collections.max(professors.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }


    // get winner da fare nel controller
    @Deprecated
    public Player getWinner(){
        return null;
    }

    //TODO: testare il funzionamento
    public Player professorOwner(ColorS color){
        return this.boards.stream().filter(board -> board.getProfessorTable().hasProfessor(color))
                .findFirst().orElseThrow(() -> new RuntimeException("Professor owner not found")).getPlayer();
    }

    /**
     * Calculates the influence of every player on the island and returns the player who has the highest influence:
     * for each player checks if he has a specific professor and in that case increments a counter (that calculates influence)
     * of the number of students on the island with the same color of the specific professor. If the player in exam is the
     * owner of the tower/s on the island/s, increments influence of the number of towers on the island. If the calculated
     * influence is bigger than the highest influence, it becomes the highest influence; else if they are equals a boolean
     * flag "parity" turns on. At the end if there's a parity of the highest influence calculated, the method returns
     * the old owner of the tower (if there isn't a tower it returns null); else it returns the player who has the
     * highest influence.
     * @param island
     * @return player who has the highest influence on the island
     */
    public Player getSupremacy(Island island) {
        Player oldIslandKing = null, newIslandKing = null;
        int playerInfluence = 0, maxInfluence = 0;
        boolean parity = false;

        if (island.getTower() != null) {
            oldIslandKing = island.getTower().getOwner();
        }

        for (Player p : players) {
            for (Professor pr : professors) {
                if (pr.getOwner().equals(p)) {
                    playerInfluence = playerInfluence + island.numStudent(pr.getColor());
                }
            }
            if (p.equals(oldIslandKing)) {
                playerInfluence = playerInfluence + island.numOfTowers();
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
