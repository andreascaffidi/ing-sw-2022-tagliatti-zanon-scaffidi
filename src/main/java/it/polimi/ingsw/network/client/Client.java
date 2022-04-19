package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.requests.SetupRequestMessage;
import it.polimi.ingsw.network.requests.setupmessages.ConnectionRequest;
import it.polimi.ingsw.network.responses.Response;
import it.polimi.ingsw.view.UI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * The purpose of this Class is to:
 * <ul>
 *      <li>Send a message to the server in a <b>synchronous</b> way</li>
 *      <li>Listen to upcoming messages in an  <b>asynchronous</b> way</li>
 *      <li>To perform the action related to the message on the view in a <b>asynchronous</b> way</li>
 * </ul>
 *
 */
public class Client {
    private String ip;
    private int port;

    private String username;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ExecutorService executorReceiver = Executors.newCachedThreadPool();
    private ExecutorService executorHandler = Executors.newCachedThreadPool();

    private Socket socket;
    private UI ui;

    public Client(String username, String ip, int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
    }

    public void startClient() throws IOException {
        this.socket = new Socket(ip, port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

        //Invio richiesta connessione
        send(new ConnectionRequest(username));

        MessageReceiver messageReceiver = new MessageReceiver();
        executorReceiver.submit(messageReceiver);
    }

    /**
     * This Class is used in a dedicated thread to execute the
     * action relative to the message received.
     * <br>
     * It's a thread because the main thread of the client (view-cli) is
     * supposed to listen to the keyboard input stream
     *
     * <br><br>
     * <h1> ! IMPORTANT </h1>
     * Probably with the new strategy pattern implementation of the messaages
     * the message can be executed to the main thread because the message
     * contains "the implementation of the client side code".
     * So if needed it waits for the user input .... (not sure, because
     * there can be some input strings that the user can digit to view
     * different parts of the game)
     */
    private class MessageHandler implements Runnable {
        private Response response;
        public MessageHandler(Response response) {
            this.response = response;
        }
        public void run() {
            try {
                response.getResponseMessage().execute(Client.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This Class is used in a dedicated thread that listens
     * for new upcoming messages and submits each to a dedicated thread that
     * executes them
     */
    private class MessageReceiver implements Runnable{
        public void run(){
            while (true){
                try {
                    Response response = (Response) in.readObject();
                    MessageHandler messageHandler = new MessageHandler(response);
                    executorHandler.submit(messageHandler);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }


    public void send(RequestMessage request){
        try{
            out.writeObject(request);
            out.reset();
            out.flush();
        }catch (IOException e){
            ui.sendingFailed();
        }
    }

    public void send(SetupRequestMessage request){
        try{
            out.writeObject(request);
            out.reset();
            out.flush();
        }catch (IOException e){
            ui.sendingFailed();
        }
    }

    public UI getUi() {
        return this.ui;
    }

}
