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

import java.util.ArrayList;
import java.util.List;

/**
 * controller expert mode class
 */
public class ControllerExpertMode extends Controller{

    private final TableExpertMode table;


    /**
     * builds the controller expert mode
     * @param table to control
     */
    public ControllerExpertMode(TableExpertMode table)
    {
        super(table);
        this.table = table;
    }

    /**
     * executes a method of the controller expert mode after the received message
     * @param message that requests the execution of a specific controller method
     */
    @Override
    public void update(ControllerMessage message){
        if (message.isExpertMode()){
            ControllerExecuteExpertMode controller = (ControllerExecuteExpertMode) message.getRequestMessage();
            controller.execute(this, message.getUsername());
        }
        else
        {
            super.update(message);
        }
    }

    /**
     * chooses the cloud
     * @param message choose cloud
     * @param username of the player that chooses the cloud
     */
    @Override
    public void chooseCloud(ChooseCloudMessage message, String username){
        super.chooseCloud(message, username);
        table.resetCurrentEffect();
    }

    /**
     * moves student to dining room
     * @param message move student to dining
     * @param username player that chooses to move the student to dining room
     */
    @Override
    public void moveStudentToDining(MoveStudentMessage message, String username){
        try {
            ColorS color = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(message.getStudentIndex()-1).getColor();
            super.moveStudentToDining(message, username);
            if (table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(color).size() % 3 == 0 && table.getBank() > 0){
                table.addCoins(table.getCurrentPlayer(), 1);
            }
        } catch (IndexOutOfBoundsException e){
            //TODO:
            System.out.println("StudentIndexOutOfBoundsException");
        }
    }

    /**
     * plays character 1
     * @param message pay character 1
     * @param username of the player that plays the character
     */
    public void payCharacter1(PayCharacter1Message message, String username)
    {
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.getCardWithStudents(message.getCharacter()).validStudent(message.getStudentId()-1);
            Student student = table.getCardWithStudents(message.getCharacter()).getStudents().remove(message.getStudentId()-1);
            table.getIsland(message.getIslandId()-1).addStudent(student);
            table.getCardWithStudents(message.getCharacter()).getStudents().add(table.getBag().drawStudent());
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e){
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 2
     * @param message pay character 2
     * @param username of the player that plays the character
     */
    public void payCharacter2(PayCharacter2Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            table.setCurrentEffect(new ProfessorTieEffect(table.getCurrentPlayer()));
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 3
     * @param message pay character 3
     * @param username of the player that plays the character
     */
    public void payCharacter3(PayCharacter3Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.processIsland(table.getIsland(message.getIslandId()-1));
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 4
     * @param message pay character 4
     * @param username of the player that plays the character
     */
    public void payCharacter4(PayCharacter4Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            table.validAdditionalMovement(message.getAdditionalMovement());
            table.moveMotherNature(message.getAdditionalMovement());
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 5
     * @param message pay character 5
     * @param username of the player that plays the character
     */
    public void payCharacter5(PayCharacter5Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            Island island = table.getIsland(message.getIslandId()-1);
            table.validNoEntryTile(island);
            table.setNoEntryTile(island, true);
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e){
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 6
     * @param message pay character 6
     * @param username of the player that plays the character
     */
    public void payCharacter6(PayCharacter6Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.setCurrentEffect(new CountTowersEffect(table.getIsland(message.getIslandId()-1)));
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e){
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 7
     * @param message pay character 7
     * @param username of the player that plays the character
     */
    public void payCharacter7(PayCharacter7Message message, String username){
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
        }catch(GameException | NotEnoughCoinsException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 8
     * @param message pay character 8
     * @param username of the player that plays the character
     */
    public void payCharacter8(PayCharacter8Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            table.setCurrentEffect(new AdditionalInfluenceEffect(table.getCurrentPlayer()));
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 9
     * @param message pay character 9
     * @param username of the player that plays the character
     */
    public void payCharacter9(PayCharacter9Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            ColorS color = ColorS.parseToColor(message.getColor());
            table.setCurrentEffect(new NoInfluenceColorEffect(color));
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 10
     * @param message pay character 10
     * @param username of the player that plays the character
     */
    public void payCharacter10(PayCharacter10Message message, String username){
        try {
            //verify all validity
            table.validCharacter(message.getCharacter());
            for (int i = 0; i < message.getDiningStudents().size(); i++) {
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(message.getEntranceStudents().get(i)-1);
            }
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().validColors(message.getDiningStudents());

            List<Student> entranceStudents = new ArrayList<>();
            List<Student> diningStudents = new ArrayList<>();

            //pick the students with the assigned indexes
            for (int i = 0; i < message.getEntranceStudents().size(); i++) {
                int entranceIndex = message.getEntranceStudents().get(i)-1;
                Student entranceStudent = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(entranceIndex);
                entranceStudents.add(entranceStudent);

                ColorS diningColor = ColorS.parseToColor(message.getDiningStudents().get(i));
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
        }catch(GameException | NotEnoughCoinsException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 11
     * @param message pay character 11
     * @param username of the player that plays the character
     */
    public void payCharacter11(PayCharacter11Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            table.getCardWithStudents(message.getCharacter()).validStudent(message.getStudentId()-1);
            Student student = table.getCardWithStudents(message.getCharacter()).getStudents().remove(message.getStudentId()-1);
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(student);
            table.getCardWithStudents(message.getCharacter()).getStudents().add(table.getBag().drawStudent());
            pay(message.getCharacter());
        }catch(GameException | NotEnoughCoinsException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays character 12
     * @param message pay character 12
     * @param username of the player that plays the character
     */
    public void payCharacter12(PayCharacter12Message message, String username){
        try {
            table.validCharacter(message.getCharacter());
            ColorS color = ColorS.parseToColor(message.getColor());
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
        }catch(GameException | NotEnoughCoinsException e){
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /**
     * plays a character and increments its cost
     * @param character to play
     */
    private void pay(int character) {
        int cost = table.getCharacters().get(character);
        table.pay(table.getCurrentPlayer(), cost);
        table.incrementCardCost(character);
    }
}