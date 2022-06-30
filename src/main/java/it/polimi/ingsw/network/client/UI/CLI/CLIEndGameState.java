package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedModel;
import it.polimi.ingsw.network.client.states.AbstractClientState;

/**
 * CLI end game state class
 */
public class CLIEndGameState extends AbstractClientState {
    private final Client client;

    /**
     * builds a CLI end game state class
     * @param client client
     */
    public CLIEndGameState(Client client){
        this.client = client;
    }

    /**
     * displays end game state on command line
     */
    @Override
    public void render()
    {
        ReducedModel reducedModel = client.getReducedModel();
        if (reducedModel.getBoards().size() == 4){
            int winningTeam = reducedModel.getBoard(client.getWinner()).getTagTeam();

            if (reducedModel.getBoard(client.getUsername()).getTagTeam() == winningTeam){
                this.showVictory();
                System.out.println("\"             Congratulations, your team won this game!          ");
            } else {
                this.showGameOver();
                System.out.println("                       And the winner is... not you! It's team " + Ansi.colorize(String.valueOf(winningTeam),Ansi.BACKGROUND_GREEN));
            }
        } else {
            if(client.getUsername().equals(client.getWinner())){
                this.showVictory();
                System.out.println("               Congratulations, you won this game!              ");
            }else{
                this.showGameOver();
                System.out.println("                           And the winner is... not you! It's  " + Ansi.colorize(client.getWinner(),Ansi.BACKGROUND_GREEN));
            }
        }
        client.disconnectClient();
    }

    private void showVictory(){
        String gameOver =   "▄██   ▄    ▄██████▄  ███    █▄        ▄█     █▄   ▄█  ███▄▄▄▄   \n" +
                            "███   ██▄ ███    ███ ███    ███      ███     ███ ███  ███▀▀▀██▄ \n" +
                            "███▄▄▄███ ███    ███ ███    ███      ███     ███ ███  ███   ███ \n" +
                            "▀▀▀▀▀▀███ ███    ███ ███    ███      ███     ███ ███  ███   ███ \n" +
                            "▄██   ███ ███    ███ ███    ███      ███     ███ ███  ███   ███ \n" +
                            "███   ███ ███    ███ ███    ███      ███     ███ ███  ███   ███ \n" +
                            "███   ███ ███    ███ ███    ███      ███ ▄█▄ ███ ███  ███   ███ \n" +
                            " ▀█████▀   ▀██████▀  ████████▀        ▀███▀███▀  █▀    ▀█   █▀  \n" +
                            "                                                                \n" ;
        System.out.println(Ansi.colorize(gameOver,Ansi.GREEN));
    }

    private void showGameOver(){
        String gameOver =  "   ▄██████▄     ▄████████   ▄▄▄▄███▄▄▄▄      ▄████████       ▄██████▄   ▄█    █▄     ▄████████    ▄████████ \n" +
                            "  ███    ███   ███    ███ ▄██▀▀▀███▀▀▀██▄   ███    ███      ███    ███ ███    ███   ███    ███   ███    ███ \n" +
                            "  ███    █▀    ███    ███ ███   ███   ███   ███    █▀       ███    ███ ███    ███   ███    █▀    ███    ███ \n" +
                            " ▄███          ███    ███ ███   ███   ███  ▄███▄▄▄          ███    ███ ███    ███  ▄███▄▄▄      ▄███▄▄▄▄██▀ \n" +
                            "▀▀███ ████▄  ▀███████████ ███   ███   ███ ▀▀███▀▀▀          ███    ███ ███    ███ ▀▀███▀▀▀     ▀▀███▀▀▀▀▀   \n" +
                            "  ███    ███   ███    ███ ███   ███   ███   ███    █▄       ███    ███ ███    ███   ███    █▄  ▀███████████ \n" +
                            "  ███    ███   ███    ███ ███   ███   ███   ███    ███      ███    ███ ███    ███   ███    ███   ███    ███ \n" +
                            "  ████████▀    ███    █▀   ▀█   ███   █▀    ██████████       ▀██████▀   ▀██████▀    ██████████   ███    ███ \n" +
                            "                                                                                                 ███    ███ \n" ;
        System.out.println(Ansi.colorize(gameOver,Ansi.RED));
    }

}
