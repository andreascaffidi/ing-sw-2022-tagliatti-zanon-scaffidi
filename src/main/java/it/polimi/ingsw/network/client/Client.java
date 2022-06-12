package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.responses.ClientExecute;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.client.UI.UI;
import it.polimi.ingsw.network.server.Lobby;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private boolean active = true;

    private AbstractClientState currentState;
    private ClientState backState;

    private List<Lobby> availableLobbies;

    private final UI ui;

    private ReducedModel reducedModel;

    private String waitingMessage;

    private String winner;

    /**
     * builds a client
     * @param ip ip address
     * @param port socket port
     * @param ui user interface used (GUI or CLI)
     */
    public Client(String ip, int port, UI ui) {
        this.ip = ip;
        this.port = port;
        this.ui = ui;
        this.availableLobbies = new ArrayList<>();
    }

    /**
     * starts the main process on the client (called in ClientApp)
     * @throws IOException if there are IO problems
     */
    public void startClient() throws IOException{
        Socket socket = new Socket(ip, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        try{
            while(active) {
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

    /**
     * sends a request message to the server through the socket's output stream
     * @param request request message
     */
    public void send(RequestMessage request){
        try{
            out.writeObject(request);
            out.reset();
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * receives a response message from the server through the socket's input stream
     * @throws IOException if there are IO problems
     * @throws ClassNotFoundException if there are problems with readObject() method
     */
    private void receive() throws IOException, ClassNotFoundException {
        ResponseMessage response = (ResponseMessage) in.readObject();
        if (response instanceof ClientExecute){
            ( (ClientExecute) response).execute(this);
        }else{
            throw new IllegalStateException();
        }
    }

    /**
     * changes the state of the client
     * @param nextState client state to set
     */
    public void changeState(ClientState nextState){
        currentState = ui.getClientState(nextState, this);
        if (nextState != ClientState.PLAY_CHARACTER){
            backState = nextState;
        }
        currentState.render();
    }

    /**
     * sets the client's username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets the client's username
     * @return client's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the available lobbies for the client
     * @param availableLobbies available lobbies
     */
    public void setAvailableLobbies(List<Lobby> availableLobbies) {
        this.availableLobbies = availableLobbies;
    }

    /**
     * gets the available lobbies for the client
     * @return available lobbies
     */
    public List<Lobby> getAvailableLobbies() {
        return availableLobbies;
    }

    /**
     * set the reduced model of the current match
     * @param reducedModel reduced model
     */
    public void setReducedModel(ReducedModel reducedModel) {
        this.reducedModel = reducedModel;
    }

    /**
     * gets the reduced model of the current match
     * @return reduced model
     */
    public ReducedModel getReducedModel() {
        return reducedModel;
    }

    /**
     * gets the current state of the client
     * @return current client state
     */
    public AbstractClientState getCurrentState() {
        return currentState;
    }

    /**
     * gets the waiting message to show
     * @return waiting message
     */
    public String getWaitingMessage() {
        return waitingMessage;
    }

    /**
     * sets a waiting message for waiting state
     * @param waitingMessage waiting message
     */
    public void setWaitingMessage(String waitingMessage) {
        this.waitingMessage = waitingMessage;
    }

    /**
     * sets the winner of the current match
     * @param winner winner player
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * gets the winner of the current match
     * @return winner player
     */
    public String getWinner()
    {
        return this.winner;
    }

    /**
     * gets the previous client state (used when playing character card)
     * @return previous client state
     */
    public ClientState getBackState() {
        return backState;
    }

    /**
     * disconnects this client
     */
    public void disconnectClient(){
        this.active = false;
    }

    /**
     * gets the UI
     * @return UI
     */
    public UI getUI() {
        return ui;
    }
}
