package it.polimi.ingsw;

import it.polimi.ingsw.model.Table;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Table table = new Table();
        table.setupAssistantCards();
        System.out.println( "Hello World!" );
    }
}
