package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    private Table table;

    private RoundPhases roundPhase;
    private TurnPhases turnPhase;

    private Map<Player, Integer> playerValues;


    public Controller(List<Player> players){
        this.table = new Table(players);
        int randomPlayerIndex = new Random().nextInt(players.size());
        this.table.setCurrentPlayer(players.get(randomPlayerIndex));
        this.roundPhase = RoundPhases.PLANNING;
    }


    /*
        INIZIO ROUND
            FASE PIANIFICAZIONE
                -   per ogni giocatore si pascano 3 studenti e si piazzano su una nuvola
                -   ogni giocatore scehlie carta assistente
    */

        public void setupPlanning(){
            this.roundPhase = RoundPhases.PLANNING;
            this.playerValues = new HashMap<>();

            for(int i = 0; i<table.getClouds().size();i++){
                table.addStudentsToCloud(table.getClouds().get(i));
            }

            //scegliere carta assistente
        }


        public void playAssistant(int i, Player player){
            if(this.roundPhase != RoundPhases.PLANNING){
                throw new RuntimeException("");
            }
           //todo: implementare

            Assistant card = new Assistant(0,0, Wizards.WIZARD_1);
            Integer[] params = new Integer[]{card.getValue(),card.getMotherNatureMovements()};
            this.playerValues.put(player, card.getValue());
        }

        public void setupAction(){
            this.roundPhase = RoundPhases.ACTION;

            //this.turnPhase = TurnPhases.WAITING_FOR_MOVE_STUDENTS;
        }

        private void nextPlayer(){
            Player currentPlayer = table.getCurrentPlayer();

            Integer currentValue = playerValues.get(currentPlayer);

            List<Integer> mapValues = new ArrayList<>(playerValues.values());
            List<Integer> values =  new ArrayList<>();

            for(Integer currValue : mapValues){
                values.add(currValue);
            }

            values = values.stream().filter(el -> el > currentValue).collect(Collectors.toList());
            Collections.sort(values);
            Integer nextValue = values.get(0);

            for(Player player: playerValues.keySet()){
                if(playerValues.get(player) == nextValue){
                    table.setCurrentPlayer(player);
                }
            }

        }

        /*
            studente , from , to
            colore, da[entrance], a[cloud,island]
        */

        //FASE 1 - SPOSTARE 3 STUDENTI
        public void moveStudent(String students_, String to_){
            this.turnPhase = TurnPhases.MOVE_STUDENTS;

            List<Student> students = new ArrayList<>();
            //FIXME implementare in base al numero di giocatori
            if(students.size()!=3) throw new RuntimeException("Levateje er vino");
            for(int i=0; i < students.size();i++){
                table.getCurrentPlayer().getSchoolBoard().getEntrance().removeStudent(students.get(i));
                //da fare if e decidere destinazione
                table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(students.get(i));
                //in caso è isola dobbiamo capire quale isola è
            }
        }

        private void replaceProfessors(){

        }


        //FASE 2
        public void moveMotherNature(int movements){
            this.turnPhase = TurnPhases.MOVE_MOTHER_NATURE;

            this.table.moveMotherNature(movements);

            Tower oldTower = this.table.motherNatureIsland().getTower();
            Player oldIslandKing = oldTower.getOwner();

            Player newIslandKing = this.table.getSupremacy(this.table.motherNatureIsland());
            if(!newIslandKing.equals(oldIslandKing)){
                if(oldIslandKing != null){
                    oldIslandKing.getSchoolBoard().getTowers().addTower(oldTower);
                }
                Tower newTower = newIslandKing.getSchoolBoard().getTowers().removeLastTower();
                this.table.motherNatureIsland().setTower(newTower);
            }
        }

        //FASE 3
        public void chooseCloud(int cloudIndex){
            this.turnPhase = TurnPhases.GET_STUDENTS_FROM_CLOUD;


            Cloud cloud = table.getClouds().get(cloudIndex);
            for(Student student : cloud.takeAllStudents()){
                table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(student);
            }

            this.nextPlayer();
        }


        private void endRound(){

        }














}
