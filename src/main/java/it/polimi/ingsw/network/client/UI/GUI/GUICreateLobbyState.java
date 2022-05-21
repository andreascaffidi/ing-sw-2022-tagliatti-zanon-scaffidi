package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.CreateLobbySceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.MenuSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WelcomeSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.CreateLobbyMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
import javafx.fxml.FXMLLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class GUICreateLobbyState extends AbstractClientState {

    private Client client;
    private Scanner in;
    private String gameMode;
    private String host;
    private int numOfPlayers;

    public GUICreateLobbyState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render() throws MalformedURLException {
        this.choice();
    }

    private void choice() throws MalformedURLException {
        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\CreateLobbyScene.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);

        CreateLobbySceneController lobbySceneController = loader.getController();

        gameMode = lobbySceneController.getMode();
        numOfPlayers = lobbySceneController.getNum();
        host = client.getUsername();

        client.send(new CreateLobbyMessage(host, gameMode, numOfPlayers));
    }
}
