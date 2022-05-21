package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.UI.GUI.GUI;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.client.UI.CLI.CLI;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.server.Lobby;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Eriantys Client side
 */
public class Client {
    private final String ip;
    private final int port;

    private String username;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private AbstractClientState currentState;

    private List<Lobby> availableLobbies;

    private UI ui;

    private ReducedModel reducedModel;

    private String waitingMessage;

    private String winner;

    public Client(String ip, int port, UI ui) {
        this.ip = ip;
        this.port = port;

        //FIXME: new CLI() is only an example
        this.ui = ui;
        if (this.ui instanceof GUI){
            ((GUI) this.ui).init();
        }
        this.availableLobbies = new ArrayList<>();
    }

    public void startClient() throws IOException{
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        try{
            while(true) {
                receive();
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Connection closed from the server side");
        }
        finally {
            in.close();
            out.close();
            socket.close();
        }
    }

    public void send(RequestMessage request){
        try{
            out.writeObject(request);
            out.reset();
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void receive() throws IOException, ClassNotFoundException {
        ResponseMessage response = (ResponseMessage) in.readObject();
        if (response instanceof ClientExecute){
            ( (ClientExecute) response).execute(this);
        }else{
            throw new IllegalStateException();
        }
    }

    public void changeState(ClientState nextState) throws MalformedURLException {
        currentState = ui.getClientState(nextState, this);
        //ui.clearScreen();
        currentState.render();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAvailableLobbies(List<Lobby> availableLobbies) {
        this.availableLobbies = availableLobbies;
    }

    public List<Lobby> getAvailableLobbies() {
        return availableLobbies;
    }

    public void setReducedModel(ReducedModel reducedModel) {
        this.reducedModel = reducedModel;
    }

    public ReducedModel getReducedModel() {
        return reducedModel;
    }

    public AbstractClientState getCurrentState() {
        return currentState;
    }

    public String getWaitingMessage() {
        return waitingMessage;
    }

    public void setWaitingMessage(String waitingMessage) {
        this.waitingMessage = waitingMessage;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner()
    {
        return this.winner;
    }
}
