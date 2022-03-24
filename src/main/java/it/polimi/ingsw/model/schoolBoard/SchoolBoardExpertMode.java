package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerExpertMode;

public class SchoolBoardExpertMode extends SchoolBoard{

    private DiningRoomExpertMode diningRoomExpertMode;

    public SchoolBoardExpertMode(PlayerExpertMode player){
        super(player);
        diningRoomExpertMode = new DiningRoomExpertMode();
    }
}
