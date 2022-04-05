package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.InvalidCharacterException;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.*;

public class ControllerExpertMode extends Controller{

    private TableExpertMode table;

    public ControllerExpertMode(TableExpertMode table)
    {
        super(table);
        this.table = table;
    }

    public void payCharacter1(PayCharacter1Message message, String username)
    {
        try {
            table.validCharacter(1);

        }catch(InvalidCharacterException e)
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

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
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

    public void payCharacter10(PayCharacter10Message message, String username){
        try {
            table.validCharacter(10);

        }catch(InvalidCharacterException e)
        {
            //TODO
            System.out.println(e.getMessage());
        }
    }

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