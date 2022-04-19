package it.polimi.ingsw.network.server;

import it.polimi.ingsw.exceptions.SendMessageException;
import it.polimi.ingsw.exceptions.WrongPlayerException;
import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.responses.messages.NotYourTurnResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketConnection implements Runnable{
    private final Server server;
    private final Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketConnection(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out   =  new ObjectOutputStream(socket.getOutputStream());
            in    =  new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            ControllerMessage request = receive();
            if(request != null) {
                try {
                    //prendo il controller e inoltro il messaggio
                    server.getGames().get(this).update(request);

                }catch (WrongPlayerException e){
                    send(new NotYourTurnResponse());
                }catch (SendMessageException e){
                    send(e.getResponse());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            disconnect();
        }
    }

    public ControllerMessage receive() throws IOException, ClassNotFoundException {
        return (ControllerMessage) in.readObject();
    }

    public void send(ResponseMessage message){
        try{
            out.writeObject(message);
            out.reset();
        }catch (IOException e){
            print(e.getMessage());
        }
    }

    public void print(String text){
        server.print(text);
    }


    public void disconnect(){

    }

}
