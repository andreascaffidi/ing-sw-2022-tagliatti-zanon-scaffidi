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
import it.polimi.ingsw.network.client.reducedModel.ReducedBoard;
import it.polimi.ingsw.network.client.reducedModel.ReducedCloud;
import it.polimi.ingsw.network.client.reducedModel.ReducedIsland;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.ReducedModelMessage;
import it.polimi.ingsw.utils.Observable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Table Class for match control, represents the entire model
 */
public class Table extends Observable<ResponseMessage> {

    public static final int NUM_OF_ISLANDS = 12;
    public static final int NUM_OF_STUDENTS_PER_COLOR = 26;
    public static final int NUM_OF_STUDENTS_SETUP = 2;

    //parameters that depend on number of players
    public int NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW;
    public int NUM_OF_TOWER_AT_SETUP;
    public int NUM_OF_STUDENTS_TO_PLACE_ON_CLOUD;

    private final TurnManager turnManager;

    private final Bag bag;
    private final int numberOfPlayers;
    private List<Student> students;

    private List<Island> islands;
    private MotherNature motherNature;
    private List<Cloud> clouds;
    private Player[] players;
    private Player currentPlayer;
    private List<Professor> professors;

    /**
     * builds the table
     * @param players game players
     */
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

        //a bit of setup point 8
        this.setupPlayers(players);

        //setup points 1-2-3-4
        this.setupIslands();

        //setup point 5
        this.setupClouds();

        //setup point 6
        this.setupProfessors();

        //setup point 9
        this.setupAssistantCards();

