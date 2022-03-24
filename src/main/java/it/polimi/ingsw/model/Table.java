package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;
//import org.json.simple.parser.JSONParser;


import java.util.*;

public class Table {

    public static final int NUM_OF_ISLANDS = 12;
    public static final int NUM_OF_STUDENTS_PER_COLOR = 26;
    public static final int NUM_OF_STUDENTS_SETUP = 2;

    //parametri che variano in base al numero di giocatori
    public static int NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW;
    public static int NUM_OF_TOWER_AT_SETUP;
    public static int NUM_OF_STUDENTS_TO_PLACE_ON_CLOUD;



    protected Bag bag;
    protected int numberOfPlayers;
    protected List<Student> students;

    private List<Island> islands;
    protected MotherNature motherNature;
    private List<Cloud> clouds;
    protected List<SchoolBoard> boards;
    private Player[] players;
    private Player currentPlayer;
    private List<Professor> professors;

    //TODO: impementare parti comuni
    public Table(){

    }

    public Table(List<Player> players){
        this.bag = new Bag();
        this.numberOfPlayers = players.size();
        switch (numberOfPlayers){
            case 2 :
            case 4 :
                NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW = 7;
                NUM_OF_TOWER_AT_SETUP = 8;
                NUM_OF_STUDENTS_TO_PLACE_ON_CLOUD = 3;
                break;
            case 3:
                NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW = 9;
                NUM_OF_TOWER_AT_SETUP = 6;
                NUM_OF_STUDENTS_TO_PLACE_ON_CLOUD = 4;
                break;

            default:
                throw new RuntimeException("Too much players");
        }

        this.setupStudents();

        //PARTE DEL PUNTO 8
        this.setupPlayers(players);

        //PUNTO 1-2-3-4
        this.setupIslands();

        //PUNTO 5
        this.setupClouds();

        //PUNTO 6
        this.setupProfessors();

        //PUNTO 7 DOPO


        //PUNTO 9
        this.setupAssistantCards();

        //PUNTO 7 - 10
        this.setupSchoolboards();
    }

    protected void setupPlayers(List<Player>players){
        this.players = new Player[numberOfPlayers];
        for (int i = 0; i<this.numberOfPlayers; i++){
            //Assegno un colore al giocatore
            players.get(i).setTowerColor(ColorT.values()[i]);
            this.players[i] = players.get(i);
        }
    }

    protected void setupIslands(){
        this.islands = new ArrayList<Island>();
        for (int i = 0; i < NUM_OF_ISLANDS; i++){
            this.islands.add(new Island(i));
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
                this.islands.get(i).addStudent(bag.drawStudent());
            }
        }

        // aggiungo al bag tutti gli studenti rimasti
        this.bag.addStudents(students);
    }

    protected void setupClouds(){
        this.clouds = new ArrayList<>();
        // The number of clouds is the same as the number of players
        for(int i = 0; i < this.numberOfPlayers; i++){
            this.clouds.add(new Cloud());
        }
    }

    /**
     * Creates all 130 students and shuffle them
     */
    protected void setupStudents(){
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
    protected void setupProfessors(){
        this.professors = new ArrayList<>();
        for (ColorS color : ColorS.values()) {
            professors.add(new Professor(color));
        }
    }

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


    protected void setupAssistantCards(){
        //JSONParser parser = new JSONParser();

        //TODO LEGGERE JSON E INSTANZIARE LE CARTE CON I VALORI LETTI
    }

    /**
     * Set the current island of mother nature
     * @param motherNatureIsland
     */
    public void setMotherNature(Island motherNatureIsland){
        motherNatureIsland.setMotherNature(true);
        this.motherNature.setIsland(motherNatureIsland);
    }

    public void moveMotherNature(int movement){
        int id = this.motherNatureIsland().getId();
        this.motherNatureIsland().setMotherNature(false);
        id = (id + movement) % this.islands.size();
        this.setMotherNature(this.getIsland(id));
    }


    public Professor getProfessor(ColorS color){
        return this.professors.stream().filter(professor->professor.getColor() == color)
                .findAny().orElseThrow(()->new RuntimeException("Professor not found"));
    }



    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer){
        this.currentPlayer = currentPlayer;
    }


    /**
     * create a group of island from a list of islands: add all the students, set the tower, remove the islands and change
     * all island ids.
     * @param islands : islands to merge
     */
    public void newIslandGroup(List<Island> islands){
        int id_min = islands.stream().map(Island::getId).reduce(0, (id1, id2) -> id1 < id2 ? id1 : id2);
        int id_max = islands.stream().map(Island::getId).reduce(0, (id1, id2) -> id1 > id2 ? id1 : id2);
        Island islandGroup = new Island(id_min);
        for (Island i : islands){
            this.islands.remove(i);
            if (i.isMotherNature()){
                islandGroup.setMotherNature(true);
            }
            islandGroup.addStudents(i.getStudents());
        }
        islandGroup.setTower(islands.get(0).getTower());
        islandGroup.setNumOfTowers(islands.size());
        for (Island i : this.islands){
            if (i.getId() > id_max){
                i.changeId(islands.size()-1);
            }
        }
        this.islands.add(islandGroup);
    }


    //TODO metodo ricorsivo che ritorna le isole da unire
    public List<Island> canIUnify(){
        return new ArrayList<>();
    }

    //TODO: testare il funzionamento
    public Island motherNatureIsland(){
        return this.islands.stream().filter(island -> island.isMotherNature())
                .findFirst().orElseThrow(() -> new RuntimeException("Mother Nature not found"));
    }

    //TODO: testare perche non sono convinto ahahah
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


    //TODO: testare il funzionamento
    public Player professorOwner(ColorS color){
        return this.boards.stream().filter(board -> board.getProfessorTable().hasProfessor(color))
                .findFirst().orElseThrow(() -> new RuntimeException("Professor owner not found")).getPlayer();
    }

    public Bag getBag() {
        return bag;
    }

    public Island getIsland(int id)
    {
        for(Island i: this.islands)
        {
            if(i.getId() == id)
            {
                return i;
            }
        }
        throw new RuntimeException("Island not found");
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
                playerInfluence = playerInfluence + island.getNumOfTowers();
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

    public  List<Island> getIslands() {
        List<Island> clonedIslands = new ArrayList<Island>();
        clonedIslands.addAll(this.islands);
        return clonedIslands;
    }

    public Player[] getPlayers() {
        Player[] playersReturn = new Player[this.numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++) {
            playersReturn[i] = this.players[i];
        }
        return playersReturn;
    }


    /**
     *  Method to call during Planning phase, it place on a selected cloud the correct number of
     *  Students depending on the number of players
     * @param cloud
     */
    public void addStudentsToCloud(Cloud cloud){
        for(int i=0; i < NUM_OF_STUDENTS_TO_PLACE_ON_CLOUD; i++){
            cloud.addStudent(this.getBag().drawStudent());
        }
    }

    public List<Cloud> getClouds() {
        return clouds;
    }
}
