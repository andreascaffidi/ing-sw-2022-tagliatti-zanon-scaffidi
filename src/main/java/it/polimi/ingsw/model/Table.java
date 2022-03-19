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

    //todo: attributi aggiunti dopo
    private List<Professor> professors;
    private List<Student> students;

    public Table(List<Player> players){
        this.motherNature = new MotherNature();
        this.bag = new Bag();
        this.numberOfPlayers = players.size();
        this.setupStudents();


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
                studentsToAdd.add(this.bag.drawStudent());
            }
            this.boards.get(i).getEntance().addStudent(studentsToAdd);
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
        for(int i = 0; i < this.professors.size(); i++) {
            if(this.professors.get(i).getColor() == color){
                return this.professors.get(i);
            }
        }

        //FIXME: non ritornare null
        return null;
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
