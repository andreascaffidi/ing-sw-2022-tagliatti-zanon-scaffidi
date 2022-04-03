package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.ChooseCloudMessage;
import it.polimi.ingsw.network.messages.MoveStudentMessage;
import it.polimi.ingsw.network.messages.PlayAssistantMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private Table table;

    public Controller(List<Player> players){
        this.table = new Table(players);
        int randomPlayerIndex = new Random().nextInt(players.size());
        this.table.setCurrentPlayer(players.get(randomPlayerIndex));
        //this.roundPhase = RoundPhases.PLANNING;
    }


    /*  INIZIO ROUND
        FASE PIANIFICAZIONE
            -   per ogni giocatore si pascano 3 studenti e si piazzano su una nuvola
            -   ogni giocatore scehlie carta assistente
    */

    public void update(Message message){
        message.getTypeOfMessage().execute(this,message.getUsername());
    }


        public void playAssistant(PlayAssistantMessage message, String username){
            if(this.table.getRoundPhase() != RoundPhases.PLANNING){
                throw new RuntimeException("");
            }
            try{
                checkPlayer(username);
                table.playAssistant(table.getCurrentPlayer().getAssistant(message.getValue()));
            }catch (WrongPlayerException e){
                //TODO
            }catch (AssistantNotFoundException e){
                //TODO
            }catch (AssistantNotPlayableException e){
                //TODO
            }

            table.nextPlayer();
        }

        private void checkPlayer(String username){
            if(!username.equals(table.getCurrentPlayer().getUsername()))
                throw new WrongPlayerException("Ma ce la fai?");
        }



        /*
            studente , from , to
            colore, da[entrance], a[cloud,island]
        */

        //FASE 1 - SPOSTARE 3 STUDENTI
        public void moveStudent(String students_, String to_){
            //this.turnPhase = TurnPhases.MOVE_STUDENTS;

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

        public void moveStudentToIsland(MoveStudentMessage message, String username){
            try{
                checkPlayer(username);
                int idIsland = message.getIdIsland()-1;
                int studentIndex = message.getStudentIndex()-1;
                table.validIsland(idIsland);
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(studentIndex);
                Student student = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(studentIndex);
                table.getIsland(idIsland).addStudent(student);
            }catch (WrongPlayerException e){
                //TODO
            } catch (IslandNotValidException e) {
                //TODO
            } catch (StudentIndexOutOfBoundsException e) {
                //TODO
            }
        }

    public void moveStudentToDining(MoveStudentMessage message, String username){
        try{
            checkPlayer(username);
            int studentIndex = message.getStudentIndex()-1;
            table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(studentIndex);
            Student student = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(studentIndex);
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(student);
        }catch (WrongPlayerException e){
            //TODO
        } catch (StudentIndexOutOfBoundsException e) {
            //TODO
        }
    }


        //FASE 2
        public void moveMotherNature(int movements){
           // this.turnPhase = TurnPhases.MOVE_MOTHER_NATURE;
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

            }catch(LastTowerVictoryException e){
                table.endGame();

            }catch(Exception e){
                //todo:
            }
        }



    //FASE 3
    public void chooseCloud(ChooseCloudMessage message, String username){
        try {
            checkPlayer(username);
            int idCloud = message.getId()-1;
            table.validCloud(idCloud);
            Cloud cloud = table.getClouds().get(idCloud);
            for(Student student : cloud.takeAllStudents()) {
                table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(student);
            }
        }catch (WrongPlayerException e){
            //TODO
        }catch (CloudNotValidException e){
            //TODO
        }
        table.nextPlayer();
    }


















}
