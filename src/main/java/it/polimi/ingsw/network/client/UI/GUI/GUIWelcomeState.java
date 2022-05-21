package it.polimi.ingsw.network.client.UI.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.MenuSceneController;
import it.polimi.ingsw.network.client.UI.GUI.scenesController.WelcomeSceneController;
import it.polimi.ingsw.network.client.states.AbstractClientState;
import it.polimi.ingsw.network.requests.setupMessages.SetupRequestMessage;
import it.polimi.ingsw.network.responses.setupMessages.SetupResponsesTypes;
import javafx.fxml.FXMLLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class GUIWelcomeState extends AbstractClientState {

    private Client client;
    private Scanner in;
    private String username;

    public GUIWelcomeState(Client client){
        this.client = client;
        in = new Scanner(System.in);
    }

    @Override
    public void render() {

    }

    private void askUsername() throws MalformedURLException {
        URL url = Paths.get("C:\\Users\\wilta\\IdeaProjects\\ing-sw-2022-tagliatti-zanon-scaffidi\\src\\main\\java\\it\\polimi\\ingsw\\network\\client\\UI\\GUI\\scenesController\\WelcomeScene.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        WelcomeSceneController welcomeSceneController = loader.getController();
        username = welcomeSceneController.getUsername();


        client.setUsername(username.toLowerCase());
        client.send(new SetupRequestMessage(SetupResponsesTypes.USERNAME, username));
    }
}
