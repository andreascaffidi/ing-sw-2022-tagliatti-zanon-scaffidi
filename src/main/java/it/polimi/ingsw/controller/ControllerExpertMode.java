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
import it.polimi.ingsw.network.requests.messages.*;
import it.polimi.ingsw.view.View;

import java.util.ArrayList;
import java.util.List;

public class ControllerExpertMode extends Controller{

    private TableExpertMode table;


    //FIXME: gestione controllerExpertMode
    public ControllerExpertMode(TableExpertMode table)
    {
        super(table);
        this.table = table;
    }

    @Override
    public void update(ControllerMessage message){
        if (message.isExpertMode()){
            ControllerExecuteExpertMode controller = (ControllerExecuteExpertMode) message.getRequestMessage();
            controller.execute(this, message.getUsername(), message.getView());
        }
        else
        {
            super.update(message);
        }
    }

    @Override
    public void chooseCloud(ChooseCloudMessage message, String username, View view){
        super.chooseCloud(message, username, view);
        table.resetCurrentEffect();
    }

    //FIXME: non funziona l'override
    @Override
    public void moveStudentToDining(MoveStudentMessage message, String username, View view){
        try {
            ColorS color = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(message.getStudentIndex()-1).getColor();
            super.moveStudentToDining(message, username, view);
            if (table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(color).size() % 3 == 0){
                table.addCoins(table.getCurrentPlayer(), 1);
            }
        } catch (IndexOutOfBoundsException e){
            //TODO:
            System.out.println("StudentIndexOutOfBoundsException");
        }
    }

