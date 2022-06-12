package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.*;
import it.polimi.ingsw.network.requests.setupMessages.ChooseTeamMessage;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.ServerErrorMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponseMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestTypes;
import it.polimi.ingsw.network.responses.setupMessages.ShowLobbyMessage;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.utils.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * connection class (used to connect a client to the server)
 */
public class Connection extends Observable<ControllerMessage> implements Runnable, Observer<ResponseMessage> {

    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final Server server;
    private String usernameConnection;
    private String hostLobby;
    private boolean active = true;

    /**
     * builds a connection
     * @param socket socket connection
     * @param server server
     */
    public Connection(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
    }

    /**
     * checks if connection is active
     * @return true if connection is active, else false
     */
    private synchronized boolean isActive(){
        return active;
    }

    /**
     * sends a response message to the client through the socket's output stream
     * @param message response message
     */
    public void send(ResponseMessage message){
        try {
            if (!isActive()) {
                return;
            }
            out.writeObject(message);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * starts the main process of the connection (receiving messages from the client)
     */
    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            send(new SetupResponseMessage(ClientState.WELCOME));
            while(isActive()){
                receive();
            }
        } catch(IOException e){
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * receives a request message from the client through the socket's input stream
     * @throws IOException if there are IO problems
     * @throws ClassNotFoundException if there are problems with readObject() method
     */
    private void receive() throws IOException, ClassNotFoundException {
        RequestMessage request = (RequestMessage) in.readObject();
        if (request instanceof SetupExecute){
            ((SetupExecute) request).execute(this);
        }
        if (request instanceof ControllerExecute || request instanceof ControllerExecuteExpertMode){
            notify(new ControllerMessage(request, usernameConnection));
        }
    }

    /**
     * handles a setup message based on the specific type of the message (SetupTypeMessage)
     * @param message setup message (contains a string and a SetupRequestType)
     */
    public void setupConnection(SetupRequestMessage message){
        SetupRequestTypes typeOfMessage = message.getTypeOfMessage();
        switch(typeOfMessage){
            case USERNAME:
                String username = message.getMessage();
                if(!server.validUsername(username, this)){
                    send(new ServerErrorMessage("This username already exists, try another one: "));
                }else {
                    this.usernameConnection = username;
                    send(new SetupResponseMessage(ClientState.MENU));
                }
                break;

            case COMMAND:
                String command = message.getMessage();
                if (command.equals("JOIN")){
                    send(new ShowLobbyMessage(server.getWaitingLobbies()));
                }
                if (command.equals("CREATE")){
                    send(new SetupResponseMessage(ClientState.CREATE_LOBBY));
                }
                break;

            case JOIN_LOBBY:
                String selectedLobby = message.getMessage();
                this.hostLobby = selectedLobby;
                server.joinLobby(selectedLobby, this);
                break;
        }
    }

    /**
     * creates a lobby with the specific setting on the server
     * @param message lobby settings message
     */
    public void lobbySettings(CreateLobbyMessage message){
        this.hostLobby = this.getUsernameConnection();
        server.createLobby(message, this);
    }

    /**
     * chooses a team for this lobby and set it on the server
     * @param message choose team message
     */
    public void chooseTeam(ChooseTeamMessage message){
        server.setLobbyTeam(message, this);
    }

    /**
     * closes the connection
     */
    public synchronized void closeConnection(){
        try{
            socket.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Removing client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
        active = false;
    }

    /**
     * gets the host of the lobby joined
     * @return host username
     */
    public String getHostLobby() {
        return hostLobby;
    }

    /**
     * sets the host of the lobby joined
     * @param hostLobby host name
     */
    public void setHostLobby(String hostLobby) {
        this.hostLobby = hostLobby;
    }

    /**
     * get the username of the client
     * @return client's username
     */
    public String getUsernameConnection() {
        return usernameConnection;
    }

    /**
     * handles a message received from the model
     * @param message generic message
     */
    @Override
    public void update(ResponseMessage message) {
        send(message);
    }
}
