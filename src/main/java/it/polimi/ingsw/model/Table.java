package it.polimi.ingsw.model;
import it.polimi.ingsw.exceptions.ParityTowersException;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Table Class for match control
 */
public class Table {

    public static final int NUM_OF_ISLANDS = 12;
    public static final int NUM_OF_STUDENTS_PER_COLOR = 26;
    public static final int NUM_OF_STUDENTS_SETUP = 2;

    //parametri che variano in base al numero di giocatori
    public int NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW;
    public int NUM_OF_TOWER_AT_SETUP;
    public int NUM_OF_STUDENTS_TO_PLACE_ON_CLOUD;

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

    protected List<Assistant> assistants;


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

    /**
     * sets up the Players depending on the number of Players
     * @param players
     */

    protected void setupPlayers(List<Player>players){
        this.players = new Player[numberOfPlayers];
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

    /**
     * sets up the 12 Islands and motherNature
     */

    protected void setupIslands(){
        this.islands = new ArrayList<Island>();
        for (int i = 0; i < NUM_OF_ISLANDS; i++){
            this.islands.add(new Island(i));
        }

        // aggiungo al sacchetto 2 studenti per colore e li tolgo dal totale: PUNTO 3 SETUP REGOLE
        for (ColorS color : ColorS.values()) {
            for (int i=0;i<NUM_OF_STUDENTS_SETUP; i++){
                Student studentToAdd = this.students.stream().filter(s -> s.getColor()==color).findFirst().orElseThrow(()->new RuntimeException("Student not found"));
                this.students.remove(studentToAdd);
                this.bag.addStudent(studentToAdd);
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
     * sets up the Clouds dependently on the number of Players
     */

    protected void setupClouds(){
        this.clouds = new ArrayList<>();
        // The number of clouds is the same as the number of players
        for(int i = 0; i < this.numberOfPlayers; i++){
            this.clouds.add(new Cloud());
        }
    }

    /**
     * creates all 130 students and shuffles them
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
     * creates all Professor, one for each Color
     */
    protected void setupProfessors(){
        this.professors = new ArrayList<>();
        for (ColorS color : ColorS.values()) {
            professors.add(new Professor(color));
        }
    }

    /**
     * sets up the SchoolBoards, one for each Player
     */

    protected void setupSchoolboards(){
        this.boards = new ArrayList<>();
        for(int i = 0; i < this.numberOfPlayers; i++){
            Player player = this.players[i];
            SchoolBoard schoolBoard = new SchoolBoard(player);
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


    /**
     * sets up the AssistantCards, 10 for every Player
     */
    public void setupAssistantCards(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("assets/assistants.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray cards = (JSONArray) obj;
            System.out.println(cards);

            this.assistants = new ArrayList<>();

            for(Wizards wizard : Wizards.values()){
                cards.forEach( object ->{
                    JSONObject card =  (JSONObject) object;
                    this.assistants.add(new Assistant(((Long)card.get("value")).intValue(), ((Long)card.get("movement")).intValue(), wizard));
                });
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //TODO LEGGERE JSON E INSTANZIARE LE CARTE CON I VALORI LETTI
    }

    /**
     * sets the current Island of motherNature
     * @param motherNatureIsland
     */
    public void setMotherNature(Island motherNatureIsland){
        this.motherNature.getIsland().setMotherNature(false);
        motherNatureIsland.setMotherNature(true);
        this.motherNature.setIsland(motherNatureIsland);
    }

    /**
     * moves motherNature
     * @param movement
     */
    public void moveMotherNature(int movement){
        int id = this.motherNatureIsland().getId();
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
     * creates an IslandGroup from a list of islands: adds all the students, sets the tower, removes the old Islands and changes
     * all the Islands ids.
     * @param islands : islands to merge
     */
    public void newIslandGroup(List<Island> islands){
        int idMin = islands.stream().map(Island::getId).reduce(12, (id1, id2) -> id1 < id2 ? id1 : id2);
        int idMax = islands.stream().map(Island::getId).reduce(0, (id1, id2) -> id1 > id2 ? id1 : id2);
        Island islandGroup = new Island(idMin);
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
            if (i.getId() > idMax){
                i.changeId( islands.size()-1);
            }
        }
        this.islands.add(idMin, islandGroup);
    }


    /**
     * unifies Islands
     * @return
     */
    /*
    data un isola ritorna le isole che possono essere unite a lei
     */


    //TODO ricontrollare
    public List<Island> canIUnify(Island island){
        List<Island> islandsToUnify = new ArrayList<>();
        int i = this.islands.indexOf(island);
        islandsToUnify.add(this.islands.get(i));

        //scorro indietro
        int prevIndex = (i-1<0) ? (i-1) % this.islands.size()+this.islands.size() : (i-1) % this.islands.size();
        while(
                this.islands.get(i).getTower() !=null &&
                this.islands.get(prevIndex).getTower() !=null &&
                this.islands.get(i).getTower().getOwner().equals(this.islands.get(prevIndex).getTower().getOwner())
        ){
            islandsToUnify.add(this.islands.get(prevIndex));
            i = prevIndex;
            prevIndex = (i-1<0) ? (i-1) % this.islands.size()+this.islands.size() : (i-1) % this.islands.size();

        }
        //scorro avanti
        i = this.islands.indexOf(island);
        int nextIndex = ++i % this.islands.size();
        while(
                this.islands.get(i).getTower() !=null &&
                this.islands.get(nextIndex).getTower() !=null &&
                this.islands.get(i).getTower().getOwner().equals(this.islands.get(nextIndex).getTower().getOwner())
        ){
            islandsToUnify.add(this.islands.get(nextIndex));
            i = nextIndex;
            nextIndex = ++i % this.islands.size();
        }
        return islandsToUnify;
    }


    /**
     * finds the Island where motherNature is
     * @return
     */
    //TODO: testare il funzionamento
    public Island motherNatureIsland(){
        return this.islands.stream().filter(island -> island.isMotherNature())
                .findFirst().orElseThrow(() -> new RuntimeException("Mother Nature not found"));
    }

    //TODO: sarebbe meglio creare l'eccezione parità
    //todo: sistemare per 4 giocatori
    //il giocatore che ha costruito il maggior numero di torri è anche quello che ne ha il minor numero su towers
    public Player getPlayerWithMinTowers() throws ParityTowersException {
        int minTower = 9, numOfTower = 0;
        boolean parity = false;
        Player winner = null;
        for (Player p : this.players){
            numOfTower = p.getSchoolBoard().getTowers().getTowers().size();
            if (numOfTower < minTower){
                minTower = numOfTower;
                winner = p;
                parity = false;
            }
            else if (numOfTower == minTower){
                parity = true;
            }
        }
        if (parity){
            throw new ParityTowersException("There's a parity");
        }
        else{
            return winner;
        }
    }

    //todo: sarebbe meglio creare l'eccezione parità
    public Player getPlayerWithMaxProfessor(){
        int maxProf = 0, numOfProf = 0;
        boolean parity = false;
        Player winner = null;
        for (Player p : this.players){
            numOfProf = p.getSchoolBoard().getProfessorTable().getProfessors().size();
            if (numOfProf > maxProf){
                maxProf = numOfProf;
                winner = p;
                parity = false;
            }
            else if (numOfProf == maxProf){
                parity = true;
            }
        }
        if (parity){
            throw new RuntimeException("There's a parity");
        }
        else{
            return winner;
        }
    }


    public Player professorOwner(ColorS color){
        return this.professors.stream().filter(pr -> pr.getColor()==color)
                .findFirst().orElseThrow(() -> new RuntimeException("Professor owner not found")).getOwner();
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

    //todo: sarebbe meglio creare l'eccezione parità
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
                if (p.equals(pr.getOwner())) {
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

    public MotherNature getMotherNature() {
        return motherNature;
    }

}