        //setup points 7 - 10
        this.setupSchoolBoards();
    }

    /**
     * sets up the players depending on the number of players, for 4 players matches: team 1 is placed on even indexes
     * of the array and team 2 is placed on odd indexes of the array
     * @param players players to set up
     */
    private void setupPlayers(List<Player>players){
        this.players = new Player[numberOfPlayers];

        if (this.numberOfPlayers == 4){
            List<Player> team1 = players.stream().filter(p -> p.getTagTeam() == 1).collect(Collectors.toList());
            List<Player> team2 = players.stream().filter(p -> p.getTagTeam() == 2).collect(Collectors.toList());
            players.clear();
            players.add(team1.get(0));
            players.add(team2.get(0));
            players.add(team1.get(1));
            players.add(team2.get(1));
        }

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
     * sets up the 12 islands and mother nature
     */

    private void setupIslands(){
        this.islands = new ArrayList<>();
        for (int i = 0; i < NUM_OF_ISLANDS; i++){
            this.islands.add(new Island(i));
        }

        // add 2 students per color to the bag and remove them from the total: setup point 3
        for (ColorS color : ColorS.values()) {
            for (int i=0;i<NUM_OF_STUDENTS_SETUP; i++){
                Student studentToAdd = this.students.stream().filter(s -> s.getColor()==color).findFirst().orElseThrow(()->new RuntimeException("Student not found"));
                this.students.remove(studentToAdd);
                this.bag.addStudent(studentToAdd);
            }
        }

        //take a random island
        int motherNatureIslandIndex = new Random().nextInt(NUM_OF_ISLANDS);
        Island motherNatureIsland = islands.get(motherNatureIslandIndex);

        //create mother nature
        this.motherNature = new MotherNature(motherNatureIsland);
        this.setMotherNature(motherNatureIsland);

        int oppositeIslandIndex = (motherNatureIslandIndex + (NUM_OF_ISLANDS/2)) % NUM_OF_ISLANDS;

        for(int i = 0; i<this.islands.size(); i++){
            if(i != motherNatureIslandIndex && i != oppositeIslandIndex) {
                this.islands.get(i).addStudent(bag.drawStudent());
            }
        }

        // add all students remained to the bag
        this.bag.addStudents(students);
    }

    /**
     * sets up the clouds depending on the number of players
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
        this.students = new ArrayList<>();
        for (ColorS color : ColorS.values()) {
            for (int i=0;i<NUM_OF_STUDENTS_PER_COLOR; i++){
                this.students.add(new Student(color));
            }
        }
        Collections.shuffle(this.students);
    }

    /**
     * creates all professors, one for each color
     */
    private void setupProfessors(){
        this.professors = new ArrayList<>();
        for (ColorS color : ColorS.values()) {
            professors.add(new Professor(color));
        }
    }

    /**
     * sets up the school boards, one for each player
     */

    private void setupSchoolBoards(){
        for(int i = 0; i < this.numberOfPlayers; i++){
            Player player = this.players[i];
            SchoolBoard schoolBoard = new SchoolBoard();
            this.players[i].setSchoolBoard(schoolBoard);
            for(int j=0; j<NUM_OF_STUDENTS_PER_ENTRANCE_TO_DRAW; j++){
                schoolBoard.getEntrance().addStudent(this.bag.drawStudent());
            }
            //if there are <4 players place towers on all player's tower boards;
            //else place only on the first two players' boards
            if(this.numberOfPlayers <= 3 || (this.numberOfPlayers == 4 && i < 2)){
                for(int j=0;j<NUM_OF_TOWER_AT_SETUP;j++){
                    schoolBoard.getTowerBoard().addTower(new Tower(player.getTowerColor(),player));
                }
            }
        }
    }


    /**
     * sets up the assistant cards, 10 for every player
     */
    private void setupAssistantCards(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("assets/assistants.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;

            List<Assistant> allAssistants = new ArrayList<>();
            for(Wizards wizard : Wizards.values()){
                for(Object object : cards){
                    JSONObject card =  (JSONObject) object;
                    allAssistants.add(new Assistant(((Long)card.get("value")).intValue(), ((Long)card.get("movement")).intValue(), wizard));
                }
            }
            //FIXME: technically player choose the wizard :(
            for (int i = 0; i < this.numberOfPlayers; i++){
                Wizards wizard = Wizards.values()[i];
                List<Assistant> assistants = allAssistants.stream().filter(a -> a.getWizard()==wizard).collect(Collectors.toList());
                this.players[i].getAssistantDeck().addAll(assistants);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the current island of mother nature
     * @param motherNatureIsland island to set
     */
    public void setMotherNature(Island motherNatureIsland){
        this.motherNature.getIsland().setMotherNature(false);
        motherNatureIsland.setMotherNature(true);
        this.motherNature.setIsland(motherNatureIsland);
    }

    /**
     * moves mother nature
     * @param movement mother nature movements
     */
    public void moveMotherNature(int movement){
        int id = this.motherNatureIsland().getId();
        id = (id + movement) % this.islands.size();
        this.setMotherNature(this.getIsland(id));
    }


    /**
     * gets the professor with a given color
     * @param color professor color
     * @return professor with the given color
     */
    public Professor getProfessor(ColorS color){
        return this.professors.stream().filter(professor->professor.getColor() == color)
                .findAny().orElseThrow(()->new RuntimeException("Professor not found"));
    }


    /**
     * gets the current player
     * @return current player
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * sets the current player
     * @param currentPlayer player to set
     */
    public void setCurrentPlayer(Player currentPlayer){
        this.currentPlayer = currentPlayer;
    }


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
     * checks if there are islands that can be unified to the given island
     * @return possible islands to unify
     */

    public List<Island> canIUnify(Island island){
        List<Island> islandsToUnify = new ArrayList<>();
        int i = this.islands.indexOf(island);
        islandsToUnify.add(this.islands.get(i));
        //search back
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
        //search forward
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
     * gets the island where there's mother nature
     * @return mother nature island
     */
    public Island motherNatureIsland(){
        return this.islands.stream().filter(Island::isMotherNature)
                .findFirst().orElseThrow(() -> new RuntimeException("Mother Nature not found"));
    }


    /**
     * gets the player who has the maximum towers built, so the player who has the minimum towers on his tower board
     * @return player who has maximum towers built
     * @throws ParityException if there are two players with the same number of towers built
     */
    private Player getPlayerWithMinTowers() throws ParityException {
        int minTower = 9, numOfTower;
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

    /**
     * gets player who has the maximum professors on his professor board
     * @return player who has the maximum professors
     */
    private Player getPlayerWithMaxProfessor(){
        int maxProf = 0, numOfProf;
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


    /**
     * gets the player who has the professor with a given color
     * @param color professor's color
     * @return player who has the professor
     */
    public Player getProfessorOwner(ColorS color){
        return this.professors.stream().filter(pr -> pr.getColor()==color)
                .findFirst().orElseThrow(() -> new RuntimeException("Professor owner not found")).getOwner();
    }

    /**
     * gets the bag on the table
     * @return bag
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * gets an island by its id
     * @param id island's id
     * @return island with the given id
     */
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
     * calculate the player who has the greatest influence on the given island
     * @param island island on which calculate the supremacy
     * @return player with the greatest influence on the island
     * @throws ParityException if there's a parity of the max influence
     */
    public Player getSupremacy(Island island) throws ParityException {
        Player newIslandKing = null;
        int[] playerInfluence = new int[this.numberOfPlayers];
        int maxInfluence = 0;
        boolean parity = false;

        for (int i = 0; i < this.numberOfPlayers; i++) {
            playerInfluence[i] = getInfluence(island, this.players[i]);
        }

        //assume that player 0 is the leader of team 1 (even team) and player 1 is the leader of team 2 (odd team)
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

    /**
     * gets the given player's influence on a given island
     * @param island island on which calculate player's influence
     * @param player influence's owner
     * @return calculated influence
     */
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

    /**
     * gets the islands on the table
     * @return islands
     */
    public  List<Island> getIslands() {
        return this.islands;
    }

    /**
     * gets the game players
     * @return game players
     */
    public Player[] getPlayers() {
        Player[] playersReturn = new Player[this.numberOfPlayers];
        System.arraycopy(this.players, 0, playersReturn, 0, this.numberOfPlayers);
        return playersReturn;
    }

    /**
     * processes an island: calculates the king, substitutes the towers and creates a new island group
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
     *  places on a selected cloud the correct number of students depending on the number of players
     * @param cloud cloud on which place students
     */
    public void addStudentsToCloud(Cloud cloud){
        for(int i=0; i < NUM_OF_STUDENTS_TO_PLACE_ON_CLOUD; i++){
            cloud.addStudent(this.getBag().drawStudent());
        }
    }

    /**
     * gets the clouds on the table
     * @return clouds
     */
    public List<Cloud> getClouds() {
        return clouds;
    }

    //FIXME: used only in tests
    /**
     * gets mother nature
     * @return mother nature
     */
    public MotherNature getMotherNature() {
        return motherNature;
    }

    /**
     * checks if a player is the new owner of the professor with a given color, in that case sets the new professor owner
     * @param color professor's color
     * @param currentPlayer player to check and possibly set
     */
    //this method is called every time a student is placed on player's dining room
    public void setProfessorOwner(ColorS color, Player currentPlayer){
        int currentPlayerProf = getNumberOfStudents(color, currentPlayer);
        Player oldOwner = this.getProfessorOwner(color);
        if (oldOwner == null){
            this.getProfessor(color).setOwner(currentPlayer);
            this.currentPlayer.getSchoolBoard().getProfessorTable().addProfessor(this.getProfessor(color));
        }
        else if (currentPlayerProf > oldOwner.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(color)){
            this.getProfessor(color).setOwner(currentPlayer);
            this.currentPlayer.getSchoolBoard().getProfessorTable().addProfessor(this.getProfessor(color));
            oldOwner.getSchoolBoard().getProfessorTable().removeProfessor(this.getProfessor(color));
        }
    }

    /**
     * counts the number of students with a specified color for a player
     * @param color students' color
     * @param currentPlayer students' owner
     * @return number of students with the specified color
     */
    protected int getNumberOfStudents(ColorS color, Player currentPlayer){
        return currentPlayer.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(color);
    }

    /**
     * plays the assistant card, puts it on top of the discard pile and calculates the order of action phase turn
     * @param card assistant card to play
     * @throws AssistantNotPlayableException if assistant card isn't playable (i.e. another one has played it)
     * @throws AssistantNotFoundException if there isn't the assistant card
     */
    public void playAssistant(Assistant card) throws AssistantNotPlayableException, AssistantNotFoundException {
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

    /**
     * changes the current player of the game
     */
    public void nextPlayer(){
        setCurrentPlayer(turnManager.nextPlayer());
    }

    /**
     * checks island index's validity
     * @param idIsland island's index to check
     * @throws IslandNotValidException if island's index is invalid
     */
    public void validIsland(int idIsland) throws IslandNotValidException {
        if(idIsland >= islands.size() || idIsland < 0)
            throw new IslandNotValidException("Not valid Island");
    }

    /**
     * checks cloud index's validity
     * @param cloudIndex cloud's index to check
     * @throws CloudNotValidException if cloud's index is invalid
     */
    public void validCloud(int cloudIndex) throws CloudNotValidException {
        if(cloudIndex >= clouds.size() ||
                cloudIndex < 0 ||
                clouds.get(cloudIndex).getStudents().isEmpty()
        ){
            throw new CloudNotValidException("Not valid cloud");
        }
    }

    /**
     * ends the game and choose the winner
     * @return the winner of the game
     */
    public Player endGame() {
        Player winner;
        try {
            winner = this.getPlayerWithMinTowers();
        }catch (ParityException e){
            winner = this.getPlayerWithMaxProfessor();
        }
        return winner;
        //TODO: we are in the endgame now!
    }

    //FIXME: è una prova
    public ReducedModel createReducedModel(){

        List<ReducedBoard> reducedBoards = new ArrayList<>();
        List<ReducedIsland> reducedIslands = new ArrayList<>();
        List<ReducedCloud> reducedClouds = new ArrayList<>();

        for(Player p : this.players)
        {
            reducedBoards.add(p.reduceBoard());
        }

        for(Island i : this.islands)
        {
            reducedIslands.add(i.reduceIsland());
        }

        for(Cloud c : this.clouds)
        {
            reducedClouds.add(new ReducedCloud(this.clouds.indexOf(c),
                    c.getStudents().stream().map(Student::getColor).collect(Collectors.toList())));
        }

        return new ReducedModel(reducedIslands, reducedClouds, this.currentPlayer.getUsername(), reducedBoards);
    }

    public void startGame(){
        notify(new ReducedModelMessage(ClientState.PLAY_ASSISTANT,createReducedModel()));
    }

}