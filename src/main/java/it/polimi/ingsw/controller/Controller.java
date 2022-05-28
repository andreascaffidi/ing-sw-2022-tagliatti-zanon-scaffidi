package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.ControllerExecute;
import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;
import it.polimi.ingsw.network.requests.gameMessages.MoveMotherNatureMessage;
import it.polimi.ingsw.network.requests.gameMessages.MoveStudentMessage;
import it.polimi.ingsw.network.requests.gameMessages.PlayAssistantMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.ReducedModelMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.ServerErrorMessage;
import it.polimi.ingsw.utils.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * controller class
 */
public class Controller implements Observer<ControllerMessage> {

    private final Table table;

    /**
     * builds the controller
     * @param table model to modify
     */
    public Controller(Table table){
        this.table = table;
    }


    /**
     * called when Connection class calls notify()
     * executes a controller method based on the controller message received
     * @param message controller message to be executed
     */
    @Override
    public void update(ControllerMessage message){
        try {
            table.checkCurrentPlayer(message.getUsername());
            ControllerExecute request = (ControllerExecute) message.getRequestMessage();
            request.execute(this);
        } catch (GameException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }


    /**
     * plays an assistant card for the current player
     * @param message play assistant message
     */
    public void playAssistant(PlayAssistantMessage message){
        try{
            table.playAssistant(table.getCurrentPlayer().getAssistant(message.getValue()));
        }catch (AssistantNotFoundException | GameException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * moves a student to an island
     * @param studentIndex student to move
     * @param idIsland island on which place student
     */
    private void moveStudentToIsland(int studentIndex, int idIsland){
        Student student = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(studentIndex);
        table.getIsland(idIsland).addStudent(student);
    }

    /**
     * moves a student to the current player's dining room
     * @param studentIndex student to move
     */
    protected void moveStudentToDining(int studentIndex){
        Student student = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(studentIndex);
        table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(student);
        table.setProfessorOwner(student.getColor(), table.getCurrentPlayer());
    }

    /**
     * moves students from current player's entrance to the dining room or to an island chosen
     * @param message move students message
     */
    public void moveStudents(MoveStudentMessage message){
        try{
            List<Integer> studentIndexes = new ArrayList<>(message.getMovements().keySet());

            //verify all movements' validity
            for (int student : studentIndexes){
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(student-1);
                String destination = message.getMovements().get(student);
                if (!destination.equals("DINING ROOM")){
                    int idIsland = Integer.parseInt(destination);
                    table.validIsland(idIsland-1);
                }
            }

            //add students to the game
            for (int student : studentIndexes){
                String destination = message.getMovements().get(student);
                if (destination.equals("DINING ROOM")){
                    moveStudentToDining(student-1);
                }else{
                    int idIsland = Integer.parseInt(destination);
                    moveStudentToIsland(student-1, idIsland-1);
                }
            }

            //remove students from the entrance
            for (int student : studentIndexes){
                table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(student-1);
            }

            table.notify(new ReducedModelMessage(ClientState.MOVE_MN, table.createReducedModel()));
        } catch (GameException | NumberFormatException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }


    /**
     * moves mother nature and processes the island on which it is placed
     * @param message move mother nature message
     */
    public void moveMotherNature(MoveMotherNatureMessage message){
        try{
            int movement = message.getMovements();
            table.getCurrentPlayer().validMovement(movement);
            table.moveMotherNature(movement);
            table.processIsland(table.motherNatureIsland());
            //TODO: test this endgame condition
            if (!table.isLastRound()){
                table.notify(new ReducedModelMessage(ClientState.CHOOSE_CLOUD, table.createReducedModel()));
            }else{
                table.lastRound();
            }
        } catch (GameException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }


    /**
     * chooses the cloud for the current player
     * @param message choose cloud message
     */
    public void chooseCloud(ChooseCloudMessage message){
        try {
            int idCloud = message.getId()-1;
            table.validCloud(idCloud);
            Cloud cloud = table.getClouds().get(idCloud);
            table.addStudentsToEntrance(cloud);
        }catch (GameException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }
}