    public void payCharacter1(PayCharacter1Message message, String username, View view)
    {
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.getCardWithStudents(message.getCharacter()).validStudent(message.getStudentId()-1);
            Student student = table.getCardWithStudents(message.getCharacter()).getStudents().remove(message.getStudentId()-1);
            table.getIsland(message.getIslandId()-1).addStudent(student);
            table.getCardWithStudents(message.getCharacter()).getStudents().add(table.getBag().drawStudent());
            pay(message.getCharacter());
        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
        catch(IslandNotValidException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
        catch(CardNotFoundException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
        catch(InvalidCardStudentException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (NotEnoughCoinsException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter2(PayCharacter2Message message, String username, View view){
        try {
            table.validCharacter(message.getCharacter());
            table.setCurrentEffect(new Effect(new ProfessorTieEffect(table.getCurrentPlayer())));
            pay(message.getCharacter());
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter3(PayCharacter3Message message, String username, View view){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.processIsland(table.getIsland(message.getIslandId()-1));
            pay(message.getCharacter());
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (IslandNotValidException e) {
            e.printStackTrace();
        }
    }

    public void payCharacter4(PayCharacter4Message message, String username, View view){
        try {
            table.validCharacter(message.getCharacter());
            table.validAdditionalMovement(message.getAdditionalMovement());
            table.moveMotherNature(message.getAdditionalMovement());
            pay(message.getCharacter());
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (InvalidAdditionalMovementException e) {
            e.printStackTrace();
        }
    }

    public void payCharacter5(PayCharacter5Message message, String username, View view){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            Island island = table.getIsland(message.getIslandId()-1);
            table.validNoEntryTile(island);
            table.setNoEntryTile(island, true);
            pay(message.getCharacter());
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (IslandNotValidException e) {
            e.printStackTrace();
        } catch (InvalidNoEntryTileException e) {
            e.printStackTrace();
        } catch (TooManyNoEntryTileException e) {
            e.printStackTrace();
        }
    }

    public void payCharacter6(PayCharacter6Message message, String username, View view){
        try {
            table.validCharacter(message.getCharacter());
            table.validIsland(message.getIslandId()-1);
            table.setCurrentEffect(new Effect(new CountTowersEffect(table.getIsland(message.getIslandId()-1))));
            pay(message.getCharacter());
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (IslandNotValidException e) {
            e.printStackTrace();
        }
    }

    public void payCharacter7(PayCharacter7Message message, String username, View view){
        try {
            //verify all indexes' validity
            table.validCharacter(message.getCharacter());
            for (int i = 0; i < message.getCardStudents().size(); i++) {
                table.getCardWithStudents(message.getCharacter()).validStudent(message.getCardStudents().get(i));
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(message.getEntranceStudents().get(i));
            }

            List<Student> cardStudents = new ArrayList<>();
            List<Student> entranceStudents = new ArrayList<>();

            //pick the students with the assigned indexes
            for (int i = 0; i < message.getCardStudents().size(); i++) {
                Student cardStudent = table.getCardWithStudents(message.getCharacter()).getStudents().get(i);
                Student entranceStudent = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(i);
                cardStudents.add(cardStudent);
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
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidCardStudentException e) {
            e.printStackTrace();
        } catch (InvalidEntranceStudentException e) {
            e.printStackTrace();
        }
    }

    public void payCharacter8(PayCharacter8Message message, String username, View view){
        try {
            table.validCharacter(message.getCharacter());
            table.setCurrentEffect(new Effect(new AdditionalInfluenceEffect(table.getCurrentPlayer())));
            pay(message.getCharacter());
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter9(PayCharacter9Message message, String username, View view){
        try {
            table.validCharacter(message.getCharacter());
            ColorS color = ColorS.parseToColor(message.getColor());
            table.setCurrentEffect(new Effect(new NoInfluenceColorEffect(color)));
            pay(message.getCharacter());
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (ColorNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void payCharacter10(PayCharacter10Message message, String username, View view){
        try {
            //verify all validity
            table.validCharacter(message.getCharacter());
            for (int i = 0; i < message.getDiningStudents().size(); i++) {
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(message.getEntranceStudents().get(i));
            }
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().validColors(message.getDiningStudents());

            List<Student> entranceStudents = new ArrayList<>();

            //pick the students with the assigned indexes
            for (int i = 0; i < message.getEntranceStudents().size(); i++) {
                Student entranceStudent = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().get(i);
                entranceStudents.add(entranceStudent);
            }

            //switch selected students
            for (int i = 0; i < message.getDiningStudents().size(); i++) {
                Student diningStudent = table.getCurrentPlayer().getSchoolBoard().getDiningRoom().removeStudent(ColorS.parseToColor(message.getDiningStudents().get(i)));
                table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(diningStudent);
                table.getCurrentPlayer().getSchoolBoard().getEntrance().removeStudent(entranceStudents.get(i));
                table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(entranceStudents.get(i));
                table.setProfessorOwner(entranceStudents.get(i).getColor(), table.getCurrentPlayer());
            }
            pay(message.getCharacter());
        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (InvalidEntranceStudentException e) {
            e.printStackTrace();
        } catch (NotEnoughCoinsException ex) {
            ex.printStackTrace();
        } catch (ColorNotFoundException ex) {
            ex.printStackTrace();
        } catch (InvalidColorsException ex) {
            ex.printStackTrace();
        }
    }

    public void payCharacter11(PayCharacter11Message message, String username, View view){
        try {
            table.validCharacter(message.getCharacter());
            table.getCardWithStudents(message.getCharacter()).validStudent(message.getStudentId()-1);
            Student student = table.getCardWithStudents(message.getCharacter()).getStudents().remove(message.getStudentId()-1);
            table.getCurrentPlayer().getSchoolBoard().getDiningRoom().addStudent(student);
            table.getCardWithStudents(message.getCharacter()).getStudents().add(table.getBag().drawStudent());
            pay(message.getCharacter());
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (CardNotFoundException | InvalidCardStudentException ex) {
            ex.printStackTrace();
        }
    }

    public void payCharacter12(PayCharacter12Message message, String username, View view){
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
        }catch(InvalidCharacterException | NotEnoughCoinsException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (ColorNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void pay(int character) {
        int cost = table.getCharacters().get(character);
        table.pay(table.getCurrentPlayer(), cost);
        table.incrementCardCost(character);
    }
}