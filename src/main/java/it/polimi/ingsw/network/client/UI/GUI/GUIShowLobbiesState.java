package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.ShowLobbiesSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WelcomeSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.ChooseTeamMessage;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
import it.polimi.ingsw.network.server.Lobby;
import javafx.fxml.FXMLLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class GUIShowLobbiesState extends AbstractClientState {
    private Client client;
    private Scanner in;
    private List<Lobby> availableLobbies;
    private String selectedHost;
    private Lobby selectedLobby;

    public GUIShowLobbiesState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render() throws MalformedURLException {
        this.lobbyChosen();
    }

    private void lobbyChosen() throws MalformedURLException {
        /*URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\ShowLobbiesScene.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        ShowLobbiesSceneController showLobbiesSceneController = loader.getController();
        username = welcomeSceneController.getUsername();


        client.setUsername(username.toLowerCase());
        client.send(new SetupRequestMessage(SetupResponsesTypes.USERNAME, username));*/
    }
}
