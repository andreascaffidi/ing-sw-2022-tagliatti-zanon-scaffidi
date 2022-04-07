package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.network.messages.MoveStudentMessage;

public class ControllerExpertMode extends Controller{

    private TableExpertMode table;

    public ControllerExpertMode(TableExpertMode table){
        super(table);
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

    public void payCharacter(){
        this.validCharacter(1);
        //request input (switch case)
    }

    public void payCharacter1(){
        this.validCharacter(1);
        //effect character 1
    }

    public void payCharacter2(){
        this.validCharacter(2);
    }

    public void payCharacter3(){
        this.validCharacter(3);
    }

    public void payCharacter4(){
        this.validCharacter(4);
    }

    public void payCharacter5(){
        this.validCharacter(5);
    }

    public void payCharacter6(){
        this.validCharacter(6);
    }

    public void payCharacter7(){
        this.validCharacter(7);
    }

    public void payCharacter8(){
        this.validCharacter(8);
    }

    public void payCharacter9(){
        this.validCharacter(9);
    }

    public void payCharacter10(){
        this.validCharacter(10);
    }

    public void payCharacter11(){
        this.validCharacter(11);
    }

    public void payCharacter12(){
        this.validCharacter(12);
    }

    private void validCharacter(int character){
        //check if you can play that character
        //check if player has already played a character
    }

}
