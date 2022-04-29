package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.requests.ControllerMessage;
import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.responses.ResponseMessage;
import it.polimi.ingsw.network.server.Connection;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.utils.Observer;

public class RemoteView extends Observable<ControllerMessage> implements Observer<ResponseMessage> {
    private Player player;
    private Connection connection;

    public RemoteView(Player player, Connection connection){
        this.player = player;
        this.connection = connection;
        this.connection.addObserver(new MessageReceiver(this));
    }

    /**
     * update from the model
     * @param message
     */
    @Override
    public void update(ResponseMessage message) {
        connection.send(message);
    }

    private class MessageReceiver implements Observer<RequestMessage> {

        private RemoteView remoteView;

        public MessageReceiver(RemoteView remoteView){
            this.remoteView = remoteView;
        }

        /**
         * message received from the client and sent to controller
         * @param message
         */
        @Override
        public void update(RequestMessage message) {
            this.remoteView.notify(new ControllerMessage(message, player.getUsername()));
        }
    }
}
