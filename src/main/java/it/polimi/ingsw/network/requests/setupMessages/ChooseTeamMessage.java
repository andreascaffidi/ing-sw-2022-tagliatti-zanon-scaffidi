package it.polimi.ingsw.network.requests.setupMessages;

import it.polimi.ingsw.network.requests.RequestMessage;
import it.polimi.ingsw.network.requests.SetupExecute;
import it.polimi.ingsw.network.server.Connection;

/**
 * choose team message
 */
public class ChooseTeamMessage implements RequestMessage, SetupExecute {
    private final int team;
    private final String selectedHost;

    /**
     * builds choose team message
     * @param team team selected (1 or 2)
     * @param selectedHost lobby's host name
     */
    public ChooseTeamMessage(int team, String selectedHost) {
        this.team = team;
        this.selectedHost = selectedHost;
    }

    /**
     * gets team selected
     * @return team selected
     */
    public int getTeam() {
        return team;
    }

    /**
     * gets host name selected
     * @return host name selected
     */
    public String getSelectedHost() {
        return selectedHost;
    }

    /**
     * implements SetupExecute interface
     * executes chooseTeam() method on the connection class
     * @param connection connection
     */
    @Override
    public void execute(Connection connection) {
        connection.chooseTeam(this);
    }
}
