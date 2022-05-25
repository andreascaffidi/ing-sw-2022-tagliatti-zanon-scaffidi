package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.states.AbstractClientState;

/**
 * CLI waiting state class
 */
public class CLIWaitingState extends AbstractClientState {

    /**
     * builds a CLI waiting state class
     * @param client client
     */
    public CLIWaitingState(Client client){
        this.client = client;
    }

    /**
     * displays waiting state on command line
     */
    @Override
    public void render(){
        System.out.println(client.getWaitingMessage());
    }

    /**
     * manages server error on command line
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        //FIXME: it's only an ester egg
        System.out.println("Other players are trying to cheating :( ");
    }
}
