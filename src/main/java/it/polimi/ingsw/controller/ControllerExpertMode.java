package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.effects.*;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.requests.ControllerExecuteExpertMode;
import it.polimi.ingsw.network.requests.*;
import it.polimi.ingsw.network.requests.gameMessages.*;
import it.polimi.ingsw.network.responses.reducedModelMessage.CharacterPaidMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.ServerErrorMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * controller expert mode class
 */
public class ControllerExpertMode extends Controller{

    private final TableExpertMode table;


    /**
     * builds the controller expert mode
     * @param table model to modify
     */
    public ControllerExpertMode(TableExpertMode table)
    {
        super(table);
        this.table = table;
    }

    /**
     * it's an override of update() Controller class' method
     * executes a controller method based on the controller message received
     * @param message controller message to be executed
     */
    @Override
    public void update(ControllerMessage message){
        try {
            RequestMessage requestMessage = message.getRequestMessage();
            if (requestMessage instanceof ControllerExecuteExpertMode) {
                table.checkCurrentPlayer(message.getUsername());
                ControllerExecuteExpertMode request = (ControllerExecuteExpertMode) requestMessage;
                request.execute(this);
            } else {
                super.update(message);
            }
        } catch (GameException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * it's an override of chooseCloud() Controller class' method
     * chooses the cloud for the current player and reset the current effect on the table
     * @param message choose cloud message
     */
    @Override
    public void chooseCloud(ChooseCloudMessage message){
        table.resetCurrentEffect();
        table.setCharacterAlreadyPlayed(false);
        super.chooseCloud(message);
    }

    /**
     * it's an override of moveStudentToDining() Controller class' method
     * moves a student to the current player's dining room and possibly gives him a coin
     * @param studentIndex student to move
     */
    @Override
    protected void moveStudentToDining(int studentIndex){
        try {
            table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(studentIndex);
            ColorS color = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(studentIndex).getColor();
            super.moveStudentToDining(studentIndex);
            if (table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(color).size() % 3 == 0 && table.getBank() > 0){
                table.addCoins(table.getCurrentPlayer(), 1);
            }
        } catch (GameException e) {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 1 and executes its effect
     * @param message pay character 1 message
     */
    public void payCharacter1(PayCharacter1Message message)
    {
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.getCardWithStudents(message.getCharacter()).validStudent(message.getStudentId()-1);
            Student student = table.getCardWithStudents(message.getCharacter()).getStudents().remove(message.getStudentId()-1);
            table.getIsland(message.getIslandId()-1).addStudent(student);
            if (!table.getBag().getStudents().isEmpty()) {
                table.getCardWithStudents(message.getCharacter()).getStudents().add(table.getBag().drawStudent());
            }
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException | CardNotFoundException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 2 and executes its effect
     * @param message pay character 2 message
     */
    public void payCharacter2(PayCharacter2Message message){
        try {
            table.validCharacter(message.getCharacter());
            table.setCurrentEffect(new ProfessorTieEffect(table.getCurrentPlayer()));
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e) {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 3 and executes its effect
     * @param message pay character 3 message
     */
    public void payCharacter3(PayCharacter3Message message){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.processIsland(table.getIsland(message.getIslandId()-1));
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e) {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 4 and executes its effect
     * @param message pay character 4 message
     */
    public void payCharacter4(PayCharacter4Message message){
        try {
            table.validCharacter(message.getCharacter());
            table.validAdditionalMovement(message.getAdditionalMovement());
            table.setCurrentEffect(new AdditionalMovementEffect(message.getAdditionalMovement()));
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e)
        {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 5 and executes its effect
     * @param message pay character 5 message
     */
    public void payCharacter5(PayCharacter5Message message){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            Island island = table.getIsland(message.getIslandId()-1);
            table.validNoEntryTile(island);
            table.setNoEntryTile(island, true);
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 6 and executes its effect
     * @param message pay character 6 message
     */
    public void payCharacter6(PayCharacter6Message message){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.setCurrentEffect(new CountTowersEffect(table.getIsland(message.getIslandId()-1)));
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 7 and executes its effect
     * @param message pay character 7 message
     */
    public void payCharacter7(PayCharacter7Message message){
        try {
            //verify all indexes' validity
            table.validCharacter(message.getCharacter());
            for (int i = 0; i < message.getCardStudents().size(); i++) {
                table.getCardWithStudents(message.getCharacter()).validStudent(message.getCardStudents().get(i)-1);
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(message.getEntranceStudents().get(i)-1);
            }

            List<Student> cardStudents = new ArrayList<>();
            List<Student> entranceStudents = new ArrayList<>();

            //pick the students with the assigned indexes
            for (int i = 0; i < message.getCardStudents().size(); i++) {
                int cardIndex = message.getCardStudents().get(i)-1;
                Student cardStudent = table.getCardWithStudents(message.getCharacter()).getStudents().get(cardIndex);
                cardStudents.add(cardStudent);

                int entranceIndex = message.getEntranceStudents().get(i)-1;
                Student entranceStudent = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(entranceIndex);
                entranceStudents.add(entranceStudent);
            }

            //switch selected students
            for (int i = 0; i < message.getCardStudents().size(); i++) {
                table.getCardWithStudents(message.getCharacter()).getStudents().remove(cardStudents.get(i));
                table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(cardStudents.get(i));
                table.getCurrentPlayer().getSchoolBoard().getEntrance().removeStudent(entranceStudents.get(i));
                table.getCardWithStudents(message.getCharacter()).getStudents().add(entranceStudents.get(i));
            }
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException | CardNotFoundException e) {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 8 and executes its effect
     * @param message pay character 8 message
     */
    public void payCharacter8(PayCharacter8Message message){
        try {
            table.validCharacter(message.getCharacter());
            table.setCurrentEffect(new AdditionalInfluenceEffect(table.getCurrentPlayer()));
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e) {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 9 and executes its effect
     * @param message pay character 9 message
     */
    public void payCharacter9(PayCharacter9Message message){
        try {
            table.validCharacter(message.getCharacter());
            ColorS color = message.getColor();
            table.setCurrentEffect(new NoInfluenceColorEffect(color));
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e) {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 10 and executes its effect
     * @param message pay character 10 message
     */
    public void payCharacter10(PayCharacter10Message message){
        try {
            //verify all validity
            table.validCharacter(message.getCharacter());

            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().validColors(message.getDiningStudents());
            for (int i = 0; i < message.getDiningStudents().size(); i++) {
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(message.getEntranceStudents().get(i)-1);
            }

            List<Student> entranceStudents = new ArrayList<>();
            List<Student> diningStudents = new ArrayList<>();

            //pick the students with the assigned indexes
            for (int i = 0; i < message.getEntranceStudents().size(); i++) {
                int entranceIndex = message.getEntranceStudents().get(i)-1;
                Student entranceStudent = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(entranceIndex);
                entranceStudents.add(entranceStudent);

                ColorS diningColor = message.getDiningStudents().get(i);
                Student diningStudent = table.getCurrentPlayer().getSchoolBoard().getDiningRoom().removeStudent(diningColor);
                diningStudents.add(diningStudent);
            }

            //switch selected students
            for (int i = 0; i < message.getDiningStudents().size(); i++) {
                table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(diningStudents.get(i));
                table.getCurrentPlayer().getSchoolBoard().getEntrance().removeStudent(entranceStudents.get(i));
                table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(entranceStudents.get(i));
                table.setProfessorOwner(entranceStudents.get(i).getColor(), table.getCurrentPlayer());
            }
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e) {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 11 and executes its effect
     * @param message pay character 11 message
     */
    public void payCharacter11(PayCharacter11Message message){
        try {
            table.validCharacter(message.getCharacter());
            table.getCardWithStudents(message.getCharacter()).validStudent(message.getStudentId()-1);
            Student student = table.getCardWithStudents(message.getCharacter()).getStudents().remove(message.getStudentId()-1);
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(student);
            if (!table.getBag().getStudents().isEmpty()) {
                table.getCardWithStudents(message.getCharacter()).getStudents().add(table.getBag().drawStudent());
            }
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException | CardNotFoundException e) {
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays character 12 and executes its effect
     * @param message pay character 12 message
     */
    public void payCharacter12(PayCharacter12Message message){
        try {
            table.validCharacter(message.getCharacter());
            ColorS color = message.getColor();
            for (Player p : table.getPlayers()) {
                if (p.getSchoolBoard().getDiningRoom().getLine(color).size() < 3) {
                    int n = p.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(color);
                    for (int i = 0; i < n; i++) {
                        table.getBag().addStudent(p.getSchoolBoard().getDiningRoom().removeStudent(color));
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        Student student = p.getSchoolBoard().getDiningRoom().removeStudent(color);
                        table.getBag().addStudent(student);
                    }
                }
            }
            pay(message.getCharacter());
            table.notify(new CharacterPaidMessage(table.createReducedModel(), message.getCharacter()));
        }catch(GameException e){
            table.notify(new ServerErrorMessage(e.getMessage()));
        }
    }

    /**
     * pays a character card for the current player and increments its cost
     * @param character character card's id to pay
     */
    private void pay(int character) {
        int cost = table.getCharacters().get(character);
        table.pay(table.getCurrentPlayer(), cost);
        table.incrementCardCost(character);
        table.setCharacterAlreadyPlayed(true);
    }
}