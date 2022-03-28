package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.Wizard;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;

import java.util.*;

public class Controller {

    private Table table;

    private RoundPhases roundPhase;
    private TurnPhases turnPhase;

    private Map<Player, Integer[]> playerParams;


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
            this.playerParams = new HashMap<>();

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

            Assistant card = new Assistant(0,0, Wizard.WIZARD_1);
            Integer[] params = new Integer[]{card.getValue(),card.getMotherNatureMovements()};
            this.playerParams.put(player, params);
        }

        public void setupAction(){
            this.roundPhase = RoundPhases.ACTION;

            //this.turnPhase = TurnPhases.WAITING_FOR_MOVE_STUDENTS;
        }



        private void nextPlayer(){
            //table.setCurrentPlayer();
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
        public void chooseCloud(){

        }


        private void endRound(){

        }














}
