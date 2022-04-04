package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.ChooseCloudMessage;
import it.polimi.ingsw.network.messages.MoveMotherNatureMessage;
import it.polimi.ingsw.network.messages.MoveStudentMessage;
import it.polimi.ingsw.network.messages.PlayAssistantMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private Table table;

    public Controller(List<Player> players){
        this.table = new Table(players);
    }



    public void update(Message message){
        message.getTypeOfMessage().execute(this,message.getUsername());
    }


    public void playAssistant(PlayAssistantMessage message, String username){
        try{
            checkPlayer(username);
            table.playAssistant(table.getCurrentPlayer().getAssistant(message.getValue()));
            table.nextPlayer();
        }catch (WrongPlayerException e){
            //TODO
        }catch (AssistantNotFoundException e){
            //TODO
        }catch (AssistantNotPlayableException e){
            //TODO
        }
    }

    private void checkPlayer(String username){
        if(!username.equals(table.getCurrentPlayer().getUsername()))
            throw new WrongPlayerException("Ma ce la fai?");
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
            table.setProfessorOwner(student.getColor(), table.getCurrentPlayer());
        }catch (WrongPlayerException e){
            //TODO
        } catch (StudentIndexOutOfBoundsException e) {
            //TODO
        }
    }


    public void moveMotherNature(MoveMotherNatureMessage message, String username){
        try{
            checkPlayer(username);
            int movement = message.getMovements();
            table.getCurrentPlayer().validMovement(movement);
            table.moveMotherNature(movement);
            table.processIsland(table.motherNatureIsland());
        } catch (WrongPlayerException e){
            //TODO
        } catch (MovementNotValidException e){
            //TODO
        }
    }



    public void chooseCloud(ChooseCloudMessage message, String username){
        try {
            checkPlayer(username);
            int idCloud = message.getId()-1;
            table.validCloud(idCloud);
            Cloud cloud = table.getClouds().get(idCloud);
            for(Student student : cloud.takeAllStudents()) {
                table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(student);
            }
            table.nextPlayer();
        }catch (WrongPlayerException e){
            //TODO
        }catch (CloudNotValidException e){
            //TODO
        }
    }

}
