package it.polimi.ingsw.view;

import it.polimi.ingsw.network.responses.ResponseMessage;

public class View {

    public View(){

    }


    public void invalidInput(ResponseMessage message){
        //TODO: invia tramite classe connection al client
    }

    /*
    //TODO: capire che tipo di messaggi arrivano dal client
    public void updateFromClient(ControllerMessage message) {
        this.notify(
                new ControllerMessage((ClientMessage) message, this, this.getUser())
        );
    }
    */

    public void updateFromModel(ResponseMessage message){
        //TODO: invia tramite classe connection al client
    }
}
