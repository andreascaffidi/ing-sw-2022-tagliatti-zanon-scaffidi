package it.polimi.ingsw.network.client.UI.CLI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.reducedModel.ReducedModelExpertMode;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.client.states.ClientState;
import it.polimi.ingsw.network.requests.gameMessages.ChooseCloudMessage;

import java.util.Scanner;

/**
 * CLI choose cloud state class
 */
public class CLIChooseCloudState extends AbstractClientState {
    private final Client client;
    private final Scanner in;

    /**
     * builds a CLI choose cloud state class and print the main information
     * @param client client
     */
    public CLIChooseCloudState(Client client){
        this.client = client;
        in = new Scanner(System.in);

        client.getUI().showModel(client.getReducedModel());
        System.out.print("It's your turn! Choose a cloud by typing the "+Ansi.colorize("ID", Ansi.UNDERLINE));
        if (client.getReducedModel() instanceof ReducedModelExpertMode){
            System.out.print("\nOr you can even pay a character card from the available, by typing "
                    +Ansi.colorize("PAY CHARACTER", Ansi.UNDERLINE) +
                    " (you can pay a character card only one time per round)");
        }
        CLI.CTA("");
    }

    /**
     * displays choose cloud state on command line
     */
    @Override
    public void render(){
        int cloudChosen = 0;
        boolean exit = false;
        boolean payCharacter = false;

        while (!exit){
            String input = in.nextLine();
            if (client.getReducedModel() instanceof ReducedModelExpertMode &&
                    input.equalsIgnoreCase("PAY CHARACTER")){
                if ( ((ReducedModelExpertMode) client.getReducedModel()).isCharacterAlreadyPlayed()){
                    CLI.error("You have already played a character in this round ");
                }else{
                    exit = true;
                    payCharacter = true;
                    client.changeState(ClientState.PLAY_CHARACTER);
                }
            }else {
                try {
                    cloudChosen = Integer.parseInt(input);
                    if (cloudChosen < 1 || cloudChosen > client.getReducedModel().getClouds().size()) {
                        CLI.error("Invalid cloud ID ");
                    } else {
                        System.out.println("Cloud chosen, waiting for players...");
                        exit = true;
                    }
                } catch (NumberFormatException e) {
                    CLI.error("You have to insert a number ");
                }
            }
        }

        if (!payCharacter) {
            client.send(new ChooseCloudMessage(cloudChosen));
        }
    }

    /**
     * manages server error on command line
     * @param message error message
     */
    @Override
    public void serverError(String message) {
        CLI.error(message);
        render();
    }
}
