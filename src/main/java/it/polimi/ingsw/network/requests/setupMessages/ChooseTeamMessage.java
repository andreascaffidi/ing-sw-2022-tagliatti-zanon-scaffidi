package it.polimi.ingsw.network.requests.setupMessages;

import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.requests.SetupExecute;
import it.polimi.ingsw.network.server.Connection;

public class ChooseTeamMessage implements RequestMessage, SetupExecute {
    private final int team;
    private final String selectedHost;

    public ChooseTeamMessage(int team, String selectedHost) {
        this.team = team;
        this.selectedHost = selectedHost;
    }

    public int getTeam() {
        return team;
    }

    public String getSelectedHost() {
        return selectedHost;
    }

    @Override
    public void execute(Connection connection) {
        connection.chooseTeam(this);
    }
}
