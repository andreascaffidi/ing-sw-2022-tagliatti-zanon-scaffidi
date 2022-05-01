package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.*;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.ClientErrorMessage;
import it.polimi.ingsw.network.responses.reducedModelMessage.ServerErrorMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponseMessage;
import it.polimi.ingsw.network.responses.setupMessages.ShowLobbyMessage;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.utils.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Observable<ControllerMessage> implements Runnable, Observer<ResponseMessage> {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Server server;
    private String usernameConnection;
    private String hostLobby;
    private boolean active = true;

    public Connection(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
    }

    private synchronized boolean isActive(){
        return active;
    }

    public void send(ResponseMessage message){
        try {
            synchronized (out) {
                if (!isActive()) {
                    return;
                }
                out.writeObject(message);
                out.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

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
            close();
        }
    }

    private void receive() throws IOException, ClassNotFoundException {
        RequestMessage request = (RequestMessage) in.readObject();
        if (request instanceof SetupExecute){
            ((SetupExecute) request).execute(this);
        }
        if (request instanceof ControllerExecute || request instanceof ControllerExecuteExpertMode){
            notify(new ControllerMessage(request, usernameConnection));
        }
    }

    public void setupConnection(SetupRequestMessage message){
        String typeOfMessage = message.getTypeOfMessage().toUpperCase();
        switch(typeOfMessage){
            case "USERNAME":
                String username = message.getMessage();
                if(!server.validUsername(username, this)){
                    send(new ClientErrorMessage("This nickname already exists, try another one: "));
                }else {
                    this.usernameConnection = username;
                    send(new SetupResponseMessage(ClientState.MENU));
                } break;

            case "COMMAND":
                String command = message.getMessage();
                if (command.equals("JOIN")){
                    send(new ShowLobbyMessage(server.getLobbies()));
                }
                if (command.equals("CREATE")){
                    send(new SetupResponseMessage(ClientState.CREATE_LOBBY));
                } break;

            case "JOIN_LOBBY":
                String selectedLobby = message.getMessage();
                this.hostLobby = selectedLobby;
                server.joinLobby(selectedLobby, this);
                break;
        }
    }

    public void LobbySettings(CreateLobbyMessage message){
        server.createLobby(message, this);
    }

    private void close(){
        try{
            socket.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }

    public synchronized void closeConnection(){
        send(new ServerErrorMessage("Disconnected from the server "));
        try{
            socket.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        active = false;
    }

    public String getHostLobby() {
        return hostLobby;
    }

    public String getUsernameConnection() {
        return usernameConnection;
    }

    @Override
    public void update(ResponseMessage message) {
        send(message);
    }
}
