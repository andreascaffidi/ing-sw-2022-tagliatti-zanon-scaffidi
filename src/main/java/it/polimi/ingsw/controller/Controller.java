package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.ControllerExecute;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.requestMessage.ChooseCloudMessage;
import it.polimi.ingsw.network.requestMessage.MoveMotherNatureMessage;
import it.polimi.ingsw.network.requestMessage.MoveStudentMessage;
import it.polimi.ingsw.network.requestMessage.PlayAssistantMessage;

public class Controller {

    private Table table;

    public Controller(Table table){
        this.table = table;
    }


    //TODO: proibire al metodo di eseguire messaggi per esperti (nella remote view)
    public void update(Message message){
        ControllerExecute controller = (ControllerExecute) message.getRequestMessage();
        controller.execute(this, message.getUsername());
    }


    public void playAssistant(PlayAssistantMessage message, String username){
        try{
            checkPlayer(username);
            table.playAssistant(table.getCurrentPlayer().getAssistant(message.getValue()));
            table.nextPlayer();
        }catch (WrongPlayerException e){
            //TODO
            System.out.println("WrongPlayerException");
        }catch (AssistantNotFoundException e){
            //TODO
            System.out.println("AssistantNotFoundException");
        }catch (AssistantNotPlayableException e){
            //TODO
            System.out.println("AssistantNotPlayableException");
        }
    }

    private void checkPlayer(String username){
        if(!username.equals(table.getCurrentPlayer().getUsername()))
            throw new WrongPlayerException("It isn't your turn");
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
            System.out.println("WrongPlayerException");
        } catch (IslandNotValidException e) {
            //TODO
            System.out.println("IslandNotValidException");
        } catch (InvalidEntranceStudentException e) {
            //TODO
            System.out.println("StudentIndexOutOfBoundsException");
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
            System.out.println("WrongPlayerException");
        } catch (InvalidEntranceStudentException e) {
            //TODO
            System.out.println("StudentIndexOutOfBoundsException");
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
            System.out.println("WrongPlayerException");
        } catch (MovementNotValidException e){
            //TODO
            System.out.println("MovementNotValidException");
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
            System.out.println("WrongPlayerException");
        }catch (CloudNotValidException e){
            //TODO
            System.out.println("CloudNotValidException");
        }
    }
}
