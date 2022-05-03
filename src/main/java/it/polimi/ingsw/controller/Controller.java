package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;
import it.polimi.ingsw.network.requests.gameMessages.MoveMotherNatureMessage;
import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;
import it.polimi.ingsw.utils.Observer;

/**
 * controller class
 */
public class Controller implements Observer<ControllerMessage> {

    private final Table table;

    /**
     * builds the controller
     * @param table to control
     */
    public Controller(Table table){
        this.table = table;
    }


    /**
     * executes a method of the controller after the received message
     * @param message that requests the execution of a specific controller method
     */
    //TODO: proibire al metodo di eseguire messaggi per esperti (nella remote view)
    @Override
    public void update(ControllerMessage message){
        ControllerExecute controller = (ControllerExecute) message.getRequestMessage();
        controller.execute(this, message.getUsername());
    }


    /**
     * plays assistant
     * @param message play assistant
     * @param username player that chooses the assistant
     */
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

    /**
     * checks if it's the player turn
     * @param username of the player
     */
    private void checkPlayer(String username){
        if(!username.equals(table.getCurrentPlayer().getUsername()))
            throw new WrongPlayerException("It isn't your turn");
    }


    /**
     * moves student to island
     * @param message move student to island
     * @param username player that chooses to move the student to island
     */
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
        } catch (InvalidEntranceStudentException e) {
            //TODO
            System.out.println("StudentIndexOutOfBoundsException");
        }
    }

    /**
     * moves student to dining room
     * @param message move student to dining
     * @param username player that chooses to move the student to dining room
     */
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


    /**
     * moves mother nature
     * @param message move mother nature
     * @param username player that moves mother nature
     */
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


    /**
     * chooses the cloud
     * @param message choose cloud
     * @param username of the player that chooses the cloud
     */
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
