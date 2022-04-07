package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.*;

import java.util.ArrayList;
import java.util.List;

public class ControllerExpertMode extends Controller{

    private TableExpertMode table;

    public ControllerExpertMode(TableExpertMode table)
    {
        super(table);
        this.table = table;
    }

    @Override
    public void moveStudentToDining(MoveStudentMessage message, String username){
        super.moveStudentToDining(message, username);
        //devo recuperare il colore dello studente spostato -> color
        ColorS color = ColorS.RED;
        if (table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine(color).size() % 3 == 0){
            table.addCoins(table.getCurrentPlayer(), 1);
        }
    }

    public void payCharacter1(PayCharacter1Message message, String username)
    {
        try {
            table.validCharacter(1);
            table.validIsland(message.getIslandId());
            table.getCard(1).validStudent(message.getStudentId());
            Student student = table.getCard(1).getStudents().remove(message.getStudentId());
            table.getIsland(message.getIslandId()).addStudent(student);
            table.getCard(1).getStudents().add(table.getBag().drawStudent());

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
        catch(InvalidStudentException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter2(PayCharacter2Message message, String username){
        try {
            table.validCharacter(2);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter3(PayCharacter3Message message, String username){
        try {
            table.validCharacter(3);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter4(PayCharacter4Message message, String username){
        try {
            table.validCharacter(4);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter5(PayCharacter5Message message, String username){
        try {
            table.validCharacter(5);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter6(PayCharacter6Message message, String username){
        try {
            table.validCharacter(6);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter7(PayCharacter7Message message, String username){
        try {
            table.validCharacter(7);

            List<Integer> cardStudentsChosen = new ArrayList<>();
            List<Integer> entranceStudentChosen = new ArrayList<>();

            cardStudentsChosen.add(message.getCardStudent1());
            cardStudentsChosen.add(message.getCardStudent2());
            cardStudentsChosen.add(message.getCardStudent3());

            entranceStudentChosen.add(message.getEntranceStudent1());
            entranceStudentChosen.add(message.getEntranceStudent2());
            entranceStudentChosen.add(message.getEntranceStudent3());

            for (int s : cardStudentsChosen){
                table.getCard(7).validStudent(s);
                Student student = table.getCard(7).getStudents().remove(s);
                table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(student);
            }
            for (int s : entranceStudentChosen){
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(s);
                Student student = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(s);
                table.getCard(7).getStudents().add(student);

            }

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidStudentException e) {
            e.printStackTrace();
        } catch (StudentIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void payCharacter8(PayCharacter8Message message, String username){
        try {
            table.validCharacter(8);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter9(PayCharacter9Message message, String username){
        try {
            table.validCharacter(9);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    /*public void payCharacter10(PayCharacter10Message message, String username){
        try {
            table.validCharacter(10);
            List<Integer> diningStudentsChosen = new ArrayList<>();
            List<Integer> entranceStudentChosen = new ArrayList<>();

            diningStudentsChosen.add(message.getDiningStudent1());
            diningStudentsChosen.add(message.getDiningStudent2());

            entranceStudentChosen.add(message.getEntranceStudent1());
            entranceStudentChosen.add(message.getEntranceStudent2());

            for (int s : diningStudentsChosen){
                Student student = table.getCurrentPlayer().getSchoolBoard().getDiningRoom().getLine().remove(s);
                table.getCurrentPlayer().getSchoolBoard().getDiningRoom().g
                table.getCurrentPlayer().getSchoolBoard().getEntrance().addStudent(student);
            }
            for (int s : entranceStudentChosen){
                table.getCurrentPlayer().getSchoolBoard().getEntrance().validStudentIndex(s);
                Student student = table.getCurrentPlayer().getSchoolBoard().getEntrance().getStudents().remove(s);
                table.getCard(7).getStudents().add(student);

            }

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidStudentException e) {
            e.printStackTrace();
        } catch (StudentIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }*/

    public void payCharacter11(PayCharacter11Message message, String username){
        try {
            table.validCharacter(11);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    public void payCharacter12(PayCharacter12Message message, String username){
        try {
            table.validCharacter(12);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }
}