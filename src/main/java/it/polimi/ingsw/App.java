package it.polimi.ingsw;

import it.polimi.ingsw.network.client.Client;

import java.io.IOException;
import java.util.Scanner;

import static it.polimi.ingsw.ServerApp.SERVER_PORT;

public class App
{
    public static void main( String[] args ) throws IOException {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Inserisci username");
        String username = stdin.nextLine();
        Client client = new Client(username, "127.0.0.1", SERVER_PORT);
        client.startClient();
    }
}

