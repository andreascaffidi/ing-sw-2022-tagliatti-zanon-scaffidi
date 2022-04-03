package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.exceptions.LastTowerVictoryException;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.Wizards;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;

import java.util.*;

public class Controller {

    private Table table;

    private RoundPhases roundPhase;
    private TurnPhases turnPhase;

    private Map<Player, Integer> playerValues;
    private Object[][] orderedPlayers;


    public Controller(Table table){
        this.table = table;
        int randomPlayerIndex = new Random().nextInt(table.getPlayers().length);
        this.table.setCurrentPlayer(table.getPlayers()[randomPlayerIndex]);
        this.roundPhase = RoundPhases.PLANNING;
        this.orderedPlayers = new Object[table.getPlayers().length][table.getPlayers().length];
    }


    /*  INIZIO ROUND
        FASE PIANIFICAZIONE
            -   per ogni giocatore si pascano 3 studenti e si piazzano su una nuvola
            -   ogni giocatore scehlie carta assistente
    */



        public void setupPlanning(){
            this.roundPhase = RoundPhases.PLANNING;
            this.playerValues = new TreeMap<>();

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
            player.addToDiscardPile(card);
            Integer[] params = new Integer[]{card.getValue(),card.getMotherNatureMovements()};


            this.playerValues.put(player, card.getValue());


            this.nextPlayer();
        }

        public void setupAction(){
            this.roundPhase = RoundPhases.ACTION;

            //this.turnPhase = TurnPhases.WAITING_FOR_MOVE_STUDENTS;
        }


        //FIXME: da gestire il caso 2 giocatori giochino 2 carte con lo stesso valore
        private void nextPlayer(){
            int currentValue = table.getCurrentPlayer().getDiscardPile().get(0).getValue();
            int minValue = 11;
            Player nextPlayer = null;
            for(Player player : table.getPlayers()){
                if(player.getDiscardPile().get(0).getValue() < minValue && player.getDiscardPile().get(0).getValue() > currentValue) {
                    minValue = player.getDiscardPile().get(0).getValue();
                    nextPlayer = player;
                }
            }
            if(nextPlayer == null) this.endRound();
            table.setCurrentPlayer(nextPlayer);
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
                table.setProfessorOwner(students.get(i).getColor(),table.getCurrentPlayer());
                //in caso è isola dobbiamo capire quale isola è
            }
        }


        //FASE 2
        public void moveMotherNature(int movements){
            this.turnPhase = TurnPhases.MOVE_MOTHER_NATURE;

            this.table.moveMotherNature(movements);

            Tower oldTower = this.table.motherNatureIsland().getTower();
            Player oldIslandKing = oldTower.getOwner();

            try {
                Player newIslandKing = this.table.getSupremacy(this.table.motherNatureIsland());
                if(!newIslandKing.equals(oldIslandKing)){
                    if(oldIslandKing != null){
                        oldIslandKing.getSchoolBoard().getTowers().addTower(oldTower);
                    }
                    Tower newTower = newIslandKing.getSchoolBoard().getTowers().removeLastTower();


                    if(table.getCurrentPlayer().getSchoolBoard().getTowers().getTowers().size() == 0){
                        throw new LastTowerVictoryException("Last tower placed");
                    }

                    this.table.motherNatureIsland().setTower(newTower);
                }
            }catch (LastTowerVictoryException e){
                this.endGame();
            }
            catch (Exception e){
                //todo:
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
            if(roundPhase == RoundPhases.ACTION){
                this.setupPlanning();
            }
            if(roundPhase == RoundPhases.PLANNING){
                roundPhase = RoundPhases.ACTION;
                turnPhase = TurnPhases.MOVE_STUDENTS; //WAITING_FOR_MOVE_STUDENTS_MESSAGE
            }

        }

        //FIXME: implementare per bene
        private void endGame() {
            Player winner = table.getCurrentPlayer();
        }














}
