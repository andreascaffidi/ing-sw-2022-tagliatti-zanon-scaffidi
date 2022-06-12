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
     * displays waiting state on command line and start waiting thread
     */
    @Override
    public void render(){
        //FIXME bloccare input
        System.out.println(client.getWaitingMessage());
    }

    /**
     * manages server error on command line
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        //do nothing
    }
}
