package it.polimi.ingsw.network.client;

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
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String ip;
    private int port;

    private String username;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private AbstractClientState currentState;
    private ClientState nextState;

    private List<Lobby> availableLobbies;

    private UI ui;

    private ReducedModel reducedModel;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.ui = new CLI();
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
            System.out.println("Connection closed from the client side");
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

    public void changeState(ClientState newState){
        nextState = newState;
        currentState = ui.getClientState(nextState, this);
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
}
