package it.polimi.ingsw.model;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;
import it.polimi.ingsw.model.cards.Assistant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    private TurnManager turnManager;

    private Bag bag;
    private int numberOfPlayers;
    private List<Student> students;

    private List<Island> islands;
    private MotherNature motherNature;
    private List<Cloud> clouds;
    private List<SchoolBoard> boards;
    private Player[] players;
    private Player currentPlayer;
    private List<Professor> professors;
    private ArrayList<Assistant> assistants;


    public Table(List<Player> players){
        this.turnManager = new TurnManager(players);
        this.bag = new Bag();
        this.currentPlayer = players.get(0);
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

    private void setupPlayers(List<Player>players){
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

    private void setupIslands(){
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

    private void setupClouds(){
        this.clouds = new ArrayList<>();
        // The number of clouds is the same as the number of players
        for(int i = 0; i < this.numberOfPlayers; i++){
            this.clouds.add(new Cloud());
        }
    }

    /**
     * creates all 130 students and shuffles them
     */
    private void setupStudents(){
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
    private void setupProfessors(){
        this.professors = new ArrayList<>();
        for (ColorS color : ColorS.values()) {
            professors.add(new Professor(color));
        }
    }

    /**
     * sets up the SchoolBoards, one for each Player
     */

    private void setupSchoolboards(){
        this.boards = new ArrayList<>();
        for(int i = 0; i < this.numberOfPlayers; i++){
            Player player = this.players[i];
            SchoolBoard schoolBoard = new SchoolBoard();
            this.players[i].setSchoolBoard(schoolBoard);
            for(int j=0; j<NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW; j++){
                schoolBoard.getEntrance().addStudent(this.bag.drawStudent());
            }
            //piazzo le torri solo se i giocatori sono 3 o meno oppure 4 ma in questo caso solo ai primi 2 giocatori
            if(this.numberOfPlayers <= 3 || (this.numberOfPlayers == 4 && i < 2)){
                for(int j=0;j<NUM_OF_TOWER_AT_SETUP;j++){
                    schoolBoard.getTowerBoard().addTower(new Tower(player.getTowerColor(),player));
                }
            }
            this.boards.add(schoolBoard);
        }
    }


    /**
     * sets up the AssistantCards, 10 for every Player
     */
    private void setupAssistantCards(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("assets/assistants.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;

            this.assistants = new ArrayList<>();
            for(Wizards wizard : Wizards.values()){
                cards.forEach( object ->{
                    JSONObject card =  (JSONObject) object;
                    this.assistants.add(new Assistant(((Long)card.get("value")).intValue(), ((Long)card.get("movement")).intValue(), wizard));
                });
            }
            //FIXME: in teoria è il player a scegliere un mago
            for (int i = 0; i < this.numberOfPlayers; i++){
                Wizards wizard = Wizards.values()[i];
                List<Assistant> assistants = this.assistants.stream().filter(a -> a.getWizard()==wizard).collect(Collectors.toList());
                this.players[i].getAssistantDeck().addAll(assistants);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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


    //potrebbe essere metodo private
    /**
     * creates an IslandGroup from a list of islands: adds all the students, sets the tower, removes the old Islands and changes
     * all the Islands IDs.
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


    //potrebbe essere metodo private
    //TODO metodo ricorsivo che ritorna le isole da unire
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
        int nextIndex = (i+1) % this.islands.size();
        while(
                this.islands.get(i).getTower() !=null &&
                this.islands.get(nextIndex).getTower() !=null &&
                this.islands.get(i).getTower().getOwner().equals(this.islands.get(nextIndex).getTower().getOwner())
        ){
            islandsToUnify.add(this.islands.get(nextIndex));
            i = nextIndex;
            nextIndex = (i+1) % this.islands.size();
        }
        return islandsToUnify;
    }


    /**
     * finds the Island where motherNature is
     * @return
     */
    public Island motherNatureIsland(){
        return this.islands.stream().filter(island -> island.isMotherNature())
                .findFirst().orElseThrow(() -> new RuntimeException("Mother Nature not found"));
    }


    //potrebbe essere metodo private
    //il giocatore che ha costruito il maggior numero di torri è anche quello che ne ha il minor numero su towers
    public Player getPlayerWithMinTowers() throws ParityException {
        int minTower = 9, numOfTower = 0;
        boolean parity = false;
        Player winner = null;
        for (int i = 0; i < this.numberOfPlayers; i++){
            if (this.numberOfPlayers < 4 || (this.numberOfPlayers == 4 && i < 2)) {
                numOfTower = this.players[i].getSchoolBoard().getTowerBoard().getTowers().size();
                if (numOfTower < minTower) {
                    minTower = numOfTower;
                    winner = this.players[i];
                    parity = false;
                } else if (numOfTower == minTower) {
                    parity = true;
                }
            }
        }
        if (parity){
            throw new ParityException("there's a parity");
        }
        else{
            return winner;
        }
    }

    //potrebbe essere metodo private
    //in teoria non si può mai verificare il caso in cui ci sia una parità di professori tra giocatori
    public Player getPlayerWithMaxProfessor(){
        int maxProf = 0, numOfProf = 0;
        Player winner = null;
        for (Player p : this.players){
            numOfProf = p.getSchoolBoard().getProfessorTable().getProfessors().size();
            if (numOfProf > maxProf){
                maxProf = numOfProf;
                winner = p;
            }
        }
        return winner;
    }


    public Player getProfessorOwner(ColorS color){
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

    //potrebbe essere metodo private
    /**
     * Calculate the player who has the greatest influence on the island, throws ParityException if there's
     * a parity of the max influence
     * @param island
     * @return player who has the highest influence on the island
     */
    public Player getSupremacy(Island island) throws ParityException {
        Player newIslandKing = null;
        int[] playerInfluence = new int[this.numberOfPlayers];
        int maxInfluence = 0;
        boolean parity = false;

        for (int i = 0; i < this.numberOfPlayers; i++) {
            playerInfluence[i] = getInfluence(island, this.players[i]);
        }

        //assumo che il player 0 sia il leader della squadra 1 (pari)
        if (this.numberOfPlayers == 4) {
            playerInfluence[0] += playerInfluence[2];
            playerInfluence[1] += playerInfluence[3];
            playerInfluence[2] = 0;
            playerInfluence[3] = 0;
        }

        for (int i = 0; i < this.numberOfPlayers; i++) {
            if (playerInfluence[i] > maxInfluence) {
                maxInfluence = playerInfluence[i];
                newIslandKing = this.players[i];
                parity = false;
            } else if (playerInfluence[i] == maxInfluence) {
                parity = true;
            }
        }

        if (parity){
            throw new ParityException("there's a parity");
        }
        else {
            return newIslandKing;
        }
    }

    protected int getInfluence(Island island, Player player){
        int influence = 0;
        for (Professor pr : this.professors){
            if (pr.getOwner()==player){
                influence += island.numStudent(pr.getColor());
            }
        }
        if (island.getTower() != null && player.equals(island.getTower().getOwner())){
            influence += island.getNumOfTowers();
        }
        return influence;
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
     * Process an island: calculate the king, substitute the towers and create new island group
     * @param island to process
     */
    public void processIsland(Island island){
        try{
            Player newIslandKing = this.getSupremacy(island);
            if (island.getTower() == null || !newIslandKing.equals(island.getTower().getOwner())){
                if (island.getTower() != null){
                    island.getTower().getOwner().getSchoolBoard().getTowerBoard().addTower(island.getTower());
                }
                island.setTower(newIslandKing.getSchoolBoard().getTowerBoard().removeLastTower());
                if (newIslandKing.getSchoolBoard().getTowerBoard().getTowers().size() == 0){
                    throw new EndGameException("Last tower placed");
                }
            }
            if (this.canIUnify(island).size() > 1) {
                this.newIslandGroup(this.canIUnify(island));
            }
            if (this.islands.size() <= 3){
                throw new EndGameException("3 islands remained");
            }
        } catch(ParityException e){
            //do nothing
        } catch (EndGameException e) {
            this.endGame();
        }
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

    //metodo chiamato ogni volta che si sposta uno studente sulla propria sala
    public void setProfessorOwner(ColorS color, Player currentPlayer){
        int currentPlayerProf = getNumberOfStudents(color, currentPlayer);
        Player oldOwner = this.getProfessorOwner(color);
        if (oldOwner == null || currentPlayerProf > oldOwner.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(color)){
            this.getProfessor(color).setOwner(currentPlayer);
            this.currentPlayer.getSchoolBoard().getProfessorTable().addProfessor(this.getProfessor(color));
            //TODO: implementare aggiunta e rimozione dei professori dal professor table
        }
    }

    protected int getNumberOfStudents(ColorS color, Player currentPlayer){
        return currentPlayer.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(color);
    }

    public void playAssistant(Assistant card) throws AssistantNotPlayableException, AssistantNotFoundException {
        int value = card.getValue();
        List<Assistant> playable = new ArrayList<>(getCurrentPlayer().getAssistantDeck());
        for(Player player : players) {
            if (!player.getDiscardPile().isEmpty()) {
                int valueToRemove = player.getDiscardPile().peek().getValue();
                playable.remove(getCurrentPlayer().getAssistant(valueToRemove));
            }
        }
        if(playable.contains(card) || playable.size() == 0){
            getCurrentPlayer().addToDiscardPile(card);
            turnManager.orderPlayer(getCurrentPlayer());
        }
        else {
            throw new AssistantNotPlayableException("Not playable assistant");
        }
    }

    public void nextPlayer(){
        setCurrentPlayer(turnManager.nextPlayer());
    }

    public void validIsland(int idIsland) throws IslandNotValidException {
        if(idIsland >= islands.size() || idIsland < 0)
            throw new IslandNotValidException("Not valid Island");
    }

    public void validCloud(int cloudIndex) throws CloudNotValidException {
        if(cloudIndex >= clouds.size() ||
                cloudIndex < 0 ||
                clouds.get(cloudIndex).getStudents().isEmpty()
        ){
            throw new CloudNotValidException("Not valid cloud");
        }
    }

    public void endGame() {
        Player winner = null;
        try {
            winner = this.getPlayerWithMinTowers();
        }catch (ParityException e){
            winner = this.getPlayerWithMaxProfessor();
        }
        //TODO
    }

}